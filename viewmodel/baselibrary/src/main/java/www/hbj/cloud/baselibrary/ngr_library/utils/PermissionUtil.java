package www.hbj.cloud.baselibrary.ngr_library.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.collection.SimpleArrayMap;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.ArrayList;
import java.util.List;

import www.hbj.cloud.baselibrary.R;
import www.hbj.cloud.baselibrary.ngr_library.component.dialog.SysAlertDialog;


/**
 * Created by Administrator on 2017/8/24 0024.
 */

public class PermissionUtil {
    public static final String CAMERA = Manifest.permission.CAMERA;
    public static final String RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    public static final String ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String WRITE_CONTACTS = Manifest.permission.WRITE_CONTACTS;
    public static final String READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String CALL_PHONE = Manifest.permission.CALL_PHONE;
    public static final String[] NECESSARY_PERMISSION = new String[]{WRITE_EXTERNAL_STORAGE,
            READ_EXTERNAL_STORAGE, READ_PHONE_STATE,ACCESS_FINE_LOCATION};
    public static final String[] NECESSARY_VIDEO_PERMISSION = new String[]{CAMERA,
            RECORD_AUDIO, ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION};

    public static List<String> getIntersection(List<String> list, String... permissions) {
        ArrayList<String> strings = new ArrayList<>();
        for (String p : permissions) {
            if (list.indexOf(p) >= 0) {
                strings.add(p);
            }
        }
        return strings;
    }

    public static void requestPermission(final Activity activity, final String permision, final String hintText,
                                         final boolean showSetting, final SinglePermissionCallback resultAction) {
        resultAction.requestCount++;
        AndPermission.with(activity)
                .requestCode(100)
                .permission(permision)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
                        if (AndPermission.hasPermission(activity, permision)) {
                            rationale.cancel();
                            return;
                        }
                        if (TextUtil.isNull(hintText)) {
                            rationale.resume();
                            return;
                        }
                        // 自定义对话框。
                        AlertDialog.newBuilder(activity)
                                .setTitle("获取权限")
                                .setMessage(hintText)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        rationale.resume();
                                    }
                                })
                                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        rationale.cancel();
                                    }
                                }).show();
                    }
                })
                .callback(new com.yanzhenjie.permission.PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> list) {
                        if (AndPermission.hasPermission(activity, list)) {
                            resultAction.call(true);
                        } else {
                            resultAction.call(false);
                        }
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> list) {
                        if (AndPermission.hasPermission(activity, permision)) {
                            resultAction.call(true);
                        } else {
                            if (AndPermission.hasAlwaysDeniedPermission(activity, list)) {
                                if (showSetting) {
                                    AndPermission.defaultSettingDialog(activity, 400).show();
                                }
                                resultAction.call(false);
                            } else {
                                if (resultAction.requestCount > 1) {
                                    resultAction.call(false);
                                } else {
                                    requestPermission(activity, permision, hintText, showSetting, resultAction);
                                }
                            }
                        }


                    }
                })
                .start();
    }
    public static void requestLocationPermission(final Activity activity, final String permision, final String hintText,
                                                 final boolean showSetting, final SinglePermissionCallback resultAction) {
        resultAction.requestCount++;
        AndPermission.with(activity)
                .requestCode(100)
                .permission(permision)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
                        if (AndPermission.hasPermission(activity, permision)) {
                            rationale.cancel();
                            return;
                        }
                        if (TextUtil.isNull(hintText)) {
                            rationale.resume();
                            return;
                        }
                        // 自定义对话框。
                        AlertDialog.newBuilder(activity)
                                .setTitle("获取权限")
                                .setMessage(hintText)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        rationale.resume();
                                    }
                                })
                                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        rationale.cancel();
                                    }
                                }).show();
                    }
                })
                .callback(new com.yanzhenjie.permission.PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> list) {
                        if (AndPermission.hasPermission(activity, list)) {
                            resultAction.call(true);
                        } else {
                            resultAction.call(false);
                        }
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> list) {
                        SysAlertDialog.Builder builder = new SysAlertDialog.Builder(activity);
                        builder.setTitle("温馨提示");
                        builder.setMessage("建议您开启定位权限，就能看到 更多周边好店和惊喜优惠");
                        builder.setPositiveButton(
                                "暂不", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        builder.setNegativeButton(
                                "开启", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                                        intent.setData(uri);
                                        activity.startActivityForResult(intent, 400);
                                        dialog.dismiss();
                                    }
                                });
                        final SysAlertDialog sysAlertDialog = builder.create();
                        sysAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                            @Override
                            public void onShow(DialogInterface dialog) {
                                sysAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                                        .setTextColor(ContextCompat.getColor(activity,
                                                R.color.textColorBlue));
                            }
                        });
                        sysAlertDialog.show();


                    }
                })
                .start();
    }

    public static void requestPermissions(final Activity activity, final String[] permision, final String hintText,
                                          final boolean showSetting, final SinglePermissionCallback resultAction) {
        resultAction.requestCount++;
        AndPermission.with(activity)
                .requestCode(100)
                .permission(permision)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
                        if (AndPermission.hasPermission(activity, permision)) {
                            rationale.cancel();
                            return;
                        }
                        if (TextUtil.isNull(hintText)) {
                            rationale.resume();
                            return;
                        }
                        // 自定义对话框。
                        AlertDialog.newBuilder(activity)
                                .setTitle("获取权限")
                                .setMessage(hintText)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        rationale.resume();
                                    }
                                })
                                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        rationale.cancel();
                                    }
                                }).show();
                    }
                })
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> list) {
                        if (AndPermission.hasPermission(activity, list)) {
                            resultAction.call(true);
                        } else {
                            resultAction.call(false);
                        }
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> list) {
                        if (AndPermission.hasPermission(activity, permision)) {
                            resultAction.call(true);
                        } else {
                            if (AndPermission.hasAlwaysDeniedPermission(activity, list)) {
                                if (showSetting) {
                                    AndPermission.defaultSettingDialog(activity, 400).show();
                                }
                                resultAction.call(false);
                            } else {
                                if (resultAction.requestCount > 1) {
                                    resultAction.call(false);
                                } else {
                                    requestPermissions(activity, permision, hintText, showSetting, resultAction);
                                }
                            }
                        }


                    }
                })
                .start();
    }

    /**
     * Builder
     */
    public static class Builder {
        private Activity activity;
        private String permision;
        private String[] permisions;
        private String hintText;
        private SinglePermissionCallback resultAction;
        private boolean showSetting;

        public Builder(String permission) {
            this.permision = permission;
        }

        public Builder(String[] permissions) {
            this.permisions = permissions;
        }

        public Builder activity(Activity activity) {
            this.activity = activity;
            return this;
        }

        public Builder hintText(String hintText) {
            this.hintText = hintText;
            return this;

        }

        public Builder callback(SinglePermissionCallback resultAction) {
            this.resultAction = resultAction;
            return this;
        }

        public Builder showSetting(boolean showSetting) {
            this.showSetting = showSetting;
            return this;
        }

        public void build() {
            if (!TextUtil.isNull(permision)) {
                requestPermission(activity, permision, hintText, showSetting, resultAction);
            } else {
                requestPermissions(activity, permisions, hintText, showSetting, resultAction);
            }

        }

    }

    /**
     * SinglePermissionCallback
     */
    public abstract static class SinglePermissionCallback {
        int requestCount;

        public abstract void call(Boolean result);
    }

    public static Builder newBuilder(String pemission) {
        return new Builder(pemission);
    }

    public static Builder newBuilder(String[] pemission) {
        return new Builder(pemission);
    }


    // Map of dangerous permissions introduced in later framework versions.
    // Used to conditionally bypass permission-hold checks on older devices.
    private static final SimpleArrayMap<String, Integer> MIN_SDK_PERMISSIONS;

    static {
        MIN_SDK_PERMISSIONS = new SimpleArrayMap<>(8);
        MIN_SDK_PERMISSIONS.put("com.android.voicemail.permission.ADD_VOICEMAIL", 14);
        MIN_SDK_PERMISSIONS.put("android.permission.BODY_SENSORS", 20);
        MIN_SDK_PERMISSIONS.put("android.permission.READ_CALL_LOG", 16);
        MIN_SDK_PERMISSIONS.put("android.permission.READ_EXTERNAL_STORAGE", 16);
        MIN_SDK_PERMISSIONS.put("android.permission.USE_SIP", 9);
        MIN_SDK_PERMISSIONS.put("android.permission.WRITE_CALL_LOG", 16);
        MIN_SDK_PERMISSIONS.put("android.permission.SYSTEM_ALERT_WINDOW", 23);
        MIN_SDK_PERMISSIONS.put("android.permission.WRITE_SETTINGS", 23);
    }

    private static volatile int targetSdkVersion = -1;


    /**
     * Checks all given permissions have been granted.
     *
     * @param grantResults results
     * @return returns true if all permissions have been granted.
     */
    public static boolean verifyPermissions(int... grantResults) {
        if (grantResults.length == 0) {
            return false;
        }
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if the permission exists in this SDK version
     *
     * @param permission permission
     * @return returns true if the permission exists in this SDK version
     */
    private static boolean permissionExists(String permission) {
        // Check if the permission could potentially be missing on this device
        Integer minVersion = MIN_SDK_PERMISSIONS.get(permission);
        // If null was returned from the above call, there is no need for a device API level check for the permission;
        // otherwise, we check if its minimum API level requirement is met
        return minVersion == null || Build.VERSION.SDK_INT >= minVersion;
    }

    /**
     * Returns true if the Activity or Fragment has access to all given permissions.
     *
     * @param context     context
     * @param permissions permission list
     * @return returns true if the Activity or Fragment has access to all given permissions.
     */
    public static boolean hasSelfPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (permissionExists(permission) && !hasSelfPermission(context, permission)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determine context has access to the given permission.
     * <p>
     * This is a workaround for RuntimeException of Parcel#readException.
     * For more detail, check this issue https://github.com/hotchemi/PermissionsDispatcher/issues/107
     *
     * @param context    context
     * @param permission permission
     * @return returns true if context has access to the given permission, false otherwise.
     * @see #hasSelfPermissions(Context, String...)
     */
    @SuppressLint("WrongConstant")
    private static boolean hasSelfPermission(Context context, String permission) {
        switch (Build.MANUFACTURER) {
            case "Xiaomi": {

                return checkSelfPermissionForXiaomi(context, permission);

            }
            default: {

                try {
                    return PermissionChecker.checkSelfPermission(context, permission)
                            == PackageManager.PERMISSION_GRANTED;
                } catch (RuntimeException t) {
                    return false;
                }

            }
        }
    }


    /**
     * "Xiaomi" phone is different others,need add AppOpsManager
     *
     * @param context
     * @param permission
     * @return
     */
    @SuppressLint("WrongConstant")
    @TargetApi(23)
    private static boolean checkSelfPermissionForXiaomi(Context context, String permission) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            try {
                return PermissionChecker.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
            } catch (RuntimeException t) {
                return false;
            }
        }
        int auth = ActivityCompat.checkSelfPermission(context, permission);

        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        int checkOp = appOpsManager.checkOp(AppOpsManager.permissionToOp(permission),
                Process.myUid(), context.getPackageName());

        if (auth == PackageManager.PERMISSION_GRANTED && checkOp == AppOpsManager.MODE_ALLOWED) {
            return true;
        }
        if (auth == PackageManager.PERMISSION_GRANTED && checkOp == AppOpsManager.MODE_IGNORED) {

            return false;
        }
        return false;
    }


    /**
     * Checks given permissions are needed to show rationale.
     *
     * @param activity    activity
     * @param permissions permission list
     * @return returns true if one of the permission is needed to show rationale.
     */
    public static boolean shouldShowRequestPermissionRationale(Activity activity, String... permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get target sdk version.
     *
     * @param context context
     * @return target sdk version
     */
    @TargetApi(Build.VERSION_CODES.DONUT)
    public static int getTargetSdkVersion(Context context) {
        if (targetSdkVersion != -1) {
            return targetSdkVersion;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            targetSdkVersion = packageInfo.applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return targetSdkVersion;
    }

}
