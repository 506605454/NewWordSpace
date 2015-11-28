package com.hqw.huangye.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hqw.huangye.R;
import com.hqw.huangye.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/11/25.
 */
public class HomeFragment extends Fragment {

    private ArrayList<Fragment> pagerItemList = new ArrayList<Fragment>();
    private MyAdapter mAdapter;
    private ViewPager mPager;
    private MyFragmentPagerAdapter fragmentPagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("hei","HomeFragment onCreate 被执行" );
        pagerItemList.clear();
        for (int i = 0; i < 7; i++) {
            HomeViewPageFragment fragment = new HomeViewPageFragment();
            pagerItemList.add(fragment);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initView(inflater);

        initViewPager();
        return view;
    }

    private View initView(LayoutInflater inflater) {

        View mView = inflater.inflate(R.layout.home_fragment, null);
        mPager = (ViewPager) mView.findViewById(R.id.pager);
        return mView;
    }


    private void initViewPager() {



        mAdapter = new MyAdapter(getChildFragmentManager());
        fragmentPagerAdapter = new MyFragmentPagerAdapter(
                getChildFragmentManager(), pagerItemList);

        mPager.setAdapter(fragmentPagerAdapter);
        mPager.setOnPageChangeListener(new MyPageChangeListener());
        mPager.setCurrentItem(0);
        Log.i("hei", mPager.getAdapter().getCount() + "home获取的长度");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return pagerItemList.size();
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;
            if (position < pagerItemList.size())
                fragment = pagerItemList.get(position);
            else
                fragment = pagerItemList.get(0);

            return fragment;

        }
    }

    class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("hei","home onDestroy");
    }
}
