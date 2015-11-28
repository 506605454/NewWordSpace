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
public class StuffViewPageFragment extends Fragment {

    private TextView textView;
    private String mWhat;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();
        mWhat = args != null ? args.getString("text") : "";
        Log.i("hei","StuffViewPageFragment被创建了");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stuff_viewpage_fragment,container,false);
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
        super.onDestroy();
        Log.i("hei", "StuffViewPageFragment被摧毁了");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.i("hei", "StuffViewPageFragment可见" + getClass());
        }
    }
}
