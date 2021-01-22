package www.hbj.cloud.baselibrary.ngr_library.component.gridview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 全部高度
 */
public class NestGrideView extends GridView {
    public NestGrideView(Context context) {
        super(context);
    }

    public NestGrideView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NestGrideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
