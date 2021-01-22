package www.hbj.cloud.platform;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;

import www.hbj.cloud.baselibrary.ngr_library.ActivityStack;
import www.hbj.cloud.baselibrary.ngr_library.net.RetrofitClient;

/**
 * @author Alan-kun
 * @date 2020/12/16.
 * description：
 */
public class BaseApplication extends Application {

    //以下属性应用于整个应用程序，合理利用资源，减少资源浪费
    private static Context mContext;//上下文


    @Override
    public void onCreate() {
        super.onCreate();
        //对全局属性赋值

        RetrofitClient.getInstance().intitRetrofitForApp307New();
        mContext = getApplicationContext();
        ActivityStack.init(mContext);
        ARouter.init((Application) mContext);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
//        if (!quickStart() && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            if (needWait(base)) {
//                waitForDexopt(base);
//            }
//            MultiDex.install(this);
//        } else {
//            return;
//        }
    }

    public static Context getAppContext() {
        return mContext;
    }



}
