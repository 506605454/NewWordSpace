package com.hqw.huangye.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;

/**
 * Created by 506605454 on 2015/12/2 0002.
 */
public class Artical implements Parcelable {

    private String author;
    private String weibo;
    private String content;
    private String tittleName;
    private  int version;
    private  int saygood;
    private String tag;
    private long createTime;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTittleName() {
        return tittleName;
    }

    public void setTittleName(String tittleName) {
        this.tittleName = tittleName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getSaygood() {
        return saygood;
    }

    public void setSaygood(int saygood) {
        this.saygood = saygood;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Artical{" +
                "author='" + author + '\'' +
                ", weibo='" + weibo + '\'' +
                ", content='" + content + '\'' +
                ", tittleName='" + tittleName + '\'' +
                ", version=" + version +
                ", saygood=" + saygood +
                ", tag='" + tag + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.author);
        dest.writeString(this.weibo);
        dest.writeString(this.content);
        dest.writeString(this.tittleName);
        dest.writeInt(this.version);
        dest.writeInt(this.saygood);
        dest.writeString(this.tag);
        dest.writeLong(this.createTime);
    }

    public Artical() {
    }

    private Artical(Parcel in) {
        this.author = in.readString();
        this.weibo = in.readString();
        this.content = in.readString();
        this.tittleName = in.readString();
        this.version = in.readInt();
        this.saygood = in.readInt();
        this.tag = in.readString();
        this.createTime = in.readLong();
    }

    public static final Parcelable.Creator<Artical> CREATOR = new Parcelable.Creator<Artical>() {
        public Artical createFromParcel(Parcel source) {
            return new Artical(source);
        }

        public Artical[] newArray(int size) {
            return new Artical[size];
        }
    };
}
