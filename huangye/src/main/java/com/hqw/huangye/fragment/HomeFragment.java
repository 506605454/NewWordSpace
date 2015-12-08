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
import android.widget.ImageView;
import android.widget.Toast;

import com.hqw.huangye.R;
import com.hqw.huangye.activity.MainActivity;
import com.hqw.huangye.adapter.MyFragmentPagerAdapter;
import com.hqw.huangye.bean.Home;
import com.hqw.huangye.util.Constants;
import com.hqw.huangye.view.CircularProgress;
import com.hqw.huangye.view.MyViewPager;
import com.litesuits.common.assist.Toastor;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.callback.ResultCallback;
import com.zhy.http.okhttp.request.OkHttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/25.
 */
public class HomeFragment extends Fragment {

    private ArrayList<Fragment> pagerItemList = new ArrayList<Fragment>();
    private MyAdapter mAdapter;
    private MyViewPager mPager;
    private MyFragmentPagerAdapter fragmentPagerAdapter;
    private CircularProgress mCircularProgress;
    private ImageView mShareView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("hei", "HomeFragment onCreate 被执行");


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initView(inflater);
        //initViewPager();
        pullServerData();
        return view;
    }

    //获取服务器数据
    private void pullServerData() {
        String url = Constants.BASE_URL + "/page/two/getHome.do";
        Log.i("hei", "home可见" + getClass());
        new OkHttpRequest.Builder()
                .url(url)
                .get(new MyResultCallback<List<Home>>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e("hei", "onError , e = " + e.getMessage());
                    }

                    @Override
                    public void onResponse(List<Home> homeList) {
                        Log.i("hei", "onResponse , Home = " + homeList.toString());

                        initViewPager(homeList);



                    }
                });
    }

    //改变页面状态 隐藏 进度圈圈
    private void changeShowStatus() {
        mPager.setVisibility(View.VISIBLE);
        mCircularProgress.setVisibility(View.GONE);
    }

    // 初始化view
    private View initView(LayoutInflater inflater) {

        View mView = inflater.inflate(R.layout.home_fragment, null);
        mPager = (MyViewPager) mView.findViewById(R.id.home_pager);
        mCircularProgress = (CircularProgress) mView.findViewById(R.id.progress_bar);
        mShareView = (ImageView) getActivity().findViewById(R.id.share_img);
        mShareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toastor toastor = new Toastor(getActivity());
                toastor.showToast("点击了");
            }
        });
        return mView;
    }


    // 初始化viewPage
    private void initViewPager(List<Home> homeList) {

        pagerItemList.clear();
        for (int i = 0; i < homeList.size(); i++) {
            Bundle data = new Bundle();
            data.putParcelable("home",homeList.get(i));
            data.putString("text", i + "");
            HomeViewPageFragment fragment = new HomeViewPageFragment();
            fragment.setArguments(data);
            pagerItemList.add(fragment);
        }

        mAdapter = new MyAdapter(getChildFragmentManager());
        fragmentPagerAdapter = new MyFragmentPagerAdapter(
                getChildFragmentManager(), pagerItemList);

        mPager.setAdapter(fragmentPagerAdapter);
        mPager.setOnPageChangeListener(new MyPageChangeListener());
        if (((MainActivity) getActivity()).mSaveTemp.get("home") != null) {
            mPager.setCurrentItem(((MainActivity) getActivity()).mSaveTemp.get("home"));
        } else {
            mPager.setCurrentItem(0);
        }

        // 设置 页面数 （页面回弹效果）
        mPager.setpagerCount(pagerItemList.size());
        mPager.setGetPageListenter(new MyViewPager.IGetPageListenter() {

            //当滑动到第一页进行刷新的时候
            @Override
            public void getNewPage() {
                Toast.makeText(getActivity(), "新，没有更多了 ", Toast.LENGTH_SHORT).show();
            }

            //当滑动到最后一页
            @Override
            public void getOldPage() {
                Toast.makeText(getActivity(), "旧，没有更多了", Toast.LENGTH_SHORT).show();

            }
        });

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
            mPager.setCurrentIndex(position);
            Log.i("hei", "onPageSelected " + position);
            ((MainActivity) getActivity()).mSaveTemp.put("home", position);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("hei", "home onDestroy");
    }

    // 回调公共类
    public abstract class MyResultCallback<T> extends ResultCallback<T> {

        //之前
        @Override
        public void onBefore(Request request) {
            super.onBefore(request);
            //  setTitle("loading...");
        }

        //之后
        @Override
        public void onAfter() {
            super.onAfter();
            //   setTitle("Sample-okHttp");
            changeShowStatus();
        }


    }


}
