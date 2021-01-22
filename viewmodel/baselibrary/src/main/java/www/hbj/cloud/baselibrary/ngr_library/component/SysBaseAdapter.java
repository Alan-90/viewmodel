package www.hbj.cloud.baselibrary.ngr_library.component;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import www.hbj.cloud.baselibrary.ngr_library.listener.NoDoubleClickListener;
import www.hbj.cloud.baselibrary.ngr_library.utils.LogUtils;


/**
 * Created by Administrator on 2016/4/17.
 */
public class SysBaseAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


    public void onClick(View v) {

        LogUtils.w("Some widget defined click event, but not consumed");
    }


    /* Click listener that avoid double click event in short time*/
    public NoDoubleClickListener mNoDoubleClickListener = new NoDoubleClickListener() {

        @Override
        public void onClickInternal(View v) {
            SysBaseAdapter.this.onClick(v);
        }
    };

}
