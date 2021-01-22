package www.hbj.cloud.baselibrary.ngr_library.component.toast;

import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import www.hbj.cloud.baselibrary.R;
import www.hbj.cloud.baselibrary.ngr_library.Config;
import www.hbj.cloud.baselibrary.ngr_library.utils.TextUtil;


/**
 * 自定义的toast
 * @author zhengji 2015-11-08
 * 
 */
public class SysToast {
	private static String currentToast;

	public static void show(CharSequence text, int duration) {
        show(Config.mContext, text, duration);
    }

	public static void show(Context c, CharSequence text, int duration) {
		View toastRoot = LayoutInflater.from(c).inflate(R.layout.toast_rootview, null);
		final Toast toast = new Toast(c);
        toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setView(toastRoot);
		TextView tv = (TextView)toastRoot.findViewById(R.id.TextViewInfo);
        if (tv == null) {
            return;
        }

        toast.setDuration(Toast.LENGTH_LONG);
		tv.setText(text);

        if (duration == Toast.LENGTH_LONG) {
            duration = 3500;
        } else if (duration == Toast.LENGTH_SHORT) {
            duration = 2000;
        }

        //不显示重复发起的toast
        if (!TextUtil.isNull(currentToast)
                && currentToast.equals(text.toString())) {
            return;
        }

        if (text != null) {
            currentToast = text.toString();
        }

        toast.show();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                currentToast = null;
            }
        }, duration);
    }

    public static void show(Context context, int resId, int duration)
                                throws Resources.NotFoundException {
        show(Config.mContext.getResources().getText(resId), duration);
    }

    public static void show(int resId, int duration)
            throws Resources.NotFoundException {
    	show(Config.mContext.getResources().getText(resId), duration);
	}
    
	public static void showLong(String text) {
		show(text, Toast.LENGTH_LONG);
	}

	public static void showShort(String text) {
		show(text, Toast.LENGTH_SHORT);
	}

    public static void showShort(int resId) {
        showShort(Config.mContext
                .getResources().getText(resId).toString());
    }
}
