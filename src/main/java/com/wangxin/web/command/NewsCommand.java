package com.wangxin.web.command;

import java.io.Serializable;
import java.util.Date;

public class NewsCommand implements Serializable {

    private static final long serialVersionUID = 1953279978592416290L;

    private String id;

    private String title;// 新闻标题

    private String description;// 新闻内容

    private String address;// 新闻发生地址

    private Date newsTime;// 新闻发生时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(Date newsTime) {
        this.newsTime = newsTime;
    }

}
