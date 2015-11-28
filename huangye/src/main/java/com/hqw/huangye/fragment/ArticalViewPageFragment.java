package com.hqw.huangye.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hqw.huangye.R;

/**
 * Created by Administrator on 2015/11/25.
 */
public class ArticalViewPageFragment extends Fragment {

    private TextView textView;
    private String mWhat;

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
        View view = inflater.inflate(R.layout.artical_viewpage_fragment,container,false);
        return view;
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
        if (isVisibleToUser) {
            if (textView!=null){
                textView.setText(mWhat);
            }
            Log.i("hei", "文章可见" + getClass());
        }
    }
}
