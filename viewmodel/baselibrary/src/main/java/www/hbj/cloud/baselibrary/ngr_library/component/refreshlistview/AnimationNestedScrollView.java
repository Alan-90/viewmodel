package www.hbj.cloud.baselibrary.ngr_library.component.refreshlistview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

/**
 * Created by OSS on 2015/12/22 0022.
 *
 */
public class AnimationNestedScrollView extends NestedScrollView {
    private OnAnimationScrollChangeListener listener;

    public AnimationNestedScrollView(@NonNull Context context) {
        super(context);
    }

    public AnimationNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimationNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnAnimationScrollListener(OnAnimationScrollChangeListener listener) {
        this.listener = listener;
    }

    public interface OnAnimationScrollChangeListener {
        void onScrollChanged(float dy,float t);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (listener != null) {
            listener.onScrollChanged(getScrollY() * 0.65f,t);//x0.65 使位移效果更加平滑 解决手指按住停留时抖动问题
        }
    }
}