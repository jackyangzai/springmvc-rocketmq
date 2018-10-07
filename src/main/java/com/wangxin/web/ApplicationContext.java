package com.wangxin.web;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * @Description 将version版本号写入application中，给css,js引用时用
 * @date Oct 9, 2016 8:39:46 PM
 */
@Component
public class ApplicationContext implements ServletContextAware {

    private static final Logger log = LoggerFactory.getLogger(ApplicationContext.class);

    /**
     * <p>
     * Description 初始化到Application作用域当中
     * </p>
     */
    @Override
    public void setServletContext(ServletContext context) {
        String domain = context.getContextPath();
        log.info("# context path=[{}]", domain);
        context.setAttribute("ctx", domain);
    }

}
