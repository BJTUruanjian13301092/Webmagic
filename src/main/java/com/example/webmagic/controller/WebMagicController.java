package com.example.webmagic.controller;

import com.example.webmagic.spider.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webmagic")
public class WebMagicController {

    @Autowired
    SpiderService spiderService;

    @RequestMapping("/spider-webmagic")
    public void webmagicStart(){
        spiderService.webMagicSpider();
    }

    @RequestMapping("/spider-baidu")
    public void baiduStart(){
        spiderService.baiduSpider();
    }

}
