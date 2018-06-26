package com.example.webmagic.spider.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

import java.util.List;

public class WebMagicProcessor extends BaseProcessor {

    private static final String HOME_PAGE = "http://webmagic\\.io/docs/zh/$";

    private static final String INTRO_PAGE = "http://webmagic\\.io/docs/zh/posts/.*/$";

    private static final String DETAIL_PAGE = "http://webmagic\\.io/docs/zh/posts/.*/[a-zA-Z_]*.html$";

    @Override
    public void process(Page page) {

        if(page.getUrl().regex(HOME_PAGE).match()){
            logger.info("This is the home page");
            List<String> listTarget = page.getHtml().xpath("//*[@class='chapter']/a/@href").all();
            page.addTargetRequests(listTarget);

            String imageUrl = page.getHtml().xpath("//*[@id='book-search-results']/div[1]/section/p[1]/img/@src").toString();
            page.putField("imageUrl", imageUrl);
        }

        if(page.getUrl().regex(INTRO_PAGE).match()){
            logger.info("This is the intro page");
        }

        if(page.getUrl().regex(DETAIL_PAGE).match()){
            logger.info("This is the detail page");
        }

        String title = page.getHtml().xpath("//*[@class='normal markdown-section']/[1]/text()").toString();
        page.putField("title", title);

    }

    @Override
    public Site getSite() {
        return site;
    }
}