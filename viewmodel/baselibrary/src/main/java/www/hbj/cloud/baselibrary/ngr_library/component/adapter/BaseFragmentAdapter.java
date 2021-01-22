package www.hbj.cloud.baselibrary.ngr_library.component.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author JokerX
 * @version V1.0
 * @Package com.aidai.net.base
 * @Description: 公共的Fragment适配器，主要用于Tab指示器
 * @date 2016/3/28 9:36
 */
public class BaseFragmentAdapter extends FragmentPagerAdapter {

    /**
     * viewpager中的fragment
     */
    private List<Fragment> mFragments;
    /**
     * 每个fragment的标题
     */
    private String[] mTitles;
    private List<String> mTitleList;

    public BaseFragmentAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);
        this.mFragments = fragments;
        this.mTitles = titles;
    }

    public BaseFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.mFragments = fragments;
        this.mTitleList = titles;
    }

    public void setData(List<Fragment> fragments, String[] titles) {
        mFragments = fragments;
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if (null != mTitleList) {
            return mTitleList.get(position);
        }
        return mTitles[position];
    }
}
