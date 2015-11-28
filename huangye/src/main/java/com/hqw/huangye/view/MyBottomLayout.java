package com.hqw.huangye.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hqw.huangye.R;

/**
 * Created by Administrator on 2015/11/25.
 */
public class MyBottomLayout extends LinearLayoutCompat {
    RelativeLayout mHomeLayout;
    RelativeLayout mArticleLayout;
    RelativeLayout mIssueLayout;
    RelativeLayout mStuffLayout;
    RelativeLayout mPersonalLayout;
    LayoutInflater mInflater;

    public MyBottomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mInflater = LayoutInflater.from(getContext());
        View view = mInflater.inflate(R.layout.bottom_bar, this);
        findView(view);
        initData();
        setOnClick();
    }

    /**
     *底部栏标题初始化
     */
    private void initData() {

        reSetStatus(mHomeLayout, R.drawable.image_tabbar_button_home_down,"首页");
        reSetStatus(mArticleLayout, R.drawable.image_tabbar_button_find,"文章");
        reSetStatus(mPersonalLayout, R.drawable.image_tabbar_button_manage,"个人");
        reSetStatus(mStuffLayout, R.drawable.image_tabbar_button_search,"问题");
        reSetStatus(mIssueLayout, R.drawable.image_tabbar_button_more,"东西");
        ((TextView)mHomeLayout.findViewById(R.id.tv_barname)).setTextColor(getResources().getColor(R.color.lite_blue));

    }

    /**
     * 设置点击事件
     */
    private void setOnClick() {
        mHomeLayout.setOnClickListener(new Listener());
        mArticleLayout.setOnClickListener(new Listener());
        mIssueLayout.setOnClickListener(new Listener());
        mStuffLayout.setOnClickListener(new Listener());
        mPersonalLayout.setOnClickListener(new Listener());

    }

    /**
     * 点击事件的处理
     */
    private class Listener implements OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.home_layout:
                    // 更改图标
                    initPic(0);
                    //页面切换
                    break;
                case R.id.article_layout:
                    initPic(1);
                    break;
                case R.id.issue_layout:
                    initPic(3);
                    break;
                case R.id.stuff_layout:
                    initPic(2);
                    break;
                case R.id.personal_layout:
                    initPic(4);
                    break;
            }
            icallBackListener.onClick(v.getId());
        }
    }

    /**
     * 重置图标颜色
     * @param layout
     */
    private void reSetStatus(RelativeLayout layout,int id,String name) {

        layout.findViewById(R.id.item_image).setBackgroundResource(id);
        TextView tv = (TextView) layout.findViewById(R.id.tv_barname);
        tv.setTextColor(getResources().getColor(R.color.little_dark));
        if (name!=null){
            tv.setText(name);
        }
    }

    private void changeStatus(RelativeLayout layout,int id){
        // 切换成点击状态
        layout.findViewById(R.id.item_image).setBackgroundResource(id);
        TextView tv = (TextView) layout.findViewById(R.id.tv_barname);
        tv.setTextColor(getResources().getColor(R.color.lite_blue));
    }

    private void initPic(int i) {
        switch (i) {
            case 0:
                // 切换成点击状态
                changeStatus(mHomeLayout,R.drawable.image_tabbar_button_home_down);

                // 还原其他图标默认状态
                reSetStatus(mArticleLayout,R.drawable.image_tabbar_button_find,null);
                reSetStatus(mPersonalLayout,R.drawable.image_tabbar_button_manage,null);
                reSetStatus(mStuffLayout,R.drawable.image_tabbar_button_search,null);
                reSetStatus(mIssueLayout,R.drawable.image_tabbar_button_more,null);
                break;
            case 1:

                // 切换成点击状态
                changeStatus(mArticleLayout,R.drawable.image_tabbar_button_find_down);

                // 还原其他图标默认状态
                reSetStatus(mHomeLayout,R.drawable.image_tabbar_button_home,null);
                reSetStatus(mPersonalLayout,R.drawable.image_tabbar_button_manage,null);
                reSetStatus(mStuffLayout,R.drawable.image_tabbar_button_search,null);
                reSetStatus(mIssueLayout,R.drawable.image_tabbar_button_more,null);
                break;
            case 2:
                // 切换成点击状态
                changeStatus(mIssueLayout, R.drawable.image_tabbar_button_more_down);

                // 还原其他图标默认状态
                reSetStatus(mHomeLayout,R.drawable.image_tabbar_button_home,null);
                reSetStatus(mArticleLayout,R.drawable.image_tabbar_button_find,null);
                reSetStatus(mPersonalLayout,R.drawable.image_tabbar_button_manage,null);
                reSetStatus(mStuffLayout,R.drawable.image_tabbar_button_search,null);

                break;
            case 3:
                // 切换成点击状态
                changeStatus(mStuffLayout, R.drawable.image_tabbar_button_search_down);

                // 还原其他图标默认状态
                reSetStatus(mHomeLayout,R.drawable.image_tabbar_button_home,null);
                reSetStatus(mArticleLayout,R.drawable.image_tabbar_button_find,null);
                reSetStatus(mPersonalLayout,R.drawable.image_tabbar_button_manage,null);
                reSetStatus(mIssueLayout,R.drawable.image_tabbar_button_more,null);

                break;
            case 4:

                // 切换成点击状态
                changeStatus(mPersonalLayout, R.drawable.image_tabbar_button_manage_down);

                // 还原其他图标默认状态
                reSetStatus(mHomeLayout,R.drawable.image_tabbar_button_home,null);
                reSetStatus(mArticleLayout,R.drawable.image_tabbar_button_find,null);
                reSetStatus(mStuffLayout,R.drawable.image_tabbar_button_search,null);
                reSetStatus(mIssueLayout,R.drawable.image_tabbar_button_more,null);
                break;
        }

    }

    /**
     * 寻找控件
     *
     * @param view
     */
    private void findView(View view) {
        mHomeLayout = (RelativeLayout) view.findViewById(R.id.home_layout);
        mArticleLayout = (RelativeLayout) view.findViewById(R.id.article_layout);
        mIssueLayout = (RelativeLayout) view.findViewById(R.id.stuff_layout);
        mStuffLayout = (RelativeLayout) view.findViewById(R.id.issue_layout);
        mPersonalLayout = (RelativeLayout) view.findViewById(R.id.personal_layout);

    }
    public interface IcallBackListener{
        public void onClick(int id);
    }
    IcallBackListener icallBackListener = null;
    public void setOnCallBackListener(IcallBackListener icallBackListener){
        this.icallBackListener =icallBackListener;

    }
}
