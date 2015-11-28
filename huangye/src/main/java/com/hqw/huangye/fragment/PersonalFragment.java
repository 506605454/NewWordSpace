package com.hqw.huangye.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hqw.huangye.R;
import com.hqw.huangye.activity.CollectActivity;
import com.hqw.huangye.activity.SettingActivity;

/**
 * Created by Administrator on 2015/11/25.
 */
public class PersonalFragment extends Fragment implements View.OnClickListener {

    private LinearLayout mCollect;
    private LinearLayout mSettings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_fragment,container,false);
         mCollect = (LinearLayout) view.findViewById(R.id.my_collect);
        mCollect.setOnClickListener(this);
        mSettings = (LinearLayout) view.findViewById(R.id.setting_lo);
        mSettings.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_collect:
                Intent intentCollect = new Intent(getActivity(), CollectActivity.class);
                startActivity(intentCollect);
                break;
            case R.id.setting_lo:
                Intent intentSetting = new Intent(getActivity(), SettingActivity.class);
                startActivity(intentSetting);
                break;
        }
    }

}
