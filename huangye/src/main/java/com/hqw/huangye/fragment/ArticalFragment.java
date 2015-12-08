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
import android.widget.Toast;

import com.hqw.huangye.R;
import com.hqw.huangye.adapter.MyFragmentPagerAdapter;
import com.hqw.huangye.bean.Artical;
import com.hqw.huangye.util.Constants;
import com.hqw.huangye.view.CircularProgress;
import com.hqw.huangye.view.MyViewPager;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.callback.ResultCallback;
import com.zhy.http.okhttp.request.OkHttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/25.
 */
public class ArticalFragment extends Fragment {

    private ArrayList<Fragment> pagerItemList = new ArrayList<Fragment>();
    private MyAdapter mAdapter;
    private MyViewPager mArticalPager;
    private MyFragmentPagerAdapter fragmentPagerAdapter;
    private CircularProgress mCircularProgress;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("hei", "ArticalFragment onCreate 被执行");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initView(inflater);
        //initViewPager();
        pullServerData();
        return view;
    }

    private void pullServerData() {
        String url = Constants.BASE_URL + "/page/two/getArtical.do";
        Log.i("hei", "Artical可见" + getClass());
        new OkHttpRequest.Builder()
                .url(url)
                .get(new MyResultCallback<List<Artical>>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e("hei", "onError , e = " + e.getMessage());
                    }

                    @Override
                    public void onResponse(List<Artical> articalList) {
                        Log.i("hei", "onResponse , articalList = " + articalList.toString());

                        initViewPager(articalList);



                    }
                });
    }

    private View initView(LayoutInflater inflater) {

        View mView = inflater.inflate(R.layout.artical_fragment, null);
        mArticalPager = (MyViewPager) mView.findViewById(R.id.artical_pager);
        mCircularProgress = (CircularProgress) mView.findViewById(R.id.progress_bar);
        return mView;
    }

    private void initViewPager(List<Artical> articalList) {

        pagerItemList.clear();
        for (int i = 0; i < articalList.size(); i++) {
            Bundle data = new Bundle();
            data.putParcelable("artical",articalList.get(i));
            data.putString("text", i + "");
            ArticalViewPageFragment fragment = new ArticalViewPageFragment();
            fragment.setArguments(data);
            pagerItemList.add(fragment);
        }

        fragmentPagerAdapter = new MyFragmentPagerAdapter(
                getChildFragmentManager(), pagerItemList);
        mArticalPager.setAdapter(fragmentPagerAdapter);
        mArticalPager.setOnPageChangeListener(new MyPageChangeListener());
        mArticalPager.setCurrentItem(0);
        mArticalPager.setpagerCount(pagerItemList.size());
        mArticalPager.setGetPageListenter(new MyViewPager.IGetPageListenter() {

            @Override
            public void getNewPage() {
                Toast.makeText(getActivity(), "新，没有更多了 ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void getOldPage() {
                Toast.makeText(getActivity(), "旧，没有更多了", Toast.LENGTH_SHORT).show();

            }
        });
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

            Log.i("hei", "onPageScrollStateChanged  ==>>" + state);

        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
            Log.i("hei", "onPageScrolled");
        }

        @Override
        public void onPageSelected(int position) {
            mArticalPager.setCurrentIndex(position);
            Log.i("hei", "onPageSelected");
        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.i("hei", "主home可见");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("hei", "onPause");
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

    //改变页面状态 隐藏 进度圈圈
    private void changeShowStatus() {
        mArticalPager.setVisibility(View.VISIBLE);
        mCircularProgress.setVisibility(View.GONE);
    }
}
