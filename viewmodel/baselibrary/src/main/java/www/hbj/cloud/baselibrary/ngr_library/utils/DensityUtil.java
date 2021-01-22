package www.hbj.cloud.baselibrary.ngr_library.utils;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import www.hbj.cloud.baselibrary.ngr_library.Config;


/**
 * dp与px互转辅助类
 *
 * @author hyr
 * @version [版本号, 2013-8-26]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DensityUtil {
    
    private static int width;
    private static int height;
    
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = Config.mContext
                .getResources().getDisplayMetrics().density;
        final float width = Config.mContext
                .getResources().getDisplayMetrics().widthPixels;
        final float height = Config.mContext
                .getResources().getDisplayMetrics().heightPixels;

        int result = 0;
        if (width == 1080 && height == 1920 && scale == 1) {
            //大屏使用
            result = (int) (dpValue * scale * width / 320);
        } else {
            //正常参数
            result =  (int) (dpValue * scale + 0.5f);
        }
        return result;
    }

    public static int sp2px(float value) {
        DisplayMetrics metric = Config.mContext
                .getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, metric);
    }

    public static int px2sp(float pxValue) {
        final float fontScale = Config.mContext
                .getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 仅供setTextSize时调用，适配不同尺寸同个density的屏幕
     * @param value
     * @return
     */
    public static int sp2textsize(int value) {
        DisplayMetrics metric = Config.mContext
                .getResources().getDisplayMetrics();
        final float width = Config.mContext
                .getResources().getDisplayMetrics().widthPixels;
        final float height = Config.mContext
                .getResources().getDisplayMetrics().heightPixels;
        final float scale = metric.density;
        int result = 0;
        if (width == 1080 && height == 1920 && scale == 1) {
            //大屏使用
            result = (int) (value * width / 320);
        } else {
            //正常参数
            result =  value;
        }
        return result;
    }

    /**
     * 仅供SizeSpan时调用，适配不同尺寸同个density的屏幕
     * @param value
     * @return
     */
    public static int sp2sizespan(int value) {
        DisplayMetrics metric =Config.mContext
                .getResources().getDisplayMetrics();
        final float width = Config.mContext
                .getResources().getDisplayMetrics().widthPixels;
        final float height = Config.mContext
                .getResources().getDisplayMetrics().heightPixels;
        final float scale = metric.density;
        int result = 0;
        if (width == 1080 && height == 1920 && scale == 1) {
            //大屏使用
            result = (int) (value * scale * width / 320);
        } else {
            //正常参数
            result = (int) (value * scale);
        }
        return result;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    
    public static DisplayMetrics getDeviceDisplay(Activity c) {
        DisplayMetrics metric = new DisplayMetrics();
        c.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric; // 屏幕宽度（像素）
        //        int height = metric.heightPixels; // 屏幕高度（像素）
        //        float density = metric.density; // 屏幕密度（0.75 / 1.0 / 1.5）
        //        int densityDpi = metric.densityDpi; // 屏幕密度DPI（120 / 160 / 240）
    }
    
    //Context 获取屏幕尺寸
    public static DisplayMetrics getDeviceDisplay(Context c) {
        Resources r;
        
        if (c == null) {
            r = c.getResources();
        }else{
        return null;
        }
        return null;
    }

    /**
     * 获取系统的密度
     *
     * @param context
     * @return
     */
    public static float getDensity(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);

        return displayMetrics.density;
    }

    public static int getDeviceWidth(Activity c) {
        if (width > 0) {
            return width;
        }
        DisplayMetrics metrics = getDeviceDisplay(c);
        return width = metrics.widthPixels;
        //        int height = metric.heightPixels; // 屏幕高度（像素）
        //        float density = metric.density; // 屏幕密度（0.75 / 1.0 / 1.5）
        //        int densityDpi = metric.densityDpi; // 屏幕密度DPI（120 / 160 / 240）
    }
    
    public static int getDeviceHeight(Activity c) {
        if (height > 0) {
            return height;
        }
        DisplayMetrics metrics = getDeviceDisplay(c);
        return height = metrics.heightPixels;
        //        int height = metric.heightPixels; // 屏幕高度（像素）
        //        float density = metric.density; // 屏幕密度（0.75 / 1.0 / 1.5）
        //        int densityDpi = metric.densityDpi; // 屏幕密度DPI（120 / 160 / 240）
    }
    
    public static int getWidth() {
        if (width > 0) {
            return width;
        }
        return width = Config.mContext.getResources()
                .getDisplayMetrics().widthPixels;
    }
    
    public static int getHeight() {
        
        if (height > 0) {
            return height;
        }
        return height = Config.mContext
                .getResources().getDisplayMetrics().heightPixels;
    }

    /**
     *
     * @param activity
     * @return > 0 success; <= 0 fail
     */
    public static int  getStatusHeight(Activity activity) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = activity.getResources().getDimensionPixelSize(i5);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    public static boolean isLargeScreen() {
        DisplayMetrics metric = Config.mContext.getResources()
                .getDisplayMetrics();
        final float width = Config.mContext.getResources()
                .getDisplayMetrics().widthPixels;
        final float height = Config.mContext.getResources()
                .getDisplayMetrics().heightPixels;
        final float scale = metric.density;
        if (width == 1080 && height == 1920 && scale == 1) {
            //大屏
            return true;
        } else {
            //正常
            return false;
        }
    }
}
