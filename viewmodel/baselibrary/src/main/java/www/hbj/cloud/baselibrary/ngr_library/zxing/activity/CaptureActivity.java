/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package www.hbj.cloud.baselibrary.ngr_library.zxing.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.lifecycle.ViewModel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import www.hbj.cloud.baselibrary.common.base.Header;
import www.hbj.cloud.baselibrary.common.base.activity.BaseTitleActivity;
import www.hbj.cloud.baselibrary.databinding.ActivityQrscanBinding;
import www.hbj.cloud.baselibrary.ngr_library.Config;
import www.hbj.cloud.baselibrary.ngr_library.DevelopmentEnvironment;
import www.hbj.cloud.baselibrary.ngr_library.component.toast.SysToast;
import www.hbj.cloud.baselibrary.ngr_library.utils.LogUtils;
import www.hbj.cloud.baselibrary.ngr_library.zxing.camera.CameraManager;

/**
 * This activity opens the camera and does the actual scanning on a background thread. It draws a
 * viewfinder to help the user place the barcode correctly, shows feedback as the image processing
 * is happening, and then overlays the results when a scan is successful.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 * @author Sean Owen
 */
public final class CaptureActivity extends BaseTitleActivity<ActivityQrscanBinding, ViewModel> implements SurfaceHolder.Callback {
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CaptureActivity.class);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, int type) {
        Intent intent = new Intent(context, CaptureActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    public int mType;//1代表核销 2代表扫描快递单号
    public static final int HEXIAO_TYPE = 1;


    private MediaPlayer mediaPlayer;
    private static final float BEEP_VOLUME = 0.50f;
    private int x = 0;
    private int y = 0;
    private int cropWidth = 0;
    private int cropHeight = 0;
    private RelativeLayout mContainer = null;
    private RelativeLayout mCropLayout = null;
    private ImageView img1, img2;
    private TextView tv1, tv2;
    private LinearLayout ll_1, ll_2;

    private static final int BIND_HUIYUAN = 0;//二维码功能类型（0或不传为绑定会员，1为解析并跳转线下订单支付功能）
    private static final int OFFLINE_PAY = 1;
    private int turnType = 1;
    private TextView lblcenter;
    private TextView lblright;

    @Override
    protected ActivityQrscanBinding bindingView() {

        return ActivityQrscanBinding.inflate(getLayoutInflater());
    }

    @Override
    protected Class VMClass() {
        return ViewModel.class;
    }

    @Override
    public Header onCreateHeader(Header.Builder builder) {
        return null;
    }

    @Override
    public void initView() {
        initData();

    }


    private static final String TAG = CaptureActivity.class.getSimpleName();

    private static final long DEFAULT_INTENT_RESULT_DURATION_MS = 200L;

    private CameraManager cameraManager;
    private CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;
    private Collection<BarcodeFormat> decodeFormats;
    private Map<DecodeHintType, ?> decodeHints;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private BeepManager beepManager;
    private AmbientLightManager ambientLightManager;

    public static void show(Activity activity, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(activity, CaptureActivity.class);
        intent.setAction(Intents.Scan.ACTION);
        intent.putExtra(Intents.Scan.MODE, Intents.Scan.QR_CODE_MODE);
        activity.startActivityForResult(intent, requestCode);
    }

    ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    CameraManager getCameraManager() {
        return cameraManager;
    }

//    @Override
//    protected int getContentView() {
//        Window window = getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        return R.layout.activity_qr_scan;
//    }

    public void initData() {

        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
        beepManager = new BeepManager(this);
        ambientLightManager = new AmbientLightManager(this);
    }


    @Override
    protected void onResume() {
        super.onResume();

        // CameraManager must be initialized here, not in onCreate(). This is necessary because we don't
        // want to open the camera driver and measure the screen size if we're going to show the help on
        // first launch. That led to bugs where the scanning rectangle was the wrong size and partially
        // off screen.
        cameraManager = new CameraManager(getApplication());

//        viewfinderView = findViewById(R.id.viewfinder_view);
        viewfinderView.setCameraManager(cameraManager);

        handler = null;

        setRequestedOrientation(getCurrentOrientation());

        resetStatusView();

        beepManager.updatePrefs();
        ambientLightManager.start(cameraManager);

        inactivityTimer.onResume();

        Intent intent = getIntent();

        decodeFormats = null;
        characterSet = null;

        if (intent != null) {
            String action = intent.getAction();

            if (Intents.Scan.ACTION.equals(action)) {

                // Scan the formats the intent requested, and return the result to the calling activity.
                decodeFormats = DecodeFormatManager.parseDecodeFormats(intent);
                decodeHints = DecodeHintManager.parseDecodeHints(intent);

                if (intent.hasExtra(Intents.Scan.WIDTH) && intent.hasExtra(Intents.Scan.HEIGHT)) {
                    int width = intent.getIntExtra(Intents.Scan.WIDTH, 0);
                    int height = intent.getIntExtra(Intents.Scan.HEIGHT, 0);
                    if (width > 0 && height > 0) {
                        cameraManager.setManualFramingRect(width, height);
                    }
                }

                if (intent.hasExtra(Intents.Scan.CAMERA_ID)) {
                    int cameraId = intent.getIntExtra(Intents.Scan.CAMERA_ID, -1);
                    if (cameraId >= 0) {
                        cameraManager.setManualCameraId(cameraId);
                    }
                }
            }

            characterSet = intent.getStringExtra(Intents.Scan.CHARACTER_SET);
        }

//        SurfaceView surfaceView = findViewById(R.id.capture_preview);
//        SurfaceHolder surfaceHolder = surfaceView.getHolder();
//        if (hasSurface) {
//            // The activity was paused but not stopped, so the surface still exists. Therefore
//            // surfaceCreated() won't be called, so init the camera here.
//            initCamera(surfaceHolder);
//        } else {
//            // Install the callback and wait for surfaceCreated() to init the camera.
//            surfaceHolder.addCallback(this);
//        }
    }

    private int getCurrentOrientation() {
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            switch (rotation) {
                case Surface.ROTATION_0:
                case Surface.ROTATION_90:
                    return ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                case Surface.ROTATION_180:
                case Surface.ROTATION_270:
                default:
                    return ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
            }
        } else {
            switch (rotation) {
                case Surface.ROTATION_0:
                case Surface.ROTATION_270:
                    return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                case Surface.ROTATION_180:
                case Surface.ROTATION_90:
                default:
                    return ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
            }
        }
    }

    @Override
    protected void onPause() {
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        inactivityTimer.onPause();
        ambientLightManager.stop();
        beepManager.close();
        cameraManager.closeDriver();
        //historyManager = null; // Keep for onActivityResult
//        if (!hasSurface) {
//            SurfaceView surfaceView = findViewById(R.id.capture_preview);
//            SurfaceHolder surfaceHolder = surfaceView.getHolder();
//            surfaceHolder.removeCallback(this);
//        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }


    boolean flag = true;

    protected void light() {
        if (flag == true) {
            flag = false;
            // 开闪光灯
            cameraManager.openLight();
        } else {
            flag = true;
            // 关闪光灯
            cameraManager.offLight();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                setResult(RESULT_CANCELED);
                finish();
                return true;
            case KeyEvent.KEYCODE_FOCUS:
            case KeyEvent.KEYCODE_CAMERA:
                // Handle these events so they don't launch the Camera app
                return true;
            // Use volume up/down to turn on light
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                cameraManager.setTorch(false);
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                cameraManager.setTorch(true);
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (holder == null) {
            Log.e(TAG, "*** WARNING *** surfaceCreated() gave us a null surface!");
        }
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // do nothing
    }

    /**
     * A valid barcode has been found, so give an indication of success and show the results.
     *
     * @param rawResult The contents of the barcode.
     */
    public void handleDecode(Result rawResult) {


        final String result = rawResult.getText();

        LogUtils.w("handleDecode:" + rawResult.getText());
        if (Config.mEnvironment != DevelopmentEnvironment.SnjkRelease) {
            SysToast.showShort(rawResult.getText());
        }


    }


    private void resetCapture() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (handler != null) {
                    handler.restartPreviewAndDecode();
                }
            }
        }, 1500);
    }

    // Briefly show the contents of the barcode, then handle the result outside Barcode Scanner.
    private void handleDecodeExternally(Result rawResult) {
        // Hand back whatever action they requested - this can be changed to Intents.Scan.ACTION when
        // the deprecated intent is retired.
        Intent intent = new Intent(getIntent().getAction());
//        intent.addFlags(Intents.FLAG_NEW_DOC);
        intent.putExtra(Intents.Scan.RESULT, rawResult.toString());
        intent.putExtra(Intents.Scan.RESULT_FORMAT, rawResult.getBarcodeFormat().toString());
        byte[] rawBytes = rawResult.getRawBytes();
        if (rawBytes != null && rawBytes.length > 0) {
            intent.putExtra(Intents.Scan.RESULT_BYTES, rawBytes);
        }
        Map<ResultMetadataType, ?> metadata = rawResult.getResultMetadata();
        if (metadata != null) {
            if (metadata.containsKey(ResultMetadataType.UPC_EAN_EXTENSION)) {
                intent.putExtra(Intents.Scan.RESULT_UPC_EAN_EXTENSION,
                        metadata.get(ResultMetadataType.UPC_EAN_EXTENSION).toString());
            }
            Number orientation = (Number) metadata.get(ResultMetadataType.ORIENTATION);
            if (orientation != null) {
                intent.putExtra(Intents.Scan.RESULT_ORIENTATION, orientation.intValue());
            }
            String ecLevel = (String) metadata.get(ResultMetadataType.ERROR_CORRECTION_LEVEL);
            if (ecLevel != null) {
                intent.putExtra(Intents.Scan.RESULT_ERROR_CORRECTION_LEVEL, ecLevel);
            }
            @SuppressWarnings("unchecked")
            Iterable<byte[]> byteSegments = (Iterable<byte[]>) metadata.get(ResultMetadataType.BYTE_SEGMENTS);
            if (byteSegments != null) {
                int i = 0;
                for (byte[] byteSegment : byteSegments) {
                    intent.putExtra(Intents.Scan.RESULT_BYTE_SEGMENTS_PREFIX + i, byteSegment);
                    i++;
                }
            }
//            if (handler != null) {
//                beepManager.playBeepSoundAndVibrate();
//                Message message = Message.obtain(handler, R.id.return_scan_result, intent);
//                handler.sendMessageDelayed(message, DEFAULT_INTENT_RESULT_DURATION_MS);
//            }
        }


    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (cameraManager.isOpen()) {
            Log.w(TAG, "initCamera() while already open -- late SurfaceView callback?");
            return;
        }
        try {
            cameraManager.openDriver(surfaceHolder);
            // Creating the handler starts the preview, which can also throw a RuntimeException.
//            if (handler == null) {
//                handler = new CaptureActivityHandler(this, decodeFormats, decodeHints, characterSet, cameraManager);
//            }
        } catch (IOException ioe) {
            Log.w(TAG, ioe);
            displayFrameworkBugMessageAndExit();
        } catch (RuntimeException e) {
            // Barcode Scanner has seen crashes in the wild of this variety:
            // java.?lang.?RuntimeException: Fail to connect to camera service
            Log.w(TAG, "Unexpected error initializing camera", e);
            displayFrameworkBugMessageAndExit();
        }
    }

    private void displayFrameworkBugMessageAndExit() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(getString(R.string.app_name));
//        builder.setMessage(getString(R.string.msg_camera_framework_bug));
//        builder.setPositiveButton(R.string.button_ok, new FinishListener(this));
//        builder.setOnCancelListener(new FinishListener(this));
//        builder.show();
    }

    private void resetStatusView() {
        viewfinderView.setVisibility(View.VISIBLE);
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();
    }

    public static class GetDecodeInfo implements Serializable {
        public GetDecodeInfo(String result) {
            this.result = result;
        }

        public String result;
    }
}
