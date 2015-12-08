package com.hqw.huangye.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hqw.huangye.R;

public class CollectActivity extends Activity {

    private ImageView mBackMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_collect);
        initView();
    }

    private void initView() {
        mBackMain = (ImageView) findViewById(R.id.back_main);
        mBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollectActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
