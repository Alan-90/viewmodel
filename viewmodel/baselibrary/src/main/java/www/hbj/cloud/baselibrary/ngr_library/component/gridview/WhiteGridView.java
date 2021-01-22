package www.hbj.cloud.baselibrary.ngr_library.component.gridview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * 空白区域可以点击
 * 
 * @author Administrator
 *
 */
public class WhiteGridView extends GridView {
	private GestureDetector gestureDetector;

	public WhiteGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public WhiteGridView(Context context) {
		super(context);
		init(context);
	}

	public WhiteGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		gestureDetector = new GestureDetector(context,new MSimpleOnGestureListener());
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = MeasureSpec.makeMeasureSpec(
				Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mTouchBlankPosListener != null) {
			if (!isEnabled()) {
				return isClickable() || isLongClickable();
			}
			int action = event.getActionMasked();
			float x = event.getX();
			float y = event.getY();
			int motionPosition = pointToPosition((int) x, (int) y);
			if (motionPosition == INVALID_POSITION) {
				gestureDetector.onTouchEvent(event);
			}
		}
		return super.onTouchEvent(event);
	}
	private OnTouchBlankPositionListener mTouchBlankPosListener;
	/**
	 * 设置GridView的空白区域的触摸事件
	 *
	 * @param listener
	 */
	public void setOnTouchBlankPositionListener(
			OnTouchBlankPositionListener listener) {
		mTouchBlankPosListener = listener;
	}

	public interface OnTouchBlankPositionListener {
		void onTouchBlank(MotionEvent event);
	}


	public class MSimpleOnGestureListener extends GestureDetector.SimpleOnGestureListener{

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			mTouchBlankPosListener.onTouchBlank(e);
			return super.onSingleTapUp(e);
		}
	}
}
