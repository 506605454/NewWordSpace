package com.hqw.huangye.bean;


import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;

/**
 * Created by 506605454 on 2015/12/1 0001.
 */

public class Stuff implements Parcelable {

    private String stuffId;

    private String stuffTittle;

    private String stuffContent;

    private String imgurl;

    private long createTime;

    private int version;


    public int getVersion() {

        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getStuffId() {
        return stuffId;
    }

    public void setStuffId(String stuffId) {
        this.stuffId = stuffId;
    }

    public String getStuffTittle() {
        return stuffTittle;
    }

    public void setStuffTittle(String stuffTittle) {
        this.stuffTittle = stuffTittle;
    }

    public String getStuffContent() {
        return stuffContent;
    }

    public void setStuffContent(String stuffContent) {
        this.stuffContent = stuffContent;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
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
        dest.writeString(this.stuffId);
        dest.writeString(this.stuffTittle);
        dest.writeString(this.stuffContent);
        dest.writeString(this.imgurl);
        dest.writeLong(this.createTime);
        dest.writeInt(this.version);
    }

    public Stuff() {
    }

    private Stuff(Parcel in) {
        this.stuffId = in.readString();
        this.stuffTittle = in.readString();
        this.stuffContent = in.readString();
        this.imgurl = in.readString();
        this.createTime = in.readLong();
        this.version = in.readInt();
    }

    public static final Parcelable.Creator<Stuff> CREATOR = new Parcelable.Creator<Stuff>() {
        public Stuff createFromParcel(Parcel source) {
            return new Stuff(source);
        }

        public Stuff[] newArray(int size) {
            return new Stuff[size];
        }
    };
}
