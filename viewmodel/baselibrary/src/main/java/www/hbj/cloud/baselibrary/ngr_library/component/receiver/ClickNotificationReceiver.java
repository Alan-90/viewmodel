package www.hbj.cloud.baselibrary.ngr_library.component.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class ClickNotificationReceiver extends BroadcastReceiver {
    public static final String KEY_REALINTENT = "realIntent";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent mainIntent = new Intent();
        mainIntent.putExtra("index", 1);
        mainIntent.setClassName(context, "com.easygroup.ngaridoctor.home.HomeActivity");
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(mainIntent);

    }

    public static void startDetailActivityWithMainActivity(Context context, Class<? extends Activity> homeClazz,
                                                           Intent detailIntent) {
        Intent mainIntent = new Intent(context, homeClazz);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Intent[] intents = {mainIntent, detailIntent};
        context.startActivities(intents);
    }

    public static Intent creat(Context c, String className) {
        Intent appIntent = new Intent(Intent.ACTION_MAIN);
        //appIntent.setAction(Intent.ACTION_MAIN);
        appIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        appIntent.setComponent(new ComponentName(c.getPackageName(), className));
        appIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);//关键的一步，设置启动模式
        return appIntent;
    }
}
