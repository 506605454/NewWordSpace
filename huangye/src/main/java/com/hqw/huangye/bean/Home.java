package com.hqw.huangye.bean;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by 506605454 on 2015/12/1 0001.
 */

public class Home  implements Serializable, Parcelable {

    private String homeId;

    private String title;
    private String comment;

    private String author;

    private long createTime;

    private int saygood;

    private String imgurl;

    private int version;

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getHomeId() {
        return homeId;
    }

    public void setHomeId(String homeId) {
        this.homeId = homeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Home{" +
                "homeId='" + homeId + '\'' +
                ", title='" + title + '\'' +
                ", comment='" + comment + '\'' +
                ", author='" + author + '\'' +
                ", createTime=" + createTime +
                ", saygood=" + saygood +
                ", imgurl='" + imgurl + '\'' +
                ", version=" + version +
                '}';
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getSaygood() {
        return saygood;
    }

    public void setSaygood(int saygood) {
        this.saygood = saygood;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.homeId);
        dest.writeString(this.title);
        dest.writeString(this.comment);
        dest.writeString(this.author);
        dest.writeLong(this.createTime);
        dest.writeInt(this.saygood);
        dest.writeString(this.imgurl);
        dest.writeInt(this.version);
    }

    public Home() {
    }

    private Home(Parcel in) {
        this.homeId = in.readString();
        this.title = in.readString();
        this.comment = in.readString();
        this.author = in.readString();
        this.createTime = in.readLong();
        this.saygood = in.readInt();
        this.imgurl = in.readString();
        this.version = in.readInt();
    }

    public static final Parcelable.Creator<Home> CREATOR = new Parcelable.Creator<Home>() {
        public Home createFromParcel(Parcel source) {
            return new Home(source);
        }

        public Home[] newArray(int size) {
            return new Home[size];
        }
    };
}
