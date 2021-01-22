package www.hbj.cloud.baselibrary.common.utils;

import android.view.Gravity;
import android.widget.Toast;

import www.hbj.cloud.platform.BaseApplication;


/**
 * @author Alan-kun
 * @date 2020/12/17.
 * description：
 */
public class ToastUtils {
    public static void showToast(String res){
        Toast toast = Toast.makeText(BaseApplication.getAppContext(),res,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public static void showToast(int res){
        Toast toast = Toast.makeText(BaseApplication.getAppContext(),res,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

}
