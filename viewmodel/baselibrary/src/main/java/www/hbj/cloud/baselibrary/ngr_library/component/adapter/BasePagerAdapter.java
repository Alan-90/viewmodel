package www.hbj.cloud.baselibrary.ngr_library.component.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * BasePagerAdapter
 */
public  class BasePagerAdapter extends FragmentStatePagerAdapter {
    private Activity mContext;
    ArrayList<Class<? extends Fragment>> mListClass = new ArrayList<>();
    ArrayList<String> mTitles = new ArrayList<>();
    public HashMap<Integer, Fragment> mFragments = new HashMap<>();
    ArrayList<Bundle> mBundles;

    public BasePagerAdapter(FragmentManager fm, ViewPager viewPager, ArrayList<Class<? extends Fragment>> list) {
        this(fm,viewPager,list,null,null);
    }

    public BasePagerAdapter(FragmentManager fm, ViewPager viewPager, ArrayList<Class<? extends Fragment>> list,
                            ArrayList<String> titles, ArrayList<Bundle> bundles) {
        super(fm);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Fragment f = mFragments.get(i);
                if (f != null) {
                    f.setUserVisibleHint(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        Context c =  viewPager.getContext();
        if (c instanceof Activity) {
            mContext = (Activity)c;
        }
        this.mListClass = list;
        this.mTitles = titles;
    }

    public BasePagerAdapter(FragmentManager fm, ViewPager viewPager, ArrayList<Class<? extends Fragment>> list,
                            ArrayList<String> titles) {
        this(fm,viewPager,list,titles,null);

    }

    public void setBundles(ArrayList<Bundle> bundles) {
        this.mBundles = bundles;
    }

    public Fragment getFragentByPosition(int position) {
        return mFragments.get(position);
    }
    
    @Override
    public Fragment getItem(int position) {
        Bundle b = mContext.getIntent().getExtras();
        if (mFragments.get(position) != null) {
            return mFragments.get(position);
        }
        if (b == null) {
            b = new Bundle();
        }
        b.putInt("position", position);
        if (mBundles != null && position < mBundles.size()) {
            b.putAll(mBundles.get(position));
        }
        Fragment fragment = Fragment.instantiate(mContext, mListClass.get(position).getName(),b);
        mFragments.put(position,fragment);
        return fragment;
    }
    
    @Override
    public int getCount() {
        return mListClass.size();
    }

    boolean send;

    @Override
    public void setPrimaryItem(ViewGroup container, final int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        if (position == viewPager.getCurrentItem() && !send) {
            viewPager.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (getFragentByPosition(position) != null) {
                        getFragentByPosition(position).setUserVisibleHint(true);
                    }
                }
            },200);
            send = true;
        }
//        super.setPrimaryItem(container,position,object);
    }
    
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container,position,object);
    }
    
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
    
    public void setPageTitle(String title, int position) {
        if (mTitles != null && position < mTitles.size()) {
            mTitles.set(position, title);
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}