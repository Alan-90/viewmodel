package www.hbj.cloud.platform.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import www.hbj.cloud.baselibrary.common.base.fragment.BaseFragment;
import www.hbj.cloud.baselibrary.common.widget.popupwindow.PopupWindowCheckSingleChoose;
import www.hbj.cloud.platform.R;
import www.hbj.cloud.platform.databinding.FragmentHomeBinding;

/**
 * @author Alan-kun
 * @date 2020/12/21.
 * description：
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeModel> {


    private PopupWindowCheckSingleChoose mPopup;
    private PopupWindowCheckSingleChoose mPopup2;
    private ArrayList<String> mList = new ArrayList();

    @Override
    protected FragmentHomeBinding bindingView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentHomeBinding.inflate(inflater, container, false);
    }

    @Override
    protected Class VMClass() {
        return HomeModel.class;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {

        initData();
    }

    private void initData() {
        mList = getPopList();
        mPopup = new PopupWindowCheckSingleChoose(getActivity(), mList,true);
        mPopup2 = new PopupWindowCheckSingleChoose(getActivity(), mList,false);
        mPopup.setButtomTxt(getString(R.string.cancel))
                .isShowTopTagText(false);//设置底部按钮内容
        mPopup2.setButtomTxt(getString(R.string.cancel))
                .isShowTopTagText(false);//设置底部按钮内容
        binding.tvName.setOnClickListener(v -> {
                    mPopup.setItemViewText(binding.tvName.getText().toString()).showSizePopViewBottom(binding.tvName);
                }
        );
        binding.tvName2.setOnClickListener(v -> {
                    mPopup2.setItemViewText(binding.tvName2.getText().toString()).showSizePopViewBottom(binding.tvName2);
                }
        );
        //单选
        mPopup.setOnEventLisenter(data -> {
            mPopup.dismiss();
            binding.tvName.setText(data);
        });
        //单选
        mPopup2.setOnEventLisenter(data -> {
            mPopup2.dismiss();
            binding.tvName2.setText(data);
        });
    }

    /**
     * 数据
     *
     * @return
     */
    public ArrayList<String> getPopList() {
        ArrayList<String> popList = new ArrayList<>();
        popList.add(getString(R.string.reason_draw_back_place_choose));
        popList.add(getString(R.string.reason_draw_back_buy_more));
        popList.add(getString(R.string.reason_draw_back_not_receive));
        popList.add(getString(R.string.reason_draw_back_its_fake));
        popList.add(getString(R.string.reason_draw_back_buy_error));
        popList.add(getString(R.string.reason_draw_back_type_error));
        popList.add(getString(R.string.reason_draw_back_quality_problem));
        popList.add(getString(R.string.reason_draw_back_deal));
        popList.add(getString(R.string.reason_draw_back_no_info_of_logistics));
        popList.add(getString(R.string.reason_draw_back_no_goods));
        popList.add(getString(R.string.reason_draw_back_another_reason));
        return popList;
    }
}
