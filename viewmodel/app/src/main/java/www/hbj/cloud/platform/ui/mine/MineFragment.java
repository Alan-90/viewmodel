package www.hbj.cloud.platform.ui.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import www.hbj.cloud.baselibrary.common.base.fragment.BaseFragment;
import www.hbj.cloud.baselibrary.common.widget.popupwindow.PopupWindowCheckSingleChoose;
import www.hbj.cloud.baselibrary.common.widget.popupwindow.PopupWindowMoreChoose;
import www.hbj.cloud.platform.R;
import www.hbj.cloud.platform.databinding.FragmentMineBinding;

/**
 * @author Alan-kun
 * @date 2020/12/21.
 * description：
 */
public class MineFragment extends BaseFragment<FragmentMineBinding, MineModel> {

    private PopupWindowMoreChoose mPopup;
    private PopupWindowMoreChoose mPopup2;
    private ArrayList<PopupWindowMoreChoose.ChooseBean> mList = new ArrayList();
    private ArrayList<PopupWindowMoreChoose.ChooseBean> mList2 = new ArrayList();

    @Override
    protected FragmentMineBinding bindingView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMineBinding.inflate(inflater, container, false);
    }

    @Override
    protected Class VMClass() {
        return MineModel.class;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        binding.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mList = getPopList();
        mList2 = getPopList2();
        mPopup = new PopupWindowMoreChoose(getActivity(), mList,true);
        mPopup2 = new PopupWindowMoreChoose(getActivity(), mList2,false);
        mPopup.setCancelText(getString(R.string.cancel))
                .isShowTopTagText(false)
                .isRestrict(true)//
                .setNumber(1);//与isRestrict（为true设置数字才起作用）联合使用 最小为2 小于1无意义 mPopup = new PopupWindowMoreChoose(getActivity(), mList);
        mPopup2.setCancelText(getString(R.string.cancel))
                .isShowTopTagText(false)
                .isRestrict(true)//
                .setNumber(4);//与isRestrict（为true设置数字才起作用）联合使用 最小为2 小于1无意义

        binding.tvName.setOnClickListener(v -> {
                    mPopup.showPopViewBottom(binding.tvName);
                }
        );
        binding.tvName2.setOnClickListener(v -> {
                    mPopup2.showSizePopViewBottom(binding.tvName2);
                }
        );

        mPopup.setOnEventListener(data -> {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < data.length; i++) {
                if (!TextUtils.isEmpty(data[i])) {
                    stringBuffer.append(data[i]);
                }
            }
            binding.tvName.setText(TextUtils.isEmpty(stringBuffer) ? "请选择" : stringBuffer);
        });
        mPopup2.setOnEventListener(data -> {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < data.length; i++) {
                if (!TextUtils.isEmpty(data[i])) {
                    stringBuffer.append(data[i]);
                }
            }
            binding.tvName2.setText(TextUtils.isEmpty(stringBuffer) ? "请选择" : stringBuffer);
        });
    }

    /**
     * 数据
     *
     * @return
     */
    public ArrayList<PopupWindowMoreChoose.ChooseBean> getPopList() {
        ArrayList<PopupWindowMoreChoose.ChooseBean> popList = new ArrayList<>();
        PopupWindowMoreChoose.ChooseBean bean;
        for (int i = 0; i < 10; i++) {
            bean = new PopupWindowMoreChoose.ChooseBean();
            bean.setItemString("haha" + i);
            popList.add(bean);
        }
        return popList;
    }

    /**
     * 数据
     *
     * @return
     */
    public ArrayList<PopupWindowMoreChoose.ChooseBean> getPopList2() {
        ArrayList<PopupWindowMoreChoose.ChooseBean> popList = new ArrayList<>();
        PopupWindowMoreChoose.ChooseBean bean;
        for (int i = 0; i < 10; i++) {
            bean = new PopupWindowMoreChoose.ChooseBean();
            bean.setItemString("haha" + i);
            popList.add(bean);
        }
        return popList;
    }

}
