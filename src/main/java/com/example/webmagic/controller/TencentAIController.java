package com.example.webmagic.controller;

import cn.xsshome.taip.nlp.TAipNlp;
import cn.xsshome.taip.ptu.TAipPtu;
import cn.xsshome.taip.speech.TAipSpeech;
import com.example.webmagic.tencent.entity.TencentAIImageEntity;
import com.example.webmagic.tencent.entity.TencentAISpeechEntity;
import com.example.webmagic.tencent.entity.TencentAIVoiceEntity;
import com.example.webmagic.util.Base64Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tencentAI")
public class TencentAIController {

    private static final String TENCENT_AI_APPKEY = "eSrdLF4D2zaxpOAn";

    private static final String TENCENT_AI_APPID = "2107688849";

    @RequestMapping("/textAnalyze")
    public void textAnalyze() throws Exception {

        String filePath1 = "D:\\projects\\webMagic\\webmagic\\TencentAI_Material\\google.jpg";

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

        String imageTranslate = aipNlp.nlpImageTranslate(filePath1, session, "doc","zh", "en");//图片翻译
        System.out.println(imageTranslate);
    }

    @RequestMapping("/imageSpecialEffect")
    public void imageSpecialEffect() throws Exception {

        String imagePath = "D:\\projects\\webMagic\\webmagic\\TencentAI_Material\\yitong.jpg";
        String imagePathOutput_Cosmetic = "D:\\projects\\webMagic\\webmagic\\TencentAI_Material\\yitong_cosmetic.jpg";
        String imagePathOutput_Decoration = "D:\\projects\\webMagic\\webmagic\\TencentAI_Material\\yitong_decorate.jpg";
        String imagePathOutput_Filter = "D:\\projects\\webMagic\\webmagic\\TencentAI_Material\\yitong_filter.jpg";
        String imagePathOutput_ImageFilter = "D:\\projects\\webMagic\\webmagic\\TencentAI_Material\\yitong_imageFilter.jpg";
        String imagePathOutput_FaceMerge = "D:\\projects\\webMagic\\webmagic\\TencentAI_Material\\yitong_faceMerge.jpg";
        String imagePathOutput_FaceSticker = "D:\\projects\\webMagic\\webmagic\\TencentAI_Material\\yitong_faceSticker.jpg";
        String imagePathOutput_FaceAge = "D:\\projects\\webMagic\\webmagic\\TencentAI_Material\\yitong_faceAge.jpg";

        TAipPtu aipPtu = new TAipPtu(TENCENT_AI_APPID, TENCENT_AI_APPKEY);
        Gson gson = new Gson();
        TencentAIImageEntity tencentAIImageEntity;

        String faceCosmetic = aipPtu.faceCosmetic(imagePath, 23);//人脸美妆
        System.out.println(faceCosmetic);
        tencentAIImageEntity = gson.fromJson(faceCosmetic, TencentAIImageEntity.class);
        Base64Util.generateImage(tencentAIImageEntity.getData().getImage(), imagePathOutput_Cosmetic);

        String faceDecoration = aipPtu.faceDecoration(imagePath, 2);//人脸变妆
        System.out.println(faceDecoration);
        tencentAIImageEntity = gson.fromJson(faceDecoration, TencentAIImageEntity.class);
        Base64Util.generateImage(tencentAIImageEntity.getData().getImage(), imagePathOutput_Decoration);

        String imgFilter = aipPtu.imgFilter(imagePath, 20);//滤镜 天天P图
        System.out.println(imgFilter);
        tencentAIImageEntity = gson.fromJson(imgFilter, TencentAIImageEntity.class);
        Base64Util.generateImage(tencentAIImageEntity.getData().getImage(), imagePathOutput_Filter);

        String visionImgfilter = aipPtu.visionImgfilter(imagePath, 32, String.valueOf(new Date().getTime()));//滤镜 AI Lab\
        System.out.println(visionImgfilter);
        tencentAIImageEntity = gson.fromJson(visionImgfilter, TencentAIImageEntity.class);
        Base64Util.generateImage(tencentAIImageEntity.getData().getImage(), imagePathOutput_ImageFilter);

        String faceMerge = aipPtu.faceMerge(imagePath, 26);//人脸融合
        System.out.println(faceMerge);
        tencentAIImageEntity = gson.fromJson(faceMerge, TencentAIImageEntity.class);
        Base64Util.generateImage(tencentAIImageEntity.getData().getImage(), imagePathOutput_FaceMerge);

        String faceSticker = aipPtu.faceSticker(imagePath, 27);//大头贴
        System.out.println(faceSticker);
        tencentAIImageEntity = gson.fromJson(faceSticker, TencentAIImageEntity.class);
        Base64Util.generateImage(tencentAIImageEntity.getData().getImage(), imagePathOutput_FaceSticker);

        String faceAge = aipPtu.faceAge(imagePath);//颜龄检测
        System.out.println(faceAge);
        tencentAIImageEntity = gson.fromJson(faceAge, TencentAIImageEntity.class);
        Base64Util.generateImage(tencentAIImageEntity.getData().getImage(), imagePathOutput_FaceAge);

    }

    @RequestMapping("/voiceAnalyze")
    public void speechAnalyze() throws Exception {

        String voiceOutPut_ttaSynthesis = "D:\\projects\\webMagic\\webmagic\\TencentAI_Material\\voice_ttaSynthesis.mp3";
        String voiceOutPut_ttaSynthesis2 = "D:\\projects\\webMagic\\webmagic\\TencentAI_Material\\voice_ttaSynthesis2.mp3";
        String voiceOutPut_ttsSynthesis = "D:\\projects\\webMagic\\webmagic\\TencentAI_Material\\voice_ttsSynthesis.mp3";
        String voiceOutPut_ttsSynthesis2 = "D:\\projects\\webMagic\\webmagic\\TencentAI_Material\\voice_ttsSynthesis2.mp3";

        TAipSpeech aipSpeech = new TAipSpeech(TENCENT_AI_APPID, TENCENT_AI_APPKEY);
        Gson gson = new Gson();
        TencentAIVoiceEntity tencentAIVoiceEntity;
        TencentAISpeechEntity tencentAISpeechEntity;
        String text = "Good Morning 刘一童";

        String ttaSynthesis = aipSpeech.TtaSynthesis(text);//语音合成（优图）默认参数
        System.out.println(ttaSynthesis);
        tencentAIVoiceEntity = gson.fromJson(ttaSynthesis, TencentAIVoiceEntity.class);
        Base64Util.generateImage(tencentAIVoiceEntity.getData().getVoice(), voiceOutPut_ttaSynthesis);

        String ttaSynthesis2 = aipSpeech.TtaSynthesis(text,2,1);//语音合成（优图）全部参数
        System.out.println(ttaSynthesis2);
        tencentAIVoiceEntity = gson.fromJson(ttaSynthesis2, TencentAIVoiceEntity.class);
        Base64Util.generateImage(tencentAIVoiceEntity.getData().getVoice(), voiceOutPut_ttaSynthesis2);

        String ttsSynthesis = aipSpeech.TtsSynthesis(text, 1, 3);//语音合成（AI Lab）默认参数
        System.out.println(ttsSynthesis);
        tencentAISpeechEntity = gson.fromJson(ttsSynthesis, TencentAISpeechEntity.class);
        Base64Util.generateImage(tencentAISpeechEntity.getData().getSpeech(), voiceOutPut_ttsSynthesis);

        String ttsSynthesis2 = aipSpeech.TtsSynthesis(text,1,3,0,100,0,58);//语音合成（AI Lab）全部参数
        System.out.println(ttsSynthesis2);
        tencentAISpeechEntity = gson.fromJson(ttsSynthesis2, TencentAISpeechEntity.class);
        Base64Util.generateImage(tencentAISpeechEntity.getData().getSpeech(), voiceOutPut_ttsSynthesis2);

        //需要回调
        String filePath = voiceOutPut_ttsSynthesis;
        String detectkeywordBySpeech = aipSpeech.aaiDetectkeywordBySpeech(filePath, 1, "http://www.xxxxx.com//txnotify", "刘一童",8000);//关键词检索基于本地语音文件
        System.out.println(detectkeywordBySpeech);

    }


}
