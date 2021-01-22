package www.hbj.cloud.baselibrary.ngr_library.component.gallery;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

/** 
 * TODO<请描述这个类是干什么的> 
 * @author  zhengji 
 * @data:  2015年12月17日 下午2:21:46 
 * @version:  V1.0 
 */
public class SysGallery extends Gallery {

	private Context context;
    private float downx;
    private float position;
    private boolean scrollhint = true;

	public SysGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2) {
		return e2.getX() > e1.getX();
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
		int keyCode;
		if (isScrollingLeft(e1, e2)) { 
			keyCode = KeyEvent.KEYCODE_DPAD_LEFT;
		} else {
			keyCode = KeyEvent.KEYCODE_DPAD_RIGHT;
		}
		onKeyDown(keyCode, null);
		return true;
	}

    
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
    	int action = ev.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
        	downx = ev.getX();
        	position = this.getSelectedItemPosition();
        } else if (action == MotionEvent.ACTION_UP) {
            /*
        	float upx = ev.getX();
        	if (upx - downx > DensityUtil.dip2px(30) && position == 0)
        	{
                if (scrollhint)
        		    SysToast.show(context.getResources().getText(R.string.photo_no_more_content), 1000);
        	}
        	else if (downx - upx > DensityUtil.dip2px(30) && position == this.getCount() - 1)
        	{
                if (scrollhint)
        		    SysToast.show(context.getResources().getText(R.string.photo_no_more_content), 1000);
        	}*/
        }
        
		return super.dispatchTouchEvent(ev);
    }

    public void setScrollhintEnable(boolean enable) {
        scrollhint = enable;
    }
}
