package com.hqw.huangye.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.litesuits.common.data.DataKeeper;

/**
 * Created by 506605454 on 2015/12/6 0006.
 */
public class sharedpreferencesUtil {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public static final String PASSWORD = "password";
    public static final String USERPHONE = "userPhone";
    public static final String USERID = "userId";
    // xml
    public sharedpreferencesUtil(Context context){
        sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        editor = sp.edit();
    }


    public String getPassword() {
        return sp.getString(PASSWORD, "");

    }

    public void setPassword(String password) {

        editor.putString(PASSWORD, password);
        editor.commit();
    }
    public void setUserphone(String userphone) {
        editor.putString(PASSWORD, userphone);
        editor.commit();
    }
    public  String getUserphone() {
        return sp.getString(USERPHONE, "");

    }
    public String getUserid(){
        return sp.getString(USERID,"");
    }
    public void setUserid(String userid){
        editor.putString(USERID, userid);
        editor.commit();
    }

}
