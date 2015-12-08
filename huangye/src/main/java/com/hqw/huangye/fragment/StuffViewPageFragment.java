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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hqw.huangye.R;
import com.hqw.huangye.bean.Stuff;
import com.hqw.huangye.util.Constants;
import com.hqw.huangye.view.CircularProgress;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.callback.ResultCallback;
import com.zhy.http.okhttp.request.OkHttpRequest;

/**
 * Created by Administrator on 2015/11/25.
 */
public class StuffViewPageFragment extends Fragment {

    private TextView textView;
    private TextView mVesion;
    private TextView mTittle;
    private TextView mContet;
    private ImageView mStuffImg;
    private String mWhat;
    private CircularProgress mCircularProgress;
    private Stuff mStuff;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();
        mStuff = args.getParcelable("stuff");
        mWhat = args != null ? args.getString("text") : "";
        Log.i("hei","StuffViewPageFragment被创建了");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stuff_viewpage_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mCircularProgress = (CircularProgress) view.findViewById(R.id.progress_bar);
        mTittle = (TextView) view.findViewById(R.id.image_info);
        mContet = (TextView) view.findViewById(R.id.stuff_comment);
        mStuffImg = (ImageView) view.findViewById(R.id.stuff_image);
        mVesion = (TextView) view.findViewById(R.id.version_log);
        showData(mStuff);
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
        Log.i("hei", "StuffViewPageFragment被摧毁了");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        String url = Constants.BASE_URL+"/page/two/getStuff.do?page=" + mWhat;
        if (isVisibleToUser) {
            Log.i("hei", "StuffViewPageFragment可见" + getClass());

        }
    }
    private void showData(Stuff stuff) {
        mContet.setText(stuff.getStuffContent());
        mTittle.setText(stuff.getStuffTittle());
        mVesion.setText(stuff.getVersion() + "");
        String url = Constants.BASE_URL+stuff.getImgurl();
        //基本
        new OkHttpRequest.Builder() .url(url).imageView(mStuffImg).displayImage(new MyResultCallback<Object>() {
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

    public abstract class MyResultCallback<T> extends ResultCallback<T> {

        @Override
        public void onBefore(Request request) {
            super.onBefore(request);
            //  setTitle("loading...");
        }

        @Override
        public void onAfter() {
            super.onAfter();
            //   setTitle("Sample-okHttp");
            changeShowStatus();
        }
    }
}
