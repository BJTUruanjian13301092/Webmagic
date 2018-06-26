package com.example.webmagic.spider.pipeline;

import com.example.webmagic.util.GraphUtil;
import com.example.webmagic.util.HttpUtil;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.IOException;
import java.util.Map;

public class WebMagicPipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {

        if(resultItems.get("imageUrl") != null){

            String imageUrl = resultItems.get("imageUrl").toString();
            String title = resultItems.get("title").toString();

            String imageFileType = HttpUtil.getFileTypeByUrl(imageUrl);
            String fileName = null;
            try {
                fileName = HttpUtil.downloadFile(imageUrl, imageFileType);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //GraphUtil.rotate(fileName, "image\\" + System.currentTimeMillis() + "." + imageFileType, 30.0);
            //GraphUtil.addWaterText(fileName, "image\\" + System.currentTimeMillis() + "." + imageFileType, "hahaha");
        }

    }
}
