package com.example.webmagic.spider;

import com.example.webmagic.spider.pipeline.GithubPipeline;
import com.example.webmagic.spider.pipeline.WebMagicPipeline;
import com.example.webmagic.spider.processor.GithubPageProcessor;
import com.example.webmagic.spider.processor.WebMagicProcessor;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

@Service
public class SpiderService {

    public void spiderStart(){
        Spider.create(new WebMagicProcessor())
                .addUrl("http://webmagic.io/docs/zh/")
                .addPipeline(new ConsolePipeline())
                .addPipeline(new WebMagicPipeline())
                .thread(5)
                .run();
    }
}
