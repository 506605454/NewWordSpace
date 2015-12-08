package com.hqw.huangye.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hqw.huangye.R;
import com.hqw.huangye.bean.Issue;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/25.
 */
public class IssueViewPageFragment extends Fragment {

    private TextView textView;
    private String mWhat;
    private Issue mIssue;
    private TextView mCreateTime;
    private TextView mIssueTittle;
    private TextView mIssueContent;
    private TextView mAuthor;
    private TextView mAnswer;
    private TextView mSaygood;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();

        mIssue = args.getParcelable("issue");

        mWhat = args != null ? args.getString("text") : "";
        Log.i("hei","issuepage被创建了 "+mIssue.toString() );
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.issue_viewpage_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mAnswer = (TextView) view.findViewById(R.id.answer_content);
        mAuthor = (TextView) view.findViewById(R.id.answer_author);
        mCreateTime = (TextView) view.findViewById(R.id.issue_time);
        mIssueTittle = (TextView) view.findViewById(R.id.issue_tittle);
        mIssueContent = (TextView) view.findViewById(R.id.issue_content);
        mSaygood = (TextView) view.findViewById(R.id.up_count);

        showData(mIssue);
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
        Log.i("hei", "issuepage被摧毁了");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.i("hei", "issue可见" + getClass());

        }
    }



    private void showData(Issue issue) {
        String asker = mIssue.getIssueAsker();
        mIssueContent.setText(asker+"： "+mIssue.getIssueContent());
        mIssueTittle.setText(mIssue.getIssueTittle());
        mSaygood.setText(mIssue.getSaygood()+"");
        mAuthor.setText(mIssue.getIssueAuthor()+"答 "+asker);
        mAnswer.setText(Html.fromHtml(mIssue.getIssueAnswer()));
        Date date = new Date(mIssue.getCreateTime());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String sDateTime = sdf.format(date);
        mCreateTime.setText(sDateTime);
    }

}
