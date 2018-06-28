package com.example.webmagic.spider.pipeline;

import com.example.webmagic.data.mapper.InformationMapper;
import com.example.webmagic.util.GraphUtil;
import com.example.webmagic.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.IOException;
import java.util.Map;

@Component
public class WebMagicPipeline implements Pipeline {

    @Autowired
    InformationMapper informationMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {

        //图片处理
        if(resultItems.get("imageUrl") != null){

            String imageUrl = resultItems.get("imageUrl").toString();

            String imageFileType = HttpUtil.getFileTypeByUrl(imageUrl);
            String fileName = null;
            try {
                fileName = HttpUtil.downloadFile(imageUrl, imageFileType);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //GraphUtil.rotateImage(fileName, "image\\" + System.currentTimeMillis() + "." + imageFileType, 30.0);
            //GraphUtil.addWaterText(fileName, "image\\" + System.currentTimeMillis() + "." + imageFileType, "hahaha");
        }

        String title = resultItems.get("title").toString();
        informationMapper.insertInfo(title, null);

    }
}
