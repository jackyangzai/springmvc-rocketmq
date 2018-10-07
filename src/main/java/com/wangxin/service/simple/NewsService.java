package com.wangxin.service.simple;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.wangxin.entity.auth.User;
import com.wangxin.entity.simple.News;

import java.util.List;


public interface NewsService {

    public void addNews(News news);

    public void editNews(News news);

    public News findNewsById(String id);

    public PageInfo<News> findNewsByPage(Integer pageNum, String keywords);

}