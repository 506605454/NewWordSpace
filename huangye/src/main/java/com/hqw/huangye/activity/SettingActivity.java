package com.hqw.huangye.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hqw.huangye.R;

public class SettingActivity extends Activity {

    private ImageView mBackImage;
    private TextView mTittle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mBackImage = (ImageView) findViewById(R.id.back_icon);
        mBackImage.setVisibility(View.VISIBLE);
        mTittle = (TextView) findViewById(R.id.tittle_name);
        mTittle.setText("设置");
        //mTittle.setTextSize(getResources().getDimension(R.dimen.));
    }
}
