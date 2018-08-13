package com.example.webmagic;

import com.example.webmagic.util.GraphUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebmagicApplication.class)
public class UtilTest {

//    @Test
//    public void graphTest() throws Exception {
//
//        Map<String, String> graphInfo = GraphUtil.getImageInfo("image\\1529981616233.jpeg");
//        for(String key : graphInfo.keySet()){
//            System.out.println(key + ": " + graphInfo.get(key));
//        }
//
//        GraphUtil.convertFontToImage("李闱吃屎", "宋体", 20, "990033",
//                "image\\" + System.currentTimeMillis() + ".png");
//
//        GraphUtil.addWaterPic("image\\1529983188324.png", "image\\1529981616233.jpeg",
//                "image\\1529981616233.jpeg", "southwest", 100, 300, 300);
//
//        GraphUtil.zoomImage("image\\1529981616233.jpeg", "image\\" + System.currentTimeMillis() + ".jpeg",
//                160, 100);
//
//        GraphUtil.cutImage("image\\1529981616233.jpeg", "image\\" + System.currentTimeMillis() + ".jpeg",
//                0, 0, 100, 100);
//    }
}
