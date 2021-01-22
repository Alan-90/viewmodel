package www.hbj.cloud.baselibrary.common.widget.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import www.hbj.cloud.baselibrary.R;
import www.hbj.cloud.baselibrary.common.utils.ToastUtils;

/**
 * 多选列表
 *
 * @author Alan-kun
 * @date 2021/1/8.
 * description：
 */
public class PopupWindowMoreChoose extends PopupWindow {

    private Activity mContext;

    /**
     * 列表和按钮的监听事件
     */
    private onEventListener mListener;

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
    private TextView mTvCancel;
    /**
     * 底部按钮
     */
    private TextView mTvOk;
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
    private String mCancelTxt;
    /**
     * 底部按钮内容
     */
    private String mOkTxt;

    /**
     * 数据源
     */
    private ArrayList<ChooseBean> mList = new ArrayList<>();
    /**
     * 适配器
     */
    private MyCheckAdapter mAdapter;

    private boolean isFullScreen = true;//横坐标是否全屏 默认全屏

    /**
     * 限制为几项
     */
    private boolean isRestrict;//是否限定为几项，true 限制 false 不限制
    private int number = 2;//与isRestrict（为true设置数字才起作用）联合使用 最小为2 小于等于1无意义

    public PopupWindowMoreChoose(Activity context, ArrayList<ChooseBean> list, boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
        mContext = context;
        if (mList != null) {
            mList.clear();
        }
        mList = list;

        initView();
        initListener();
    }

    /**
     * 初始化控件
     */
    public void initView() {
        if (isFullScreen) {
            mView = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.lay_pop_more_choose, null);
            this.setContentView(mView);
            this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        } else {
            mView = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.lay_pop_more_choose2, null);
            this.setContentView(mView);
            this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        }


        this.setFocusable(true);
        this.setAnimationStyle(R.style.pop_anim_style);
        this.setBackgroundDrawable(new ColorDrawable(mContext.getResources().getColor(R.color.color_1)));

        mTvTag = mView.findViewById(R.id.tv_tag);
        recyclerView = mView.findViewById(R.id.lv_list);
        if (isFullScreen) {
            mTvCancel = mView.findViewById(R.id.tv_cancel);
        }
        mTvOk = mView.findViewById(R.id.tv_ok);

        mAdapter = new MyCheckAdapter(mContext, mList, isFullScreen);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setNewData(mList);
    }


    public void initListener() {
        stringList = new String[mList.size()];//
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                setFullScreen(view, position);
            }
        });
        if (isFullScreen) {
            mTvCancel.setOnClickListener(v -> {
                if (mListener != null) {
                    dismiss();
                }
            });
        }
        mTvOk.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onClickOk(stringList);
                dismiss();
            }
        });


    }

    private void setFullScreen(View view, int position) {
        CheckBox checkBox = view.findViewById(R.id.item_cb_view);
        if (checkBox.isChecked()) {
            mList.get(position).setCheck(false);
            mAdapter.notifyDataSetChanged();
            if (count > 0) {
                count--;
                if (!mList.get(position).isCheck) {
                    stringList[position] = "";
                } else {
                    stringList[position] = mList.get(position).getItemString();
                }
            }
        } else {
            if (isRestrict) {
                if (count >= number) {
                    ToastUtils.showToast("最多选择" + number + "项");
                    return;
                }
            }

            count++;
            mList.get(position).setCheck(true);
            mAdapter.notifyDataSetChanged();
            if (mList.get(position).isCheck) {
                stringList[position] = mList.get(position).getItemString();
            } else {
                stringList[position] = "";
            }
        }
    }


    public void showSizePopViewBottom(View localAt) {
        if (!this.isShowing()) {
            showAsDropDown(localAt, -30, 0);
        } else {
            this.dismiss();
        }
    }

    /**
     * 设置顶部内容区域是否显示
     *
     * @param isShow
     * @return
     */
    public PopupWindowMoreChoose isShowTopTagText(boolean isShow) {
        if (isShow) {
            mTvTag.setVisibility(View.VISIBLE);
        } else {
            mTvTag.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 是否限制选择的个数
     *
     * @param isRestrict
     * @return
     */
    public PopupWindowMoreChoose isRestrict(boolean isRestrict) {
        this.isRestrict = isRestrict;
        return this;
    }

    /**
     * 限制为几条
     *
     * @param number 限制最小为2
     * @return
     */
    public PopupWindowMoreChoose setNumber(int number) {
        if (number < 2) {
            this.number = 2;
        } else {
            this.number = number;
        }
        return this;
    }

    /**
     * 设置顶部内容
     *
     * @param tagTxt
     * @return
     */
    public PopupWindowMoreChoose setTagTxt(String tagTxt) {
        mTagTxt = tagTxt;
        if (mTvTag != null) {
            mTvTag.setText(mTagTxt);
        }
        return this;
    }


    /**
     * 设置底部按钮内容
     *
     * @param cancelText
     * @return
     */
    public PopupWindowMoreChoose setCancelText(String cancelText) {
        mCancelTxt = cancelText;
        if (mTvCancel != null) {
            mTvCancel.setText(mCancelTxt);
        }
        return this;
    }

    /**
     * 设置底部按钮内容
     *
     * @param okTxt
     * @return
     */
    public PopupWindowMoreChoose setOkTxt(String okTxt) {
        mOkTxt = okTxt;
        if (mTvOk != null) {
            mTvOk.setText(okTxt);
        }
        return this;
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
            showAsDropDown(localAt);
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
     * 对控件设置监听事件
     */
    private String[] stringList;
    private int count;


    public interface onEventListener {
        void onClickOk(String[] item);
    }

    public void setOnEventListener(onEventListener Listener) {
        mListener = Listener;
    }


    class MyCheckAdapter extends BaseQuickAdapter<ChooseBean, BaseViewHolder> {
        //这个是checkbox的Hashmap集合
        private HashMap<Integer, Boolean> map = new HashMap<>();
        private List<ChooseBean> list;
        private boolean isFullScreen;

        private Context mContext;

        public MyCheckAdapter(Context context, List<ChooseBean> list, boolean isFullScreen) {
            super(isFullScreen ? R.layout.item_more_choose_lay : R.layout.item_more_choose_lay2);
            this.isFullScreen = isFullScreen;
            this.mContext = context;
            this.list = list;
            map.clear();
            for (int i = 0; i < list.size(); i++) {
                map.put(i, false);
            }
        }

        @Override
        protected void convert(BaseViewHolder helper, ChooseBean item) {
            helper.setText(R.id.item_tv_view, item.getItemString());
            CheckBox checkBox = helper.getView(R.id.item_cb_view);
            checkBox.setChecked(item.isCheck());
            if (!isFullScreen) {
                if (checkBox.isChecked()) {
                    helper.setBackgroundColor(R.id.item_tv_view, mContext.getResources().getColor(R.color.FFC134));
                } else {
                    helper.setBackgroundColor(R.id.item_tv_view, mContext.getResources().getColor(R.color.white));
                }
            }
            helper.addOnClickListener(R.id.rel_item_view);
        }
    }

    public static class ChooseBean {
        boolean isCheck;
        String itemString;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public String getItemString() {
            return itemString;
        }

        public void setItemString(String itemString) {
            this.itemString = itemString;
        }
    }
}
