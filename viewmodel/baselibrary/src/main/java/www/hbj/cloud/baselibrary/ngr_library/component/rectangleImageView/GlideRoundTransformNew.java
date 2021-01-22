package www.hbj.cloud.baselibrary.ngr_library.component.rectangleImageView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * 正方形外带椭圆角的图片控件
 *
 * @author Administrator
 */
public class GlideRoundTransformNew extends BitmapTransformation {
    private static final String ID = "com.xiaohe.www.lib.tools.glide.GlideCircleTransform";

    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    private static float radius = 0f;

    /**
     * 构造函数 默认圆角半径 4dp
     *
     * @param context Context
     */
    public GlideRoundTransformNew(Context context) {
        this(context, 4);
    }

    /**
     * 构造函数
     *
     * @param context Context
     * @param dp      圆角半径
     */
    public GlideRoundTransformNew(Context context, int dp) {
        super();
        radius = Resources.getSystem().getDisplayMetrics().density * dp;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return roundCrop(pool, toTransform);
    }

    private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);
        return result;
    }


    @Override

    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

        messageDigest.update(ID_BYTES);

    }

}