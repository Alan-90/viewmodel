package www.hbj.cloud.baselibrary.ngr_library.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * author : wangqiang
 * e-mail : xxx@xx
 * time   : 2020/10/14
 * desc   :
 */
public class CenterTextView extends AppCompatTextView {
    private StaticLayout myStaticLayout;
    private TextPaint tp;

    public CenterTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        initView();
    }

    private void initView()
    {
        tp = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        tp.setTextSize(getTextSize());
        tp.setColor(getCurrentTextColor());
        myStaticLayout = new StaticLayout(getText(), tp, getWidth(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        myStaticLayout.draw(canvas);
    }
}
