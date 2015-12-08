package com.hqw.huangye.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hqw.huangye.R;
import com.hqw.huangye.bean.Users;
import com.hqw.huangye.util.Constants;
import com.hqw.huangye.util.sharedpreferencesUtil;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.callback.ResultCallback;
import com.zhy.http.okhttp.request.OkHttpRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity implements View.OnClickListener {

    private RelativeLayout mLoginBg;
    private TextView mGotoRegisiter;
    private Button mLoginButton;
    private EditText mUserAccount;
    private EditText mPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        mLoginBg = (RelativeLayout) findViewById(R.id.login_bg);
        mLoginBg.getBackground().setAlpha(50);
        mGotoRegisiter = (TextView) findViewById(R.id.register_account);
        mGotoRegisiter.setOnClickListener(this);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(this);
        mUserAccount = (EditText) findViewById(R.id.user_name);
        mPassWord = (EditText) findViewById(R.id.user_password);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.register_account:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.login_button:
                LoginToMyTwo();
                break;
        }


    }

    private void LoginToMyTwo() {
        String URL = Constants.BASE_URL + "/app/home/loginToMyTwo.do";
        String userAccount = mUserAccount.getText().toString();
        String password = mPassWord.getText().toString();
        Map<String,String> map = new HashMap<String,String>();
        map.put("userAccount",userAccount);
        map.put("password",password);

        new OkHttpRequest.Builder()
                .url(URL)
                .params(map)
                .post(new MyResultCallback<Users>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e("hei", "onError , e = " + e.getMessage());
                    }

                    @Override
                    public void onResponse(Users users) {
                        // 保存到数据库
                        sharedpreferencesUtil sharedpreferencesUtil = new sharedpreferencesUtil(LoginActivity.this);
                        sharedpreferencesUtil.setPassword(users.getPassword());
                        sharedpreferencesUtil.setUserid(users.getUserId());
                        sharedpreferencesUtil.setUserphone(users.getUserAccount());
                            users.toString();
                        Intent intent = new Intent(LoginActivity.this,CollectActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }
    // 回调公共类
    public abstract class MyResultCallback<T> extends ResultCallback<T> {

        //之前
        @Override
        public void onBefore(Request request) {
            super.onBefore(request);
        }

        //之后
        @Override
        public void onAfter() {
            super.onAfter();

        }




    }

}
