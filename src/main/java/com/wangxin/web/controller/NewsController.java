package com.wangxin.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.wangxin.entity.simple.News;
import com.wangxin.service.simple.NewsService;
import com.wangxin.web.command.NewsCommand;
import com.wangxin.web.util.WebUtil;
import com.wangxin.web.validator.NewsValidator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NewsController {

    private static final Logger log = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;

    /*
     * 表单提交日期绑定
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/news/add")
    public String addNews() {
        log.info("# addNews");
        return "view/news/add";
    }

    @GetMapping("/news/load/{id}")
    public String load(@PathVariable("id") String id, ModelMap modelMap) {
        log.info("# addNews , id={}", id);
        News news = newsService.findNewsById(id);
        modelMap.put("news", news);
        return "view/news/edit_form";
    }


    @PostMapping("/news/add")
    @ResponseBody
    public boolean add(@ModelAttribute("mewsCommand") NewsCommand command, BindingResult result) {
        log.info("# add , command={}", JSON.toJSONString(command));
        new NewsValidator().validate(command, result);
        if (result.hasErrors()) {
            return false;
        }
        News news = new News();
        news.setTitle(command.getTitle());
        news.setDescription(command.getDescription());
        news.setAddress(command.getAddress());
        news.setNewsTime(command.getNewsTime());
        newsService.addNews(news);
        return true;
    }

    @PostMapping("/news/edit")
    @ResponseBody
    public boolean edit(@ModelAttribute("mewsCommand") NewsCommand command, BindingResult result) {
        new NewsValidator().validate(command, result);
        if (result.hasErrors()) {
            return false;
        }
        News news = new News();
        news.setTitle(command.getTitle());
        news.setDescription(command.getDescription());
        news.setAddress(command.getAddress());
        news.setNewsTime(command.getNewsTime());
        news.setId(command.getId());
        newsService.editNews(news);
        return true;
    }

    @RequestMapping(value = "/news/list", method = RequestMethod.GET)
    public String list(ModelMap modelMap) {
        PageInfo<News> page = newsService.findNewsByPage(null, null);
        modelMap.put("page", page);
        return "view/news/list";
    }

    @PostMapping("/news/list_page")
    public String listPage(@RequestParam(required = false) Integer pageNum, @RequestParam(required = false) String keywords, ModelMap modelMap) {
        log.info("# search , pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<News> page = newsService.findNewsByPage(pageNum, keywords);
        modelMap.put("page", page);
        return "view/news/list_page";
    }
}
