package www.hbj.cloud.baselibrary.ngr_library.listener;

import android.view.View;
import android.widget.AdapterView;

import java.util.Calendar;

/**
 * NoDoubleItemClickListener
 */
public abstract class NoDoubleItemClickListener implements AdapterView.OnItemClickListener {

        public static final int MIN_CLICK_DELAY_TIME = 800;
        private long lastClickTime = 0;

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                lastClickTime = currentTime;
                onItemClickInternal(parent, view, position, id);
            }
        }

        public abstract void onItemClickInternal(AdapterView<?> parent, View view, int position, long id);
    }
