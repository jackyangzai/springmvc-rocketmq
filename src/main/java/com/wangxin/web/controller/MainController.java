package com.wangxin.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wangxin.web.util.WebUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @GetMapping(value = {"/", "index"})
    String home() {
        log.info("# 进入默认首页");
        return "index";
    }

    @GetMapping("leftnav")
    String leftnav() {
        log.debug("# leftnav");
        return "leftnav";
    }

    @GetMapping("topnav")
    String topnav() {
        return "topnav";
    }

    @RequestMapping(value = "/error", method = {RequestMethod.POST, RequestMethod.GET})
    String error(HttpServletRequest request, ModelMap map) {
        Object err = request.getAttribute("err");
        if (err != null) {
            log.error("# err={}", err);
            map.put("err", err);
        }
        Object pageUrl = request.getAttribute("pageUrl");
        if (pageUrl != null) {
            log.error("# pageUrl={}", pageUrl);
            map.put("pageUrl", pageUrl);
        }
        Map<String, String[]> param = request.getParameterMap();
        if (MapUtils.isNotEmpty(param)) {
            for (Map.Entry<String, String[]> entry : param.entrySet()) {
                map.put(entry.getKey(), entry.getValue());
                log.info("# error parameter.name=[{}],parameter.value=[{}]", entry.getKey(), entry.getValue());
            }
        }
        log.info("# 进入错误页面");
        return "common/error";
    }

    /**
     * 进入页面提示页面
     *
     * @param model Model
     * @param code  错误类型
     * @return
     * @author 王鑫
     */
    @RequestMapping(value = {"/system/error", "/system/error{code}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String error(Model model, @PathVariable(required = false) String code, HttpServletRequest request) {
        String msg = null;
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);// 这种取值方式可以解决redirect中文乱码问题
        if (MapUtils.isNotEmpty(map) && map.containsKey("msg")) {
            msg = (String) map.get("msg");
        }

        if (StringUtils.isBlank(msg)) {
            Object object = request.getAttribute("msg");
            if (object != null) {
                msg = object.toString();
            }
        }

        if (StringUtils.isNotBlank(code)) {
            msg = code + " - " + msg;
        }
        log.error("## error 错误 , msg={}", msg);
        model.addAttribute("msg", msg);
        return "common/error";
    }

}
