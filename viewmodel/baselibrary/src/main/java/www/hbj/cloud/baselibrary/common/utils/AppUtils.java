package www.hbj.cloud.baselibrary.common.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import www.hbj.cloud.platform.BaseApplication;

/**
 * @author Alan-kun
 * @date 2020/12/16.
 * description：
 */
public class AppUtils {

    /**
     * 获取版本name
     * @return 当前应用的版本name
     */
    public static String getVersionName() {
        PackageManager pm = BaseApplication.getAppContext().getPackageManager();
        try {
            PackageInfo pInfo = pm.getPackageInfo(BaseApplication.getAppContext().getPackageName(), 0);

            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 获取 App versionCode
     * @return
     */
    public static int getVersionCode() {
        PackageManager packageManager = BaseApplication.getAppContext().getPackageManager();
        PackageInfo packageInfo;
        int versionCode  = 0;
        try {
            packageInfo = packageManager.getPackageInfo(BaseApplication.getAppContext().getPackageName(), 0);
            versionCode = packageInfo.versionCode ;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
}
