package com.example.webmagic.spider;

import com.example.webmagic.spider.pipeline.WebMagicPipeline;
import com.example.webmagic.spider.processor.BaiduProcessor;
import com.example.webmagic.spider.processor.WebMagicProcessor;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

@Service
public class SpiderService {

    public void webMagicSpider(){

        Spider.create(new WebMagicProcessor())
                .addUrl("http://webmagic.io/docs/zh/")
                .addPipeline(new ConsolePipeline())
                .addPipeline(new WebMagicPipeline())
                .thread(5)
                .run();
    }

    public void baiduSpider(){

        Spider.create(new BaiduProcessor())
                .addUrl("http://www.baidu.com")
                //.setDownloader(new SeleniumDownloader("D:\\workingSoftwares\\Chrome\\chromedriver_win32"))
                .addPipeline(new ConsolePipeline())
                .thread(5)
                .run();
    }
}
