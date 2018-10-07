package com.wangxin.service.simple.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangxin.common.utils.UUIDUtil;
import com.wangxin.entity.auth.User;
import com.wangxin.entity.simple.News;
import com.wangxin.mapper.simple.NewsMapper;
import com.wangxin.service.simple.NewsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NewsServiceImpl implements NewsService {

    private static final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public void addNews(News news) {
        if (news != null) {
            news.setId(UUIDUtil.getRandom32PK());
            news.setCreateTime(Calendar.getInstance().getTime());
            newsMapper.insert(news);
        }
    }

    @Override
    public void editNews(News news) {
        if (news != null) {
            news.setNewsTime(Calendar.getInstance().getTime());
            newsMapper.update(news);
        }
    }

    @Override
    public News findNewsById(String id) {
        if (StringUtils.isBlank(id))
            return null;
        News news = newsMapper.findById(id);
        return news;
    }

    @Override
    public PageInfo<News> findNewsByPage(Integer pageNum, String keywords) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("keywords", keywords);

        if (null == pageNum || pageNum < 1)
            pageNum = 1;


        PageHelper.startPage(pageNum, 10);


        List<News> news = newsMapper.findNewsByKeywords(keywords);
        PageInfo<News> page = new PageInfo<News>(news);
        return page;
    }

}
