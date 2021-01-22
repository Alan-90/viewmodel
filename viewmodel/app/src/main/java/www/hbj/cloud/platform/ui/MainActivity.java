package www.hbj.cloud.platform.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import www.hbj.cloud.baselibrary.common.base.Header;
import www.hbj.cloud.baselibrary.common.base.activity.BaseTitleActivity;
import www.hbj.cloud.baselibrary.common.base.fragment.BaseFragment;
import www.hbj.cloud.baselibrary.common.widget.home.BottomBarLayout;
import www.hbj.cloud.platform.databinding.ActivityMainBinding;
import www.hbj.cloud.platform.ui.home.HomeFragment;
import www.hbj.cloud.platform.ui.mine.MineFragment;
import www.hbj.cloud.platform.ui.notifications.NotificationsFragment;

@Route(path = "/main/mainactivity")
public class MainActivity extends BaseTitleActivity<ActivityMainBinding, MainModel> {
    public static final String KEY_INDEX = "index";

    private List<BaseFragment> mFragments;
    private MainTabAdapter mTabAdapter;
    public final static int TAB_HOME = 0;
    public final static int TAB_MSG = 1;
    public final static int TAB_MINE = 2;
    private int selectedIndex = 0;

    @Override
    protected ActivityMainBinding bindingView() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected Class VMClass() {
        return MainModel.class;
    }

    @Override
    public Header onCreateHeader(Header.Builder builder) {
        return null;
    }


    @SuppressLint("CheckResult")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void initView() {
        viewModel.test("鑫航国际物流");


        viewModel.liveData.observe(this, baseObjectBeanResult -> {
            if(baseObjectBeanResult.isSuccess()){

            }
            if (baseObjectBeanResult.isFailed()){
                baseObjectBeanResult.getMessage();
            }else{
                baseObjectBeanResult.getData();
            }
        });
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new NotificationsFragment());
        mFragments.add(new MineFragment());

        mTabAdapter = new MainTabAdapter(mFragments, getSupportFragmentManager());
        binding.vpContent.setAdapter(mTabAdapter);
        binding.vpContent.setOffscreenPageLimit(mFragments.size());
        binding.bottomBar.setCurrentTab(0);

        initTabTvColor();

        initListener();


        viewModel.liveData.observe(this, liveData -> {
//            setEmptyView(R.mipmap.ic_empty,"没有数据哦~~！！",R.color.c_053ce4);
//            setEmptyView();
        });

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int tabType = intent.getIntExtra(KEY_INDEX, 0);
        setTab(tabType);
    }

    public void setTab(int tabType) {
        if (tabType == TAB_MSG) {
            binding.bottomBar.setCurrentTab(TAB_MSG);
            selectedIndex = TAB_MSG;
        }  else if (tabType == TAB_MINE) {
            binding.bottomBar.setCurrentTab(TAB_MINE);
            selectedIndex = TAB_MINE;
        } else {
            binding.bottomBar.setCurrentTab(TAB_HOME);
            selectedIndex = TAB_HOME;
        }
        initTabTvColor();
    }
    private void initTabTvColor() {
        binding.homeTv.setEnabled(selectedIndex == TAB_HOME);
        binding.notificationTv.setEnabled(selectedIndex == TAB_MSG);
        binding.mineTv.setEnabled(selectedIndex == TAB_MINE);
    }


    public void initListener() {
        binding.bottomBar.setTabSelectionListener(mOnTabSelectionChanged);
        binding.bottomBar.setTabSelectionCheckListener(mOnTabSelectionCheck);

    }
    private BottomBarLayout.OnTabSelectionChanged mOnTabSelectionChanged = index -> {
        selectedIndex = index;
        binding.vpContent.setCurrentItem(index);
        initTabTvColor();
    };
    private BottomBarLayout.OnTabSelectionCheck mOnTabSelectionCheck = index -> true;

    public class MainTabAdapter extends FragmentStatePagerAdapter {
        private List<BaseFragment> mFragments = new ArrayList<>();

        public MainTabAdapter(List<BaseFragment> fragmentList, FragmentManager fm) {
            super(fm);

            if (fragmentList != null){
                mFragments = fragmentList;
            }
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}