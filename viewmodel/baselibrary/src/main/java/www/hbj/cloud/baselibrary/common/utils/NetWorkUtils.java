package www.hbj.cloud.baselibrary.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import www.hbj.cloud.platform.BaseApplication;

/**
 * @author Alan-kun
 * @date 2020/12/16.
 * description：
 */
public class NetWorkUtils {

    /**
     * 获取网络信息
     * @return
     */
    public static boolean isNetworkAvailable() {

        ConnectivityManager cm = (ConnectivityManager) BaseApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info !=null){
            return info.isAvailable();
        }

        return false;
    }
    /**
     *
     * 获取网络类型
     *
     */
    public static int getNetType() {

        ConnectivityManager cm = (ConnectivityManager) BaseApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            return -1;
        } else {
            return info.getType();
        }
    }


}
