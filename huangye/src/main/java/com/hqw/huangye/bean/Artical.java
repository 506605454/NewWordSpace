package com.hqw.huangye.bean;

import java.sql.Timestamp;

/**
 * Created by 506605454 on 2015/12/2 0002.
 */
public class Artical {

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
}
