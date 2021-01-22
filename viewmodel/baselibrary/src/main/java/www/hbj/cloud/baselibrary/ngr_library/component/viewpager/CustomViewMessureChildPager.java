package www.hbj.cloud.baselibrary.ngr_library.component.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * CustomViewPager
 */
public class CustomViewMessureChildPager extends ViewPager {

    private boolean isPagingEnabled = true;

    public CustomViewMessureChildPager(Context context) {
        super(context);
    }

    public CustomViewMessureChildPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private int mCurPosition;
    private int mHeight = 0;

    private HashMap<Integer, View> mChildrenViews = new LinkedHashMap<Integer, View>();

    private boolean scrollble = true;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mChildrenViews.size() > mCurPosition) {
            View child = mChildrenViews.get(mCurPosition);
            if (child != null) {
                child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                mHeight = child.getMeasuredHeight();
            }
        }

        if (mHeight != 0) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void updateHeight(int current) {
        this.mCurPosition = current;
        if (mChildrenViews.size() > current) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight);
            } else {
                layoutParams.height = mHeight;
            }

            setLayoutParams(layoutParams);
        }
    }

    public void setViewPosition(View view, int position) {
        mChildrenViews.put(position, view);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        getParent().requestDisallowInterceptTouchEvent(true);
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
