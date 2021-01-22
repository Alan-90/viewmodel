package www.hbj.cloud.baselibrary.ngr_library.component.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

/**
 * CustomViewPager
 */
public class CustomViewPager extends ViewPager {

    private boolean isPagingEnabled = true;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event);
    }

    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = b;
    }

    //改变系统绘制顺序
    @Override
    protected int getChildDrawingOrder(int childCount, int i) {

        int position = getCurrentItem();
        if (position < 0) {
            return i;
        } else {
            if (i == childCount - 1) { //这是最后一个需要刷新的item
                if (position > i) {
                    position = i;
                }
                return position;
            }
            if (i == position) { //这是原本要在最后一个刷新的item
                return childCount - 1;
            }
        }
        return i;
    }

    /**
     * Inherited from the ViewPager, solve api13 and the following, when the ViewPager
     * nested child ViewPager cannot slide
     *
     * @param v
     * @param checkV
     * @param dx
     * @param x
     * @param y
     * @return
     */
    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if (v instanceof ViewGroup) {
            final ViewGroup group = (ViewGroup) v;
            final int scrollX = v.getScrollX();
            final int scrollY = v.getScrollY();
            final int count = group.getChildCount();
            // Count backwards - let topmost views consume scroll distance first.
            for (int i = count - 1; i >= 0; i--) {
                // TODO: Add versioned support here for transformed views.
                // This will not work for transformed views in Honeycomb+
                final View child = group.getChildAt(i);
                if (x + scrollX >= child.getLeft() && x + scrollX < child.getRight()
                        && y + scrollY >= child.getTop() && y + scrollY < child.getBottom()
                        && canScroll(child, true, dx, x + scrollX - child.getLeft(),
                        y + scrollY - child.getTop())) {
                    return true;
                }
            }
        }

        if (checkV) {
            // Direct call ViewPager.canScrollHorizontally(int)
            if (v instanceof ViewPager) {
                return ((ViewPager) v).canScrollHorizontally(-dx);
            } else {
                return ViewCompat.canScrollHorizontally(v, -dx);
            }
        } else {
            return false;
        }
    }

//    @Override
//    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
//        if (v != this && v instanceof ViewPager) {
//            ViewPager childViewPager = ((ViewPager)v);
//            if (childViewPager.getAdapter() == null) return true;
//            int currentItem = childViewPager.getCurrentItem();
//            int countItem = childViewPager.getAdapter().getCount();
//            if ((currentItem == (countItem - 1) && dx < 0) || (currentItem == 0 && dx > 0)) {
//                return false;
//            }
//            return true;
//        }
//        return super.canScroll(v, checkV, dx, x, y);
//    }
}
