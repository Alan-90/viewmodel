package www.hbj.cloud.baselibrary.ngr_library.component.gridview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 加了间隔线的gridview
 *
 * @author Administrator
 *
 */
public class SysGridView extends GridView {
	private int dividerColor = -1;
	private boolean mIsdividercolor = true;

	public SysGridView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public SysGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SysGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);

	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO 自动生成的方法存根
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
