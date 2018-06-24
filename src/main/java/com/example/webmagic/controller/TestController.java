package com.example.webmagic.controller;

import com.example.webmagic.spider.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    SpiderService spiderService;

    @RequestMapping("/myController")
    public String testMyController(){
        spiderService.spiderStart();
        return "This is my controller";
    }
}
