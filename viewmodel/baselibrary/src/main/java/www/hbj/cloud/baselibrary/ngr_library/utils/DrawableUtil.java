package www.hbj.cloud.baselibrary.ngr_library.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2015/12/2 0002.
 */
public class DrawableUtil {

    private static final int OUT_BMP_WIDTH = 200;
    private static final int OUT_BMP_HEIGHT = 200;

    /**
     * 用于按压变色的控件背景
     * @param defaultColor
     * @param pressColor
     * @param conerPx
     * @return
     */
    public static Drawable getCornerDrawable(int defaultColor, int pressColor, float conerPx) {
        GradientDrawable defaultDrawable = new GradientDrawable();
        defaultDrawable.setColor(defaultColor);
        defaultDrawable.setCornerRadius(conerPx);
        GradientDrawable pressDrawable = new GradientDrawable();
        pressDrawable.setColor(pressColor);
        pressDrawable.setCornerRadius(conerPx);
        StateListDrawable stateDrawable = new StateListDrawable();
        stateDrawable.addState(new int[]{android.R.attr.state_checked, android.R.attr.state_focused},pressDrawable);
        stateDrawable.addState(new int[]{},defaultDrawable);
        return stateDrawable;
    }

    /**
     *纯色图片变色
     * @param drawable
     * @param color
     * @return
     */
    public static Drawable getTintDrawable(Drawable drawable, int color) {
        Drawable.ConstantState state = drawable.getConstantState();
        Drawable target = DrawableCompat.wrap(state == null ? drawable : state.newDrawable()).mutate();
        target.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        DrawableCompat.setTint(target, color);
        return target;

    }

    /**
     * 可以仅用一张图片实现控件按压后颜色改变的效果
     * @param c
     * @param drawableId
     * @param colorId
     * @return
     */
    private static Drawable getStateListDraable(Context c, int drawableId, int colorId) {
        Drawable drawable = ContextCompat.getDrawable(c,drawableId);
        int[] colors = new int[] { ContextCompat.getColor(c,colorId), Color.TRANSPARENT};
        int[][] states = new int[2][];
        states[0] = new int[] { android.R.attr.state_pressed};
        states[1] = new int[] {};
        ColorStateList colorList = new ColorStateList(states, colors);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(states[0],drawable);//注意顺序
        stateListDrawable.addState(states[1],drawable);
        Drawable.ConstantState state = stateListDrawable.getConstantState();
        drawable = DrawableCompat.wrap(state == null ? stateListDrawable : state.newDrawable()).mutate();
        DrawableCompat.setTintList(drawable,colorList);
        return drawable;
    }

    /**
     * 圆角纯色Drawable
     * @param color
     * @param conerPx
     * @return
     */
    public static GradientDrawable getCornerDrawableNoState(int color, float conerPx) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(conerPx);
        return  drawable;
    }

    /**
     * 圆角纯色Drawable
     * @param color
     * @param
     * @return
     */
    public static GradientDrawable getCornerDrawableNoState(int color, float topLeft, float topRight,
                                                            float bottomLeft, float bottomRight) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        float[] corners = new float[]{topLeft,topLeft, topRight, topRight,bottomLeft,bottomLeft,bottomRight,
                bottomRight};
        ((GradientDrawable)drawable.mutate()).setCornerRadii(corners);
        return  drawable;
    }

    /**
     * 圆角纯色Drawable，带有边框
     * @param solidColor 填充颜色
     * @param strokeColor 边框颜色
     * @param strokeWidth 边框宽度
     * @param conerPx 圆角度数
     * @return
     */
    public static GradientDrawable getCornerDrawableNoState(int solidColor, int strokeColor, int strokeWidth,
                                                            float conerPx) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(solidColor);
        drawable.setCornerRadius(conerPx);
        drawable.setStroke(strokeWidth, strokeColor);
        return  drawable;
    }

    /**
     * 圆形纯色drawable
     * @param color
     * @return
     */
    public static Drawable getOvalDrawable(int color) {

        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setShape(GradientDrawable.OVAL);
        d.setSize(1, 1);
        return d;
    }

    public static GradientDrawable getCornerDrawableNoState(int color, float[] conerPxs) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadii(conerPxs);
        return  drawable;
    }

    public static Bitmap getCornerDrawableFromDrawable(Drawable inDrawable) {
        BitmapDrawable bd = (BitmapDrawable) inDrawable;
        Bitmap inBitmap = bd.getBitmap();
        return getCornerDrawableFromBmp(inBitmap);
    }

    public static Bitmap getRoundDrawableFromDrawable(Drawable inDrawable) {
        BitmapDrawable bd = (BitmapDrawable) inDrawable;
        Bitmap inBitmap = bd.getBitmap();
        return getRoundDrawableFromBmp(inBitmap);
    }

    public static Bitmap getCornerDrawableFromBmp(Bitmap inBitmap) {
        inBitmap = BitmapHelp.zoomBitmap(inBitmap, OUT_BMP_WIDTH, OUT_BMP_WIDTH, true);
        int width = inBitmap.getWidth();
        int height = inBitmap.getHeight();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setColor(Color.WHITE);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

        Bitmap outBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);//创建一个空的bitmap
        Canvas canvas = new Canvas(outBitmap);

        Path path = new Path();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setColor(Color.WHITE);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

        RectF r = new RectF();
        r.left = 0;
        r.right = (int)width;
        r.top = 0;
        r.bottom = (int)height;

        path.addRoundRect(r, 12, 12, Path.Direction.CCW);
        path.close();
        canvas.drawRoundRect(r, 12, 12, paint);

        canvas.clipPath(path, Region.Op.REPLACE);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(inBitmap, 0, 0, paint);

        return outBitmap;
    }

    public static Bitmap getRoundDrawableFromBmp(Bitmap inBitmap) {
        int width = inBitmap.getWidth();
        int height = inBitmap.getHeight();

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setColor(Color.WHITE);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

        Bitmap outBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);//创建一个空的bitmap
        Canvas canvas = new Canvas(outBitmap);

        Path path = new Path();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setColor(Color.WHITE);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

        RectF r = new RectF();
        r.left = 0;
        r.right = (int)width;
        r.top = 0;
        r.bottom = (int)height;

        path.addCircle(width / 2, width / 2, width / 2, Path.Direction.CW);
        path.close();
        canvas.drawPath(path, paint);

        canvas.clipPath(path, Region.Op.REPLACE);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(inBitmap, 0, 0, paint);

        return outBitmap;
    }

    /**
     * 复制文件
     *
     * @param source 输入文件
     * @param target 输出文件
     */
    public static void copy(File source, File target) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(source);
            fileOutputStream = new FileOutputStream(target);
            byte[] buffer = new byte[1024];
            while (fileInputStream.read(buffer) > 0) {
                fileOutputStream.write(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
