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
import com.hqw.huangye.bean.User;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.callback.ResultCallback;
import com.zhy.http.okhttp.request.OkHttpRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/25.
 */
public class ArticalViewPageFragment extends Fragment {

    private TextView textView;
    private String mWhat;
    private TextView mContent;
    private TextView mAuthor;
    private TextView mCreateTime;
    private TextView mTag;
    private TextView mWeibo;
    private TextView mButtomName;
    private TextView mSayGood;
    private TextView mTittle;
    public abstract class MyResultCallback<T> extends ResultCallback<T>
    {

        @Override
        public void onBefore(Request request)
        {
            super.onBefore(request);
          //  setTitle("loading...");
        }

        @Override
        public void onAfter()
        {
            super.onAfter();
         //   setTitle("Sample-okHttp");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();
        mWhat = args != null ? args.getString("text") : "";
        Log.i("hei","文章 onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.artical_viewpage_fragment, container, false);
      initView(view);
        mContent = (TextView) view.findViewById(R.id.artical_content);
        mAuthor = (TextView) view.findViewById(R.id.artical_author);
        mSayGood = (TextView) view.findViewById(R.id.up_count);
        mCreateTime = (TextView) view.findViewById(R.id.artical_time);
        mWeibo = (TextView) view.findViewById(R.id.artical_author_weibo);
        mTag = (TextView) view.findViewById(R.id.artical_author_introduce);
        mButtomName = (TextView) view.findViewById(R.id.artical_author_buttom);
        mTittle = (TextView) view.findViewById(R.id.artical_tittle);
        return view;
    }

    private void initView(View view) {


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
        Log.i("hei","文章被摧毁了");
        super.onDestroy();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        String url = "http://192.168.1.108:8080/page/two/getArtical.do?page="+mWhat;
        if (isVisibleToUser) {
            if (textView!=null){
                textView.setText(mWhat);
            }
            Log.i("hei", "文章可见" + getClass());
            new OkHttpRequest.Builder()
                    .url(url)
                    .get(new MyResultCallback<Artical>() {
                        @Override
                        public void onError(Request request, Exception e) {
                            Log.e("hei", "onError , e = " + e.getMessage());
                        }

                        @Override
                        public void onResponse(Artical artical) {
                            Log.e("hei", "onResponse , Artical = " + artical.toString());
                            Log.e("hei", "onResponse , Artical = " + artical.getContent());
                            mContent.setText(Html.fromHtml(artical.getContent()));
                            mButtomName.setText(artical.getAuthor());
                            mAuthor.setText(artical.getAuthor());
                            mSayGood.setText(artical.getSaygood()+"");

                            long createTime = artical.getCreateTime();
                            SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
                            java.util.Date dt = new Date(createTime);
                            String sDateTime = sdf.format(dt);
                            mCreateTime.setText(sDateTime);
                            mWeibo.setText(artical.getWeibo());
                            mTag.setText(artical.getTag());
                            mTittle.setText(artical.getTittleName());
                        }
                    });

        }
    }
}
