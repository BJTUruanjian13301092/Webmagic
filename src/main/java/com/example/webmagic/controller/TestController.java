package com.example.webmagic.controller;

import com.example.webmagic.spider.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    SpiderService spiderService;

    @RequestMapping("/my-controller")
    public String testMyController(){
        return "This is my controller";
    }

    @RequestMapping("/spider-webmagic")
    public void webmagicStart(){
        spiderService.webMagicSpider();
    }

    @RequestMapping("/spider-baidu")
    public void baiduStart(){
        spiderService.baiduSpider();
    }

    @RequestMapping("/test-request")
    public String testRequest(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        map.put("ParameterMap", request.getParameterMap());
        map.put("Cookies", request.getCookies());
        map.put("ContextPath", request.getContextPath());
        map.put("AuthType", request.getAuthType());

        Enumeration<String> headers = request.getHeaderNames();
        while(headers.hasMoreElements()) {
            String header = headers.nextElement();
            String value = request.getHeader(header);
            map.put(header, value);
        }

        System.out.println("ha~ now u catch me");

        Cookie cookie1 = new Cookie("cookie1", "111");
        Cookie cookie2 = new Cookie("cookie2", "222");
        response.addCookie(cookie1);
        response.addCookie(cookie2);

        return "success";
    }
}
