package www.hbj.cloud.baselibrary.ngr_library.listener;

import android.view.View;

import java.util.Calendar;

/**
 * NoDoubleClickListener
 */
public abstract class NoDoubleClickListener implements View.OnClickListener {

        public static final int MIN_CLICK_DELAY_TIME = 400;
        private long lastClickTime = 0;

        @Override
        public void onClick(View v) {
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                lastClickTime = currentTime;
                onClickInternal(v);
            }
        }

        public abstract void onClickInternal(View v);
    }
