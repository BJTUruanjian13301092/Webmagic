package com.example.webmagic.controller;

import cn.xsshome.taip.face.TAipFace;
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

    /**  具体细节请参考 : https://gitee.com/xshuai/taip/blob/master/README.md  **/

    /**
     * 自然语言处理
     */
    @RequestMapping("/textAnalyze")
    public void textAnalyze() throws Exception {

        String filePath1 = "D:\\projects\\webMagic\\webmagic\\TencentAI_Material\\google.jpg";
        String filePath2 = "D:\\projects\\webMagic\\webmagic\\TencentAI_Material\\voice_ttaSynthesis.mp3";

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

        String nlpSpeechTranslate = aipNlp.nlpSpeechTranslate(6, 0, 1, session, filePath2,"zh", "en");//语音翻译
        System.out.println(nlpSpeechTranslate);
    }

    /**
     * 图像特效
     */
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

    /**
     * 人脸分析
     */
    @RequestMapping("/faceAnalyze")
    public void faceAnalyze() throws Exception {

        String imagePath = "D:\\projects\\webMagic\\webmagic\\TencentAI_Material\\yitong.jpg";
        String imagePath2 = "D:\\projects\\webMagic\\webmagic\\TencentAI_Material\\lufei.jpg";

        TAipFace aipFace = new TAipFace(TENCENT_AI_APPID, TENCENT_AI_APPKEY);
        Gson gson = new Gson();

        /**********个体管理**********/
        String faceNewperson = aipFace.faceNewperson(imagePath, "group01","01","lyt");//个体创建
        System.out.println(faceNewperson);

        String faceSetInfo = aipFace.faceSetInfo("01", "lyt","我就是我,不一样的烟火");//设置信息
        System.out.println(faceSetInfo);

        String faceGetInfo = aipFace.faceGetInfo("01");//获取信息
        System.out.println(faceGetInfo);

        /**********人脸识别**********/
        String faceDetect = aipFace.detect(imagePath);//人脸检测与分析
        System.out.println(faceDetect);

        String faceCompare = aipFace.faceCompare(imagePath, imagePath2);//人脸对比
        System.out.println(faceCompare);

        String faceShape = aipFace.faceShape(imagePath);//五官定位
        System.out.println(faceShape);

        String faceIdentify = aipFace.faceIdentify(imagePath, "group01", 1);//人脸识别
        System.out.println(faceIdentify);

        String faceVerify = aipFace.faceVerify(imagePath, "01");//人脸验证
        System.out.println(faceVerify);

        /**********信息查询**********/
        String groupIds = aipFace.getGroupIds();//获取组列表
        System.out.println(groupIds);

        String personIds = aipFace.getPersonIds("group01");//获取个体列表
        System.out.println(personIds);

        String faceIds = aipFace.getFaceIds("01");//获取人脸列表
        System.out.println(faceIds);

        String faceInfo = aipFace.getFaceInfo("2704311652640119053");
        System.out.println(faceInfo);

    }

    /**
     * 声音处理
     */
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
