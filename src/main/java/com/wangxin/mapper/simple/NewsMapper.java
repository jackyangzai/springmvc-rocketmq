package com.wangxin.mapper.simple;

import java.util.List;

import com.github.pagehelper.Page;
import com.wangxin.entity.simple.News;
import com.wangxin.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsMapper extends BaseMapper<String, News> {

    List<News> findNewsByKeywords(@Param("keywords") String keywords);

}
