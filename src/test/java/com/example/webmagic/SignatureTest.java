package com.example.webmagic;

import com.example.webmagic.util.EncryptionUtil;
import com.example.webmagic.util.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebmagicApplication.class)
public class SignatureTest {

    @Test
    public void getCommonsPages(){

        Long timeNow = Timestamp.valueOf(LocalDateTime.now()).getTime();

        List<String> listParam = new ArrayList<>();
        listParam.add("page_size=10");
        listParam.add("page_no=1");
        listParam.add("source_id=mp_212513014");
        listParam.add("appkey=testkey");
        listParam.add("ts=" + timeNow.toString());
        String signature = EncryptionUtil.generateSignature(listParam, "testsecret");

        String param = "appkey=testkey&" + "ts=" + timeNow.toString() + "&sig=" + signature + "&page_size=10&page_no=1&source_id=mp_212513014";
        String response = HttpUtil.sendGet("http://test.pinglun.sohuno.com/innerapi/comments/pages", param);
        System.out.println(param);
        System.out.println(response);
    }

    @Test
    public void getTopicDetail(){
        Long timeNow = Timestamp.valueOf(LocalDateTime.now()).getTime();
        List<String> listParam = new ArrayList<>();
        listParam.add("appkey=testkey");
        listParam.add("ts=" + timeNow.toString());
        listParam.add("topicId=212513014");

        String signature = EncryptionUtil.generateSignature(listParam, "testsecret");

        String param = "appkey=testkey&" + "ts=" + timeNow.toString() + "&sig=" + signature + "&topicId=212513014";
        String response = HttpUtil.sendGet("http://test.pinglun.sohuno.com/innerapi/topics/212513014", param);
        System.out.println(param);
        System.out.println(response);
    }

    @Test
    public void postComment(){
        Long timeNow = Timestamp.valueOf(LocalDateTime.now()).getTime();
        List<String> listParam = new ArrayList<>();
        listParam.add("appkey=testkey");
        listParam.add("ts=" + timeNow.toString());
        listParam.add("topic_title=需求依旧偏弱，资金持续改善，国债慢牛可期——海通宏观债券周报");
        listParam.add("source_id=mp_212513014");
        listParam.add("topic_url=http://www.sohu.com/a/212513014_460356");
        listParam.add("content=哈哈哈我就是测试一下啦");
        listParam.add("channel_id=460356");
        listParam.add("ip=127.0.0.1");
        listParam.add("passport=655236036");

        String signature = EncryptionUtil.generateSignature(listParam, "testsecret");
        String param = "sig=" + signature;
        for(String str : listParam){
            param = param + "&" + str;
        }
        String response = HttpUtil.sendPost("http://test.pinglun.sohuno.com/innerapi/v1/comments/", param);
        System.out.println(param);
        System.out.println(response);
    }

}
