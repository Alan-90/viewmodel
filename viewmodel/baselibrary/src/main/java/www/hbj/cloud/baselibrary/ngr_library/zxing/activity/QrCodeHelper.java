package www.hbj.cloud.baselibrary.ngr_library.zxing.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.EnumMap;
import java.util.Hashtable;
import java.util.Map;

import www.hbj.cloud.baselibrary.ngr_library.utils.TextUtil;

/**
 * 二维码生成帮助类
 */
public class QrCodeHelper {

    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;

    private final String text;
    private final int size;
    private final BarcodeFormat format;
    private final Bitmap logoBitmap;

    public static Bitmap createQRCodeBitmap(String content, int width, int height,
                                            String character_set, String error_correction_level,
                                            String margin) {
        // 字符串内容判空
        if (TextUtil.isNull(content)) {
            return null;
        }
        // 宽和高>=0
        if (width < 0 || height < 0) {
            return null;
        }
        /** 1.设置二维码相关配置 */
        Hashtable<EncodeHintType, String> hints = new Hashtable<>();
        // 字符转码格式设置
        if (!TextUtil.isNull(character_set)) {
            hints.put(EncodeHintType.CHARACTER_SET, character_set);
        }
        // 容错率设置
        if (!TextUtil.isNull(error_correction_level)) {
            hints.put(EncodeHintType.ERROR_CORRECTION, error_correction_level);
        }
        // 空白边距设置
        if (!TextUtil.isNull(margin)) {
            hints.put(EncodeHintType.MARGIN, margin);
        }
        /** 2.将配置参数传入到QRCodeWriter的encode方法生成BitMatrix(位矩阵)对象 */
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        /** 3.创建像素数组,并根据BitMatrix(位矩阵)对象为数组元素赋颜色值 */
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //bitMatrix.get(x,y)方法返回true是黑色色块，false是白色色块
                if (bitMatrix.get(x, y)) {
                    pixels[y * width + x] = BLACK;//黑色色块像素设置
                } else {
                    pixels[y * width + x] = WHITE;// 白色色块像素设置
                }
            }
        }
        /** 4.创建Bitmap对象,根据像素数组设置Bitmap每个像素点的颜色值,并返回Bitmap对象 */
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }



    private QrCodeHelper(Builder builder) {
        text = builder.text;
        size = builder.size;
        logoBitmap = builder.logoBitmap;
        format = BarcodeFormat.QR_CODE;
    }

    public Bitmap createQrCodeBitmap() {
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.MARGIN, 1);
        hints.put(EncodeHintType.QR_VERSION, 5);

        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(text, format, size, size, hints);
        } catch (Exception e) {
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

        if (logoBitmap != null) {
            final Canvas canvas = new Canvas(bitmap);
            final int logoSize = size / 4;
            final int padding = (size - logoSize) / 2;
            final Bitmap logo = setScale(logoBitmap, logoSize);

            final Rect pictureRect = new Rect(0, 0, logoSize, logoSize);
            final RectF dst = new RectF(padding, padding, logoSize + padding, logoSize + padding);
            canvas.drawBitmap(logo, pictureRect, dst, null);
            logo.recycle();
        }

        return bitmap;
    }

    private static Bitmap setScale(Bitmap bitmap, int size) {
        final int bitmapWidth = bitmap.getWidth();
        final int bitmapHeight = bitmap.getHeight();

        if (bitmapWidth != size) {
            float scale = ((float) size) / bitmapWidth;

            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth, bitmapHeight, matrix, true);

            bitmap.recycle();

            return resizedBitmap;
        }

        return bitmap;
    }

    public static class Builder {

        private String text;
        private int size;
        private Bitmap logoBitmap;

        public Builder() {

        }

        public void setText(String text) {
            this.text = text;
        }

        public void setSize(int width, int height) {
            this.size = Math.min(width, height);
        }

        public void setLogoBitmap(Bitmap logoBitmap) {
            this.logoBitmap = logoBitmap;
        }

        public QrCodeHelper bulid() {
            return new QrCodeHelper(this);
        }
    }
}
