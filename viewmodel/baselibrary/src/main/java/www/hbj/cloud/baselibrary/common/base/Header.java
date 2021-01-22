package www.hbj.cloud.baselibrary.common.base;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import www.hbj.cloud.baselibrary.R;
import www.hbj.cloud.baselibrary.common.utils.ScreenUtil;

/**
 * @author Alan-kun
 * @date 2020/12/16.
 * description：
 */
public class Header {

    HeaderController mController;

    protected Header(HeaderController hc) {
        mController = hc;
    }

    public RelativeLayout getView() {
        return mController.mContainer;
    }

    public void setTitle(String title) {
        if (mController.mTitleView != null) {
            mController.mTitleView.setText(title);
        }
    }

    //设置返回按钮是否显示
    public void setBackButtonVisibility(int visibility) {
        if (mController.mBackView != null) {
            mController.mBackView.setVisibility(visibility);
        }
    }

    public void hideHeader() {
        mController.mContainer.setVisibility(View.GONE);
    }

    public void showHeader() {
        mController.mContainer.setVisibility(View.VISIBLE);
    }

    public TextView getLeftButton() {
        return mController.mLeftButton;
    }

    public TextView getRightTextView() {
        return mController.mRightTextView;
    }

    public ImageView getRightButton() {
        return mController.mRightButton;
    }

    public View getBackButton() {
        return mController.mBackView;
    }

    public TextView getTitleView() {
        return mController.mTitleView;
    }

    public static class Builder {
        HeaderController pHc;

        public Context mContext;

        public Builder(Context context, RelativeLayout container) {
            mContext = context;
            pHc = new HeaderController();
            pHc.mContainer = container;
            pHc.mContainer.setBackgroundColor(context.getResources().getColor(R.color.common_title_bg));
            setBackButton(null);
        }

        //设置头布局background
        public Builder setHeaderColor(int resId) {
            pHc.mContainer.setBackgroundResource(resId);
            return this;
        }

        public Builder backViewIsVisibility(boolean isVisibility) {
            if (pHc.mBackView != null) {
                if (isVisibility) {
                    pHc.mBackView.setVisibility(View.VISIBLE);
                } else {
                    pHc.mBackView.setVisibility(View.GONE);
                }
            }
            return this;
        }

        public Builder setBackButton(@Nullable View.OnClickListener listener) {
            ImageView backView = new ImageView(mContext);
            backView.setImageResource(R.mipmap.icon_back);
            if (listener != null) {
                backView.setOnClickListener(listener);
            }
            int padding = ScreenUtil.dp2px(mContext, 6);
            backView.setPadding(padding, padding, padding, padding);
            pHc.mBackView = backView;
            return this;
        }

        public Builder setLeftButton(int text, View.OnClickListener clickListener) {
            String content = "";
            if (text > 0) {
                content = mContext.getResources().getString(text);
            }
            TextView btn = new TextView(mContext);
            btn.setBackgroundColor(0x00000000);
            btn.setTextColor(mContext.getResources().getColor(R.color.white));
            btn.setText(content);
            btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            btn.setGravity(Gravity.CENTER);
            btn.setOnClickListener(clickListener);
            pHc.mLeftButton = btn;
            return this;
        }

        public Builder setRightButton(int src, View.OnClickListener clickListener) {
            ImageView btn = new ImageView(mContext);
            btn.setImageResource(src);
            btn.setOnClickListener(clickListener);
            int padding = ScreenUtil.dp2px(mContext, 10);
            btn.setPadding(padding, padding, padding, padding);
            pHc.mRightButton = btn;
            return this;
        }

        /**
         * 设置右边文字与点击事件
         *
         * @param str
         * @param clickListener
         * @return
         */
        public Builder setRightTextView(String str, View.OnClickListener clickListener) {
            TextView tv = new TextView(mContext);
            tv.setText(str);
            tv.setOnClickListener(clickListener);
            int padding = ScreenUtil.dp2px(mContext, 10);
            tv.setPadding(padding, padding, padding, padding);
            tv.setTextColor(mContext.getResources().getColor(R.color.c_053ce4));
            tv.setGravity(Gravity.CENTER);
            tv.setSingleLine(true);
            tv.setEllipsize(TextUtils.TruncateAt.END);
            tv.setMaxEms(12);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            pHc.mRightTextView = tv;
            return this;
        }

        /**
         * 设置右边文字、点击事件、颜色
         *
         * @param str
         * @param color
         * @param clickListener
         * @return
         */
        public Builder setRightTextView(String str, int color, View.OnClickListener clickListener) {
            TextView tv = new TextView(mContext);
            tv.setText(str);
            tv.setOnClickListener(clickListener);
            int padding = ScreenUtil.dp2px(mContext, 10);
            tv.setPadding(padding, padding, padding, padding);
            tv.setTextColor(mContext.getResources().getColor(color));
            tv.setGravity(Gravity.CENTER);
            tv.setSingleLine(true);
            tv.setEllipsize(TextUtils.TruncateAt.END);
            tv.setMaxEms(12);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            pHc.mRightTextView = tv;
            return this;
        }

        /**
         * 设置右边文字、点击事件、颜色、文字大小
         *
         * @param str
         * @param color
         * @param size
         * @param clickListener
         * @return
         */
        public Builder setRightTextView(String str, int color, int size, View.OnClickListener clickListener) {
            TextView tv = new TextView(mContext);
            tv.setText(str);
            tv.setOnClickListener(clickListener);
            int padding = ScreenUtil.dp2px(mContext, 10);
            tv.setPadding(padding, padding, padding, padding);
            tv.setTextColor(mContext.getResources().getColor(color));
            tv.setGravity(Gravity.CENTER);
            tv.setSingleLine(true);
            tv.setEllipsize(TextUtils.TruncateAt.END);
            tv.setMaxEms(12);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
            pHc.mRightTextView = tv;
            return this;
        }

        /**
         * 设置头部文字 从资源文件里拿到
         *
         * @param resId
         * @return
         */
        public Builder setTitle(int resId) {
            String text = mContext.getResources().getString(resId);
            return setTitle(text);
        }

        /**
         * 设置头部文字
         *
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            TextView titleView = new TextView(mContext);
            titleView.setText(title);
            titleView.setTextColor(ContextCompat.getColor(mContext, R.color.c_053ce4));
            titleView.setGravity(Gravity.CENTER);
            titleView.setSingleLine(true);
            titleView.setEllipsize(TextUtils.TruncateAt.END);
            titleView.setMaxEms(12);
            titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            pHc.mTitleView = titleView;
            return this;
        }

        /**
         * 设置头部文字和文字颜色
         *
         * @param title
         * @param color
         * @return
         */
        public Builder setTitle(String title, int color) {
            TextView titleView = new TextView(mContext);
            titleView.setText(title);
            titleView.setTextColor(ContextCompat.getColor(mContext, color));
            titleView.setGravity(Gravity.CENTER);
            titleView.setSingleLine(true);
            titleView.setEllipsize(TextUtils.TruncateAt.END);
            titleView.setMaxEms(12);
            titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            pHc.mTitleView = titleView;
            return this;
        }


        public Header build() {
            Header header = new Header(pHc);
            pHc.apply();
            return header;
        }
    }

    private static class HeaderController {

        private RelativeLayout mContainer;

        private TextView mTitleView;

        private ImageView mBackView;

        private TextView mLeftButton;

        private ImageView mRightButton;

        private TextView mRightTextView;

        HeaderController() {
        }

        void apply() {
            mContainer.removeAllViews();
            RelativeLayout.LayoutParams lp = null;
            if (mBackView != null) {
                lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                lp.addRule(RelativeLayout.CENTER_VERTICAL);
                mContainer.addView(mBackView, lp);
            }

            if (mTitleView != null) {
                lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                // int lrmargin = ScreenUtil.dp2px(50);
                // lp.leftMargin = lrmargin;
                // lp.rightMargin = lrmargin;
                mTitleView.setGravity(Gravity.CENTER);
                mContainer.addView(mTitleView, lp);
            }

            if (mLeftButton != null) {
                lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                lp.addRule(RelativeLayout.CENTER_VERTICAL);
                lp.leftMargin = 15;
                mContainer.addView(mLeftButton, lp);
            }

            if (mRightButton != null) {
                lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                lp.addRule(RelativeLayout.CENTER_VERTICAL);
                mContainer.addView(mRightButton, lp);
            }
            if (mRightTextView != null) {
                lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                lp.addRule(RelativeLayout.CENTER_VERTICAL);
                mContainer.addView(mRightTextView, lp);
            }
        }
    }

}
