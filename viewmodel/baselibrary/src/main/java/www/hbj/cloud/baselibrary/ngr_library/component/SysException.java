package www.hbj.cloud.baselibrary.ngr_library.component;

import android.app.Activity;
import android.os.Build;

import androidx.annotation.RequiresApi;

import www.hbj.cloud.baselibrary.ngr_library.component.toast.SysToast;
import www.hbj.cloud.baselibrary.ngr_library.utils.TextUtil;


/**
 * 自定义http fail处理
 *
 * @author zhengji 2015-11-11 create
 */
public class SysException {

    public static void processHttpException(int code, String msg) {
        if (code == 403 || code == 409) {
        }
        if (!TextUtil.isNull(msg) && msg.indexOf("java.net.UnknownHostException") != -1) {
            SysToast.show("网络不健康，请检查网络", 1000);
        } else {
            SysToast.show("服务器有小情绪了，请再试试", 1000);
        }

    }

    public static void processHttpException(Activity activity, int code, String msg) {
        processHttpException(code, msg);
    }

    /**
     * 查看activity是否已经被销毁
     *
     * @param activity
     * @return 销毁 false；未销毁 true
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean checkActivity(Activity activity) {
        if (activity == null) {
            return false;
        } else if (activity.isFinishing()) {
            return false;
        } else if (activity.isDestroyed()) {
            return false;
        }

        return true;
    }
}
