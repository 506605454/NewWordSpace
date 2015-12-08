package com.hqw.huangye.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hqw.huangye.R;
import com.hqw.huangye.bean.Artical;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.callback.ResultCallback;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/25.
 */
public class ArticalViewPageFragment extends Fragment {

    private TextView textView;
    private String mWhat;
    private Artical mArtical;
    private TextView mContent;
    private TextView mAuthor;
    private TextView mCreateTime;
    private TextView mTag;
    private TextView mWeibo;
    private TextView mButtomName;
    private TextView mSayGood;
    private TextView mTittle;

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
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();
        mArtical = args.getParcelable("artical");
        mWhat = args != null ? args.getString("text") : "";
        Log.i("hei", "文章 onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.artical_viewpage_fragment, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {

        mContent = (TextView) view.findViewById(R.id.artical_content);
        mAuthor = (TextView) view.findViewById(R.id.artical_author);
        mSayGood = (TextView) view.findViewById(R.id.up_count);
        mCreateTime = (TextView) view.findViewById(R.id.artical_time);
        mWeibo = (TextView) view.findViewById(R.id.artical_author_weibo);
        mTag = (TextView) view.findViewById(R.id.artical_author_introduce);
        mButtomName = (TextView) view.findViewById(R.id.artical_author_buttom);
        mTittle = (TextView) view.findViewById(R.id.artical_tittle);
        showData(mArtical);

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
        Log.i("hei", "文章被摧毁了");
        super.onDestroy();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (textView != null) {
                textView.setText(mWhat);
            }
            Log.i("hei", "文章可见" + getClass());

        }
    }

    private void showData(Artical artical) {
        mContent.setText(Html.fromHtml(artical.getContent()));
        mButtomName.setText(mArtical.getAuthor());
        mAuthor.setText(mArtical.getAuthor());
        mSayGood.setText(mArtical.getSaygood() + "");

        long createTime = mArtical.getCreateTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        java.util.Date dt = new Date(createTime);
        String sDateTime = sdf.format(dt);
        mCreateTime.setText(sDateTime);
        mWeibo.setText(mArtical.getWeibo());
        mTag.setText(mArtical.getTag());
        mTittle.setText(mArtical.getTittleName());
    }


}
