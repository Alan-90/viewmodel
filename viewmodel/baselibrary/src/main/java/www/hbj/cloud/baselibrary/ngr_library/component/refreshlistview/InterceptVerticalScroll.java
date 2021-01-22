package www.hbj.cloud.baselibrary.ngr_library.component.refreshlistview;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

/***
 *
 * 垂直滚动拦截对象
 *
 * @Author:  九龙藤
 * @CreateDate: 2019/8/20 09:58
 * @Description:
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark: 添加文件头作者信息
 *
 **/
public class InterceptVerticalScroll implements View.OnTouchListener {
	int initialY;
	int initialX;
	int rawX;
	int rawY;

	ViewGroup parentView;


	int activePointerId;

	int mTouchSlop;
	private boolean eatEvents;

	public InterceptVerticalScroll(ViewGroup parentView, CheckCanScroll checkCanScroll) {
		this.parentView = parentView;
		this.checkCanScroll = checkCanScroll;
		mTouchSlop = ViewConfiguration.get(parentView.getContext()).getScaledTouchSlop();

		parentView.setOnTouchListener(this);
	}

	boolean checked ;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getActionMasked();
		switch (action) {
			case 0:
				this.initialX = (int) event.getX();
				this.initialY = (int) event.getY();
				rawX = (int) event.getRawX();
				rawY = (int) event.getRawY();
				initialY = (int) event.getY();
				activePointerId = event.getPointerId(0);
				if(this.activePointerId == -1){
					checked = true;
					eatEvents = false;
				}
				return true;

			case MotionEvent.ACTION_MOVE:

				if (eatEvents) {
					parentView.getParent().requestDisallowInterceptTouchEvent(true);
					return true;
				}
				if(checked){
					break;
				}
				if (Math.abs((float) this.initialY - event.getY()) >
						(float) this.mTouchSlop) {
					if(checkCanScroll != null){
						eatEvents = !checkCanScroll.canScrollVertically(rawX,rawY,initialX,initialY);
						if(!eatEvents){
							parentView.getParent().requestDisallowInterceptTouchEvent(true);
						}
						checked = true;
					}
				}

				if (Math.abs((float) this.initialX - event.getX()) >
						(float) this.mTouchSlop) {
					if(checkCanScroll != null){
						eatEvents = !checkCanScroll.canScrollHorizontally(rawX,rawY,initialX,initialY);
						if(!eatEvents){
							parentView.getParent().requestDisallowInterceptTouchEvent(true);
						}
						checked = true;
					}
				}
				if(!checked){
					return true;
				}


				break;
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:

				eatEvents = false;
				checked = false;
				return false;

		}


		return eatEvents;
	}
	CheckCanScroll checkCanScroll;
	public static abstract class CheckCanScroll{
		public boolean canScrollVertically(int rawX,int rawY,int x,int y){
			return true;
		}
		public boolean canScrollHorizontally(int rawX ,int rawY,int x,int y){
			return true;
		}

	}
}