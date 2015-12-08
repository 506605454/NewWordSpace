package com.hqw.huangye.bean;


import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;

/**
 * Created by 506605454 on 2015/12/1 0001.
 */

public class Issue implements Parcelable {


    private String issueId;

    private String issueTittle;

    private String issueAuthor;

    private String issueAnswer;

    private String issueContent;
    private String issueAsker;

    private int saygood;

    private long createTime;

    public String getIssueAsker() {
        return issueAsker;
    }

    public void setIssueAsker(String issueAsker) {
        this.issueAsker = issueAsker;
    }



    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getIssueTittle() {
        return issueTittle;
    }

    public void setIssueTittle(String issueTittle) {
        this.issueTittle = issueTittle;
    }

    public String getIssueAuthor() {
        return issueAuthor;
    }

    public void setIssueAuthor(String issueAuthor) {
        this.issueAuthor = issueAuthor;
    }

    public String getIssueAnswer() {
        return issueAnswer;
    }

    public void setIssueAnswer(String issueAnswer) {
        this.issueAnswer = issueAnswer;
    }

    public String getIssueContent() {
        return issueContent;
    }

    public void setIssueContent(String issueContent) {
        this.issueContent = issueContent;
    }

    public int getSaygood() {
        return saygood;
    }

    public void setSaygood(int saygood) {
        this.saygood = saygood;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.issueId);
        dest.writeString(this.issueTittle);
        dest.writeString(this.issueAuthor);
        dest.writeString(this.issueAnswer);
        dest.writeString(this.issueContent);
        dest.writeString(this.issueAsker);
        dest.writeInt(this.saygood);
        dest.writeLong(this.createTime);
    }

    public Issue() {
    }

    private Issue(Parcel in) {
        this.issueId = in.readString();
        this.issueTittle = in.readString();
        this.issueAuthor = in.readString();
        this.issueAnswer = in.readString();
        this.issueContent = in.readString();
        this.issueAsker = in.readString();
        this.saygood = in.readInt();
        this.createTime = in.readLong();
    }

    public static final Parcelable.Creator<Issue> CREATOR = new Parcelable.Creator<Issue>() {
        public Issue createFromParcel(Parcel source) {
            return new Issue(source);
        }

        public Issue[] newArray(int size) {
            return new Issue[size];
        }
    };
}
