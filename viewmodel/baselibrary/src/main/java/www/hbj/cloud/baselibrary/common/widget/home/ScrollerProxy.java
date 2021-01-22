package www.hbj.cloud.baselibrary.common.widget.home;

import android.content.Context;
import android.os.Build;

/**
 * @author Alan-kun
 * @date 2020/12/21.
 * description：
 */
public abstract class ScrollerProxy {
    public static ScrollerProxy getScroller(Context context) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD ? new PreGingerScroller(context) : new GingerScroller(context);
    }

    public abstract boolean computeScrollOffset();

    public abstract void startScroll(int startX, int startY, int dx, int dy, int duration);

    public abstract void fling(int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY);

    public abstract void forceFinished(boolean finished);

    public abstract boolean isFinished();

    public abstract int getCurrX();

    public abstract int getCurrY();
}
