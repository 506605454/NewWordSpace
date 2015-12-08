package com.hqw.huangye.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hqw.huangye.R;
import com.hqw.huangye.bean.Home;
import com.hqw.huangye.util.Constants;
import com.hqw.huangye.view.CircularProgress;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.callback.ResultCallback;
import com.zhy.http.okhttp.request.OkHttpRequest;

import java.util.Date;
import java.util.Objects;

/**
 * Created by Administrator on 2015/11/25.
 */
public class HomeViewPageFragment extends Fragment {

    private TextView textView;
    private String mWhat;
    private Home mHome;
    private TextView mVersion;
    private TextView mDay;
    private TextView mMonth;
    private TextView mYearh;
    private TextView mContent;
    private TextView mTittle;
    private TextView mSaygood;
    private TextView mAuthor;
    private ImageView mHomeImg;

    private CircularProgress mCircularProgress;
    private LinearLayout mMainBody;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();
        mHome = args.getParcelable("home");

//        mWhat = args != null ? args.getString("home") : "";
//        if (mWhat!=null){
//            Gson gson = new Gson();
//            mHome = gson.fromJson(mWhat,Home.class);
//        }

       // Log.i("hei","被创建了 mHome"+ mHome.toString() );
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_viewpage_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        mContent = (TextView) view.findViewById(R.id.home_content);
        mVersion = (TextView) view.findViewById(R.id.version_log);
        mDay = (TextView) view.findViewById(R.id.day_tv);
        mMonth = (TextView) view.findViewById(R.id.month_tv);
        mYearh = (TextView) view.findViewById(R.id.year_tv);
        mTittle = (TextView) view.findViewById(R.id.image_info);
        mSaygood = (TextView) view.findViewById(R.id.saygood_tv);
        mAuthor = (TextView) view.findViewById(R.id.author_tv);
        mHomeImg = (ImageView) view.findViewById(R.id.home_img);
        mCircularProgress = (CircularProgress) view.findViewById(R.id.progress_bar);
        mMainBody = (LinearLayout) view.findViewById(R.id.main_body);
        showData(mHome);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("hei", "homepage被摧毁了");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //Toast.makeText(getActivity(),mWhat+"",Toast.LENGTH_LONG).show();
            Log.i("hei", "home可见" + getClass());

            //showData(mHome);

        }
    }



    private void showData(Home home) {
        String url = Constants.BASE_URL+mHome.getImgurl();
        Log.i("home",mHome.getVersion()+"");
        Date date = new Date(mHome.getCreateTime());
        mVersion.setText(mHome.getVersion()+"");
        mDay.setText(date.getDay()+"");
        mMonth.setText(date.getMonth()+"");
        mYearh.setText(date.getYear()+"");
        mContent.setText(mHome.getComment());
        mTittle.setText(mHome.getTitle());
        mSaygood.setText(mHome.getSaygood()+"");
        mAuthor.setText(mHome.getAuthor());
        //基本
         new OkHttpRequest.Builder() .url(url).imageView(mHomeImg).displayImage(new MyResultCallback<Object>() {
             @Override
             public void onError(Request request, Exception e) {

             }

             @Override
             public void onResponse(Object response) {

             }
         });



    }

    private void changeShowStatus() {
        mCircularProgress.setVisibility(View.GONE);

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
