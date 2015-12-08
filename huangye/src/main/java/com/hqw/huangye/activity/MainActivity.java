package com.hqw.huangye.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.hqw.huangye.R;
import com.hqw.huangye.fragment.ArticalFragment;
import com.hqw.huangye.fragment.HomeFragment;
import com.hqw.huangye.fragment.IssueFragment;
import com.hqw.huangye.fragment.PersonalFragment;
import com.hqw.huangye.fragment.StuffFragment;
import com.hqw.huangye.view.MyBottomLayout;
import com.litesuits.common.assist.Toastor;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends FragmentActivity {

    MyBottomLayout myBottomLayout;
    HomeFragment homeFragment;
    ArticalFragment articalFragment;
    PersonalFragment personalFragment;
    IssueFragment issueFragment;
    StuffFragment stuffFragment;
//    ImageView shareView;

    public Map<String,Integer> mSaveTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("hei","MainActivity onCreate");
        setContentView(R.layout.activity_main);
        mSaveTemp = new HashMap<String,Integer>();
        homeFragment = new HomeFragment();
        articalFragment = new ArticalFragment();
        personalFragment = new PersonalFragment();
        issueFragment = new IssueFragment();
        stuffFragment = new StuffFragment();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("hei", "MainActivity onResume");
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        Log.i("hei", "MainActivity onResumeFragments");
        if (mSaveTemp!=null){
            if (mSaveTemp.get("home")!=null){
                Log.i("hei", "MainActivity onResumeFragments" +mSaveTemp.get("home"));

            }
        }
    }

    private void init() {
        initPageContent(new HomeFragment());
        findView();
        setOnClick();
    }

    /**
     * 设置默认页面数据
     */
    private void initPageContent(Fragment fragment) {
        FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
        Log.i("hei","切换fragment"+fragment.getClass());
        t.replace(R.id.my_content, fragment);
        t.commit();
    }

    private void setOnClick() {
        myBottomLayout.setOnCallBackListener(new MyBottomLayout.IcallBackListener() {
            @Override
            public void onClick(int id) {
                switch (id) {
                    case R.id.home_layout:
                        Log.i("hei","首页被点击");
                        initPageContent(homeFragment);
                        break;
                    case R.id.article_layout:
                        Log.i("hei","文章被点击");
                        initPageContent(articalFragment);
                        break;
                    case R.id.issue_layout:
                        Log.i("hei","问题被点击");
                        initPageContent(issueFragment);
                        break;
                    case R.id.stuff_layout:
                        Log.i("hei","东西被点击");
                        initPageContent(stuffFragment);
                        break;
                    case R.id.personal_layout:
                        Log.i("hei","个人被点击");
                        initPageContent(personalFragment);
                        break;

                }

            }
        });
    }

    // private class MyCallBackListener implements IC

    private void findView() {
        myBottomLayout = (MyBottomLayout) findViewById(R.id.bottom_bar);
//        shareView = (ImageView) findViewById(R.id.share_img);
//        shareView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toastor toastor = new Toastor(MainActivity.this);
//                toastor.showToast("点击了");
//            }
//        });
    }
}
