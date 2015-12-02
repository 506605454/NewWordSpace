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
import com.hqw.huangye.activity.MainActivity;
import com.hqw.huangye.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/11/25.
 */
public class ArticalFragment extends Fragment {

    private ArrayList<Fragment> pagerItemList = new ArrayList<Fragment>();
    private MyAdapter mAdapter;
    private ViewPager mArticalPager;
    private MyFragmentPagerAdapter fragmentPagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("hei", "ArticalFragment onCreate 被执行");
        pagerItemList.clear();
        for (int i = 0; i < 7; i++) {
            Bundle data = new Bundle();
            data.putString("text", i + "");
            ArticalViewPageFragment fragment = new ArticalViewPageFragment();
            fragment.setArguments(data);
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

        View mView = inflater.inflate(R.layout.artical_fragment, null);
        mArticalPager = (ViewPager) mView.findViewById(R.id.artical_pager);
        return mView;
    }

    private void initViewPager() {



        mAdapter = new MyAdapter(getChildFragmentManager());
        fragmentPagerAdapter = new MyFragmentPagerAdapter(
                getChildFragmentManager(), pagerItemList);
        mArticalPager.setAdapter(fragmentPagerAdapter);
        mArticalPager.setOnPageChangeListener(new MyPageChangeListener());


        mArticalPager.setCurrentItem(0);

        Log.i("hei", mArticalPager.getAdapter().getCount() + "artical获取的长度");
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
            Log.i("hei","onPageScrollStateChanged");
        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
            Log.i("hei","onPageScrolled");
        }

        @Override
        public void onPageSelected(int position) {
            Log.i("hei","onPageSelected");
        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            Log.i("hei","主home可见");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("hei", "onPause");
    }
}
