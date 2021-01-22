package www.hbj.cloud.baselibrary.common.widget.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import www.hbj.cloud.baselibrary.R;
import www.hbj.cloud.baselibrary.common.utils.LogUtils;

/**
 * 单选列表
 *
 * @author Alan-kun
 * @date 2021/1/8.
 * description：
 */
public class PopupWindowCheckSingleChoose extends PopupWindow {

    private Activity mContext;

    /**
     * 列表和按钮的监听事件
     */
    private onEventLisenter mLisenter;

    /**
     * 父View
     */
    private View mView;
    /**
     * 顶部标题
     */
    private TextView mTvTag;
    /**
     * 底部按钮
     */
    private TextView mTvButtom;
    /**
     * 选择ListView
     */
    private RecyclerView recyclerView;

    /**
     * 顶部标题内容
     */
    private String mTagTxt;
    /**
     * 底部按钮内容
     */
    private String mButtomTxt;

    /**
     * 选中item
     */
    private String itemText;

    /**
     * 数据源
     */
    private ArrayList<String> mList = new ArrayList<>();
    /**
     * 适配器
     */
    private MyCheckAdapter mAdapter;

    private boolean isFullScreen = true;//横坐标是否全屏 默认全屏

    /**
     * 设置顶部内容区域是否显示
     *
     * @param isShow
     * @return
     */
    public PopupWindowCheckSingleChoose isShowTopTagText(boolean isShow) {
        if (isShow) {
            mTvTag.setVisibility(View.VISIBLE);
        } else {
            mTvTag.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 设置顶部内容
     *
     * @param tagTxt
     * @return
     */
    public PopupWindowCheckSingleChoose setTagTxt(String tagTxt) {
        mTagTxt = tagTxt;
        if (mTvTag != null) {
            mTvTag.setText(mTagTxt);
        }
        return this;
    }

    /**
     * 设置底部按钮内容
     *
     * @param itemText
     * @return
     */
    public PopupWindowCheckSingleChoose setItemViewText(String itemText) {
        this.itemText = itemText;
        mAdapter.notifyDataSetChanged();
        return this;
    }

    /**
     * 设置底部按钮内容
     *
     * @param buttomTxt
     * @return
     */
    public PopupWindowCheckSingleChoose setButtomTxt(String buttomTxt) {
        mButtomTxt = buttomTxt;
        if (mTvButtom != null) {
            mTvButtom.setText(mButtomTxt);
        }
        return this;
    }

    public PopupWindowCheckSingleChoose(Activity context, ArrayList<String> list) {
        mContext = context;
        mList = list;

        initView();
        initLisenter();
    }

    /**
     * @param context
     * @param list
     * @param isFullScreen 是否全屏，默认全屏为（true） false 不全屏
     */
    public PopupWindowCheckSingleChoose(Activity context, ArrayList<String> list, boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
        mContext = context;
        mList = list;

        initView();
        initLisenter();
    }

    /**
     * 展示Pop
     *
     * @param localAt
     */
    public void showPopBottom(View localAt) {
        if (!this.isShowing()) {
            // 设置背景颜色变暗
            WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
            lp.alpha = 0.7f;
            mContext.getWindow().setAttributes(lp);

            showAtLocation(localAt, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        } else {
            this.dismiss();
        }
    }

    /**
     * 展示Pop
     * 在某个view的底部
     *
     * @param localAt
     */
    public void showPopViewBottom(View localAt) {
        if (!this.isShowing()) {
            // 设置背景颜色变暗
//            WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
//            lp.alpha = 0.7f;
//            mContext.getWindow().setAttributes(lp);

            showAsDropDown(localAt, 1, 0);
        } else {
            this.dismiss();
        }
    }

    /**
     * 展示Pop
     * 在某个view的底部
     *
     * @param localAt
     */
    public void showSizePopViewBottom(View localAt, int x, int y) {
        if (!this.isShowing()) {
            showAsDropDown(localAt, x, y);
        } else {
            this.dismiss();
        }
    }

    public void showSizePopViewBottom(View localAt) {
        if (!this.isShowing()) {
            showAsDropDown(localAt, -30, 0);
        } else {
            this.dismiss();
        }
    }

    @Override
    public void dismiss() {
        if (this != null && this.isShowing()) {
            super.dismiss();
            //消失后，背景变回原来的颜色
            WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
            lp.alpha = 1f;
            mContext.getWindow().setAttributes(lp);
        }
    }

    /**
     * 初始化控件
     */
    public void initView() {
        if (isFullScreen) {
            mView = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.lay_pop_single_choose, null);
            mAdapter = new MyCheckAdapter(mContext, true);
            this.setContentView(mView);
            this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        } else {
            mView = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.lay_pop_single_choose2, null);
            mAdapter = new MyCheckAdapter(mContext, false);
            this.setContentView(mView);
            this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        }


        this.setFocusable(true);
        this.setAnimationStyle(R.style.pop_anim_style);
        this.setBackgroundDrawable(new ColorDrawable(mContext.getResources().getColor(R.color.color_1)));

        mTvTag = mView.findViewById(R.id.tv_tag);
        recyclerView = mView.findViewById(R.id.lv_list);
        mTvButtom = mView.findViewById(R.id.tv_buttom);


        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setNewData(mList);
    }

    /**
     * 对控件设置监听事件
     */
    public void initLisenter() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String item = (String) adapter.getData().get(position);
                mLisenter.onItemClick(item);
            }
        });
        mTvButtom.setOnClickListener(v -> {
            if (mLisenter != null) {
                mLisenter.onClickButtom();
                dismiss();
            }
        });
    }

    public interface onEventLisenter {
        void onItemClick(String position);//返回选中item的位置集合

        default void onClickButtom() {
        }//底部按钮点击事件
    }

    public void setOnEventLisenter(onEventLisenter lisenter) {
        mLisenter = lisenter;
    }


    class MyCheckAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        private Context mContext;
        private boolean isFullScreen = true;

        public MyCheckAdapter(Context context) {
            super(R.layout.item_singel_choose_lay);
            this.mContext = context;
        }

        public MyCheckAdapter(Context context, boolean isFullScreen) {
            super(isFullScreen ? R.layout.item_singel_choose_lay : R.layout.item_singel_choose_lay2);
            this.mContext = context;
            this.isFullScreen = isFullScreen;
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.item_tv_view, item);
            if (itemText != null) {
                if (isFullScreen) {
                    if (item.equals(itemText)) {
                        helper.setImageResource(R.id.item_iv_view, R.mipmap.ic_checked);
                    } else {
                        helper.setImageResource(R.id.item_iv_view, R.mipmap.ic_unchecked);
                    }
                } else {
                    if (item.equals(itemText)) {
                        helper.setBackgroundColor(R.id.item_tv_view, mContext.getResources().getColor(R.color.FFC134));
                    } else {
                        helper.setBackgroundColor(R.id.item_tv_view, mContext.getResources().getColor(R.color.white));
                    }
                }
            }
        }
    }
}
