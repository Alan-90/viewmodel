package www.hbj.cloud.baselibrary.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import www.hbj.cloud.baselibrary.AnimationLoaderUtils;
import www.hbj.cloud.baselibrary.R;
import www.hbj.cloud.baselibrary.common.RetryListener;

/**
 * @author Alan-kun
 * @date 2020/12/16.
 * description：
 */
public class ErrorView extends LinearLayout {

    private Context context;
    private AttributeSet attrs;

    private ImageView mErrorImage;
    private TextView mErrorTitle;
    private int errorImageRes;

    private String errorTitle;

    private boolean showTitle = true;

    private RetryListener listener;

    public ErrorView(Context context) {
        this(context, null);
    }

    public ErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;
        init();
    }

    private void init() {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ErrorView, 0, 0);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.error_view_layout, this, true);
        setBackgroundColor(getResources().getColor(R.color.common_bg));
        mErrorImage = findViewById(R.id.error_image);
        mErrorTitle = findViewById(R.id.error_title);

        try {
            errorImageRes = a.getResourceId(R.styleable.ErrorView_ev_errorImage, R.mipmap.ic_launcher);
            errorTitle = a.getString(R.styleable.ErrorView_ev_errorTitle);
            if (errorImageRes != 0)
                setErrorImageResource(errorImageRes);

            if (errorTitle != null)
                setErrorTitle(errorTitle);

            if (!showTitle)
                mErrorTitle.setVisibility(GONE);

        } finally {
            a.recycle();
        }

        mErrorImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationSet animationSet = (AnimationSet) AnimationLoaderUtils
                        .loadAnimation(getContext(), R.anim.my_image_scale_in);
                view.startAnimation(animationSet);
                if (listener != null) listener.onRetry();
            }
        });
    }

    /**
     * Attaches a listener that to the view that reports retry events.
     */
    public void setOnRetryListener(RetryListener listener) {
        this.listener = listener;
    }

    /**
     * Sets error image to a given drawable resource
     *
     * @param res drawable resource.
     */
    public void setErrorImageResource(int res) {
        mErrorImage.setImageResource(res);
    }

    /**
     * Sets the error image to a given {@link Drawable}.
     *
     * @param drawable {@link Drawable} to use as error image.
     */
    public void setErrorImageDrawable(Drawable drawable) {
        mErrorImage.setImageDrawable(drawable);
    }

    /**
     * Sets the error image to a given {@link Bitmap}.
     *
     * @param bitmap {@link Bitmap} to use as error image.
     */
    public void setErrorImageBitmap(Bitmap bitmap) {
        mErrorImage.setImageBitmap(bitmap);
    }

    /**
     * Sets the error title to a given {@link String}.
     *
     * @param text {@link String} to use as error title.
     */
    public void setErrorTitle(String text) {
        mErrorTitle.setText(text);
    }

    /**
     * Sets the error title to a given string resource.
     *
     * @param res string resource to use as error title.
     */
    public void setErrorTitle(int res) {
        mErrorTitle.setText(res);
    }

    /**
     * Sets the error title text to a given color.
     *
     * @param res color resource to use for error title text.
     */
    public void setErrorTitleColor(int res) {
        mErrorTitle.setTextColor(res);
    }


    /**
     * Shows or hides the error title
     */
    public void showTitle(boolean show) {
        mErrorTitle.setVisibility(show ? VISIBLE : GONE);
    }

}