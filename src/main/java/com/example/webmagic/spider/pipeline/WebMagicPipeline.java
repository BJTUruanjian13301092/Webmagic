package com.example.webmagic.spider.pipeline;

import com.example.webmagic.util.GraphUtil;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

public class WebMagicPipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {

        String imageUrl = resultItems.get("imageUrl").toString();
        String title = resultItems.get("title").toString();

        GraphUtil.rotate("image\\source.jpeg", "image\\dest.jpg", 10.0);
    }
}
