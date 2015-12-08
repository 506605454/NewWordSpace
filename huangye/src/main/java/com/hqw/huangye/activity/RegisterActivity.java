package com.hqw.huangye.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hqw.huangye.R;
import com.hqw.huangye.bean.Users;
import com.hqw.huangye.util.Constants;
import com.hqw.huangye.util.sharedpreferencesUtil;
import com.litesuits.common.data.DataKeeper;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.callback.ResultCallback;
import com.zhy.http.okhttp.request.OkHttpRequest;

import java.util.HashMap;
import java.util.Map;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;

public class RegisterActivity extends Activity implements View.OnClickListener {

   private RelativeLayout mLoginBg;
    private EditText mUserNumber;
    private EditText mVerifyCode;
    private Button mGetVerifyCode;
    private Button mNextStep;
    private Boolean hasVerifySMS = false;
    private EditText mPassword;
    private ImageView mBacktoLogin;
    private LinearLayout mPasswordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        BmobSMS.initialize(this, Constants.BMOB_KEY);
        mLoginBg = (RelativeLayout) findViewById(R.id.login_bg);
        mLoginBg.getBackground().setAlpha(50);
        mGetVerifyCode = (Button) findViewById(R.id.get_verify_code);
        mGetVerifyCode.setOnClickListener(this);
        mNextStep = (Button) findViewById(R.id.next_step);
        mNextStep.setOnClickListener(this);
        mUserNumber = (EditText) findViewById(R.id.user_name);
        mVerifyCode = (EditText) findViewById(R.id.verify_code);
        mPassword = (EditText) findViewById(R.id.user_password);
        mBacktoLogin = (ImageView) findViewById(R.id.back_to_login);
        mBacktoLogin.setOnClickListener(this);
        mPasswordLayout = (LinearLayout) findViewById(R.id.user_password_layout);
    }

    private void isRegister(){
        String url = Constants.BASE_URL + "/app/home/isRegister.do";

        new OkHttpRequest.Builder()
                .url(url)
                .get(new MyResultCallback<Map<String,Object>>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e("hei", "onError , e = " + e.getMessage());
                    }

                    @Override
                    public void onResponse(Map<String,Object> objectMap) {
                        Boolean isregister = (Boolean) objectMap.get("isRegister");
                        if (!isregister){
                            String userNumber = mUserNumber.getText().toString();
                            sendSMS(userNumber);
                        }else{
                            Toast.makeText(RegisterActivity.this,"该用户已经注册过啦",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void verifySMSCode(String userNumber,String verifyCode){
        BmobSMS.verifySmsCode(this, userNumber, verifyCode, new VerifySMSCodeListener() {

            @Override
            public void done(BmobException ex) {
                // TODO Auto-generated method stub
                if (ex == null) {//短信验证码已验证成功
                    Log.i("bmob", "验证通过");

                    hasVerifySMS = true;
                    mPasswordLayout.setVisibility(View.VISIBLE);
                    mNextStep.setText("Login My Two");

                    mVerifyCode.setEnabled(false);
                    mUserNumber.setEnabled(false);
                    mUserNumber.setFocusable(false);
                    mUserNumber.setFocusable(false);


                } else {
                    Toast.makeText(RegisterActivity.this,"验证码不整确",Toast.LENGTH_SHORT).show();
                    Log.i("bmob", "验证失败：code =" + ex.getErrorCode() + ",msg = " + ex.getLocalizedMessage());
                }
            }
        });
    }

    private void sendSMS(String userNumber){

        BmobSMS.requestSMSCode(this, userNumber, "register", new RequestSMSCodeListener() {

            @Override
            public void done(Integer smsId, BmobException ex) {
                // TODO Auto-generated method stub
                if (ex == null) {//验证码发送成功
                    Log.i("hei", "短信id：" + smsId);
                } else {
                    Log.i("hei", "错误" + ex.getMessage());
                }
            }
        });
    }

    private void saveUser(){



        String url = Constants.BASE_URL + "/app/home/saveUser.do";
        Map<String,String> map = new HashMap<String,String>();
        String userAccount = mUserNumber.getText().toString();
        String password = mPassword.getText().toString();


        if (mPassword.length()<6){
            Toast.makeText(RegisterActivity.this,"密码太短啦",Toast.LENGTH_SHORT).show();
            return;
        }


        map.put("userAccount",userAccount);
        map.put("password",password);

        new OkHttpRequest.Builder()
                .url(url)
                .params(map)
                .post(new MyResultCallback<Users>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e("hei", "onError , e = " + e.getMessage());
                    }

                    @Override
                    public void onResponse(Users users) {
                        // 保存到数据库
                        sharedpreferencesUtil sharedpreferencesUtil = new sharedpreferencesUtil(RegisterActivity.this);
                        sharedpreferencesUtil.setPassword(users.getPassword());
                        sharedpreferencesUtil.setUserid(users.getUserId());
                        sharedpreferencesUtil.setUserphone(users.getUserAccount());

                        Intent intent = new Intent(RegisterActivity.this,SettingActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.get_verify_code :

                isRegister();
                break;

            case R.id.next_step:

                String userNumber = mUserNumber.getText().toString();
                String verifyCode = mVerifyCode.getText().toString();
                verifySMSCode(userNumber,verifyCode);

                if (hasVerifySMS){
                    saveUser();
                }
                break;
            case R.id.user_password:

                break;
            case R.id.back_to_login:
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
               startActivity(intent);
                finish();
                break;

        }
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
