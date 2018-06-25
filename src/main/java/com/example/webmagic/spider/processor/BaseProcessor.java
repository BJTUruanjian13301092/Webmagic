package com.example.webmagic.spider.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class BaseProcessor implements PageProcessor {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    public Site site = Site.me()
            .setRetryTimes(3).setSleepTime(1000).setCharset("utf-8")
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36");

    @Override
    public void process(Page page) {

    }

    @Override
    public Site getSite() {
        return site;
    }
}
