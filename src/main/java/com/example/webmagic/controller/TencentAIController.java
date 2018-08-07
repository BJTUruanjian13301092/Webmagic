package com.example.webmagic.controller;

import cn.xsshome.taip.nlp.TAipNlp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tencentAI")
public class TencentAIController {

    private static final String TENCENT_AI_APPKEY = "eSrdLF4D2zaxpOAn";

    private static final String TENCENT_AI_APPID = "2107688849";

    @RequestMapping("/textAnalyze")
    public void textAnalyze() throws Exception {

        TAipNlp aipNlp = new TAipNlp(TENCENT_AI_APPID, TENCENT_AI_APPKEY);
        String session = System.currentTimeMillis() / 1000 + "";

        String seg = aipNlp.nlpWordseg("我想去玩桌游");//分词
        System.out.println(seg);

        String pos = aipNlp.nlpWordpos("我想去玩桌游");//词性标注
        System.out.println(pos);

        String ner = aipNlp.nlpWordner("我想去玩桌游");//专有名词
        System.out.println(ner);

        String syn = aipNlp.nlpWordsyn("我想去玩桌游");//同义词
        System.out.println(syn);

        String com = aipNlp.nlpWordcom("我想去玩桌游");//意图成分
        System.out.println(com);

        String polar = aipNlp.nlpTextpolar("我想去玩桌游");//情感分析
        System.out.println(polar);

        String chat = aipNlp.nlpTextchat(session,"我想去玩桌游");//基础闲聊
        System.out.println(chat);

        String trans1 = aipNlp.nlpTextTrans(0, "我想去玩桌游");//文本翻译（AI Lab）
        System.out.println(trans1);

        String trans2 = aipNlp.nlpTextTranslate("我想去玩桌游", "zh", "en");//文本翻译（翻译君）
        System.out.println(trans2);

        String detect = aipNlp.nlpTextDetect("こんにちは", 0);//语种识别
        System.out.println(detect);

    }
}
