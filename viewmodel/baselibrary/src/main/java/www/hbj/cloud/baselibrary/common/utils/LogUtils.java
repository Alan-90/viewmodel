package www.hbj.cloud.baselibrary.common.utils;

import android.util.Log;

import www.hbj.cloud.baselibrary.BuildConfig;


/**
 * @author Alan-kun
 * @date 2020/12/16.
 * description：
 */
public class LogUtils {

    public static void e(String msg){
        if (BuildConfig.DEBUG) {
            Log.e("TAG", msg);
        }
    }

    public static void i(String msg){
        if (BuildConfig.DEBUG) {
            Log.i("TAG", msg);
        }
    }

    public static void d(String msg){
        if (BuildConfig.DEBUG) {
            Log.d("TAG", msg);
        }
    }

    public static void v(String msg){
        if (BuildConfig.DEBUG) {
            Log.v("TAG", msg);
        }
    }

}
