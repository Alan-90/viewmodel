package www.hbj.cloud.baselibrary.common.base.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import www.hbj.cloud.baselibrary.R;
import www.hbj.cloud.baselibrary.common.PermissionListener;
import www.hbj.cloud.baselibrary.common.RetryListener;
import www.hbj.cloud.baselibrary.common.base.Header;
import www.hbj.cloud.baselibrary.common.utils.StatusBarUtil;
import www.hbj.cloud.baselibrary.common.view.ErrorView;

/**
 * @author Alan-kun
 * @date 2020/12/16.
 * description：包含头部的基类
 */
public abstract class BaseTitleActivity<T extends ViewBinding, VM extends ViewModel> extends BaseActivity implements RetryListener {
    private Context mContext;
    protected Header mHeader;
    protected View mDivideLine;
    protected View mContentView;
    private Bundle mSavedInstanceState;
    private LinearLayout container;
    private BitmapFactory.Options options = new BitmapFactory.Options();
    private RelativeLayout headerContainer;
    ErrorView mErrorView;


    protected abstract T bindingView();

    protected abstract Class<VM> VMClass();

    protected T binding;

    protected VM viewModel;


    private static final int REQUEST_PERMISSION = 1;

    private PermissionListener permissionListener;

    private ProgressDialog mProgress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 禁止页面自动弹出输入法, or 禁止输入法顶起布局
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.base_title_layout);

        binding = bindingView();
        viewModel = new ViewModelProvider(this).get(VMClass());

        mContext = this;
        StatusBarUtil.transparencyBar(this);

        this.mSavedInstanceState = savedInstanceState;
        options.inJustDecodeBounds = true;
        mErrorView = findViewById(R.id.view_error);
        mErrorView.setOnRetryListener(this);
        headerContainer =  findViewById(R.id.header_container);
        headerContainer.setBackgroundColor(getResources().getColor(R.color.common_title_bg));
        mDivideLine = findViewById(R.id.main_layout_divider);
        initHeader(headerContainer);
        addView();
    }


    private void initHeader(RelativeLayout headerContainer) {
        Header.Builder builder = new Header.Builder(this, headerContainer);
        mHeader = onCreateHeader(builder);
        if (mHeader == null) {
            headerContainer.setVisibility(View.GONE);
            return;
        }
        if (null != mHeader.getBackButton()) {
            mHeader.getBackButton().setOnClickListener(v -> finish());
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            if (mContentView != null) {
                container.removeView(mContentView);
            }
//            addView();
        }
    }

    private void addView() {
        mContentView = binding.getRoot();//将布局添加到
        if (mContentView != null) {
            container = findViewById(R.id.main_layout_container);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
            lp.weight = 1;
            container.addView(mContentView, lp);
        }
        initView();
        initData();
    }

    public void setFullScreenBackground(int res){
        container.setBackgroundResource(res);
    }

    public abstract Header onCreateHeader(Header.Builder builder);


    public abstract void initView();

    /**
     * 加载当前Activity所需数据
     */
    public abstract void initData();

    /**
     * @param visible
     */
    public void setHeaderLineVisibility(int visible) {
        if (mHeader != null && mHeader.getView() != null) {
            mHeader.getView().setVisibility(visible);
        }
        mDivideLine.setVisibility(visible);
    }

    public Bundle getSavedInstanceState() {
        return this.mSavedInstanceState;
    }


    /**
     * 设置页面为空显示
     *
     * @param resId 图片资源id
     * @param code  提示语资源id
     */
    public void setEmptyView(int resId, String code) {                //, int Width, int Height
        if (resId != 0) {
            mContentView.setVisibility(View.GONE);
            mErrorView.setVisibility(View.VISIBLE);
            mErrorView.setErrorImageResource(resId);
        }
        mErrorView.setErrorTitle(code);
    }

    /**
     * 设置页面为空显示
     *
     * @param resId 图片资源id
     * @param code  提示语资源id
     */
    public void setEmptyView(int resId, String code,int color) {                //, int Width, int Height
        if (resId != 0) {
            mContentView.setVisibility(View.GONE);
            mErrorView.setVisibility(View.VISIBLE);
            mErrorView.setErrorImageResource(resId);
        }
        mErrorView.setErrorTitle(code);
        if (color != 0){
            mErrorView.setErrorTitleColor(getColorResources(color));
        }
    }

    public void setEmptyView(String code) {
        mContentView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
        mErrorView.setErrorTitle(code);
    }

    /*透明底 可以完全看到底部的背景*/
    public void setEmptyView() {
        mContentView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
        mErrorView.setBackgroundColor(getColorResources(R.color.transparent));
    }


    public void setHideEmptyView() {
        mContentView.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
    }


    public void hideDivideLine() {
        if (mDivideLine != null && mDivideLine.getVisibility() != View.GONE)
            mDivideLine.setVisibility(View.GONE);
    }


    /**
     * 获取内容区域父view
     *
     * @return
     */
    public LinearLayout getContentView() {
        return container;
    }

    public String getStringResources(int resources) {
        return this.getResources().getString(resources);
    }

    public int getColorResources(int resources) {
        return this.getResources().getColor(resources);
    }

    /**
     *  获取布局文件
     * @param layout
     * @return
     */
    public View getLayoutView( int layout){
        return LayoutInflater.from(mContext).inflate(layout,null);
    }

    /**
     * 错误页面点击回调
     */
    @Override
    public void onRetry() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
