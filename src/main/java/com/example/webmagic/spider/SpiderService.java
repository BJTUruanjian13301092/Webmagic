package com.example.webmagic.spider;

import com.example.webmagic.spider.pipeline.GithubPipeline;
import com.example.webmagic.spider.processor.GithubPageProcessor;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

@Service
public class SpiderService {

    public void spiderStart(){
        Spider.create(new GithubPageProcessor())
                .addUrl("https://github.com/code4craft")
                .addPipeline(new ConsolePipeline())
                .addPipeline(new GithubPipeline())
                .thread(5)
                .run();
    }
}
