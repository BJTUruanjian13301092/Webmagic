package com.example.webmagic.controller;

import com.example.webmagic.data.entity.InformationEntity;
import com.example.webmagic.distributedlock.RedisLockService;
import com.example.webmagic.distributedlock.ThreadLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    RedisLockService redisLockService;

    @Resource
    RedisTemplate<Serializable, Serializable> redisTemplate;

    @RequestMapping("/save/object")
    public void saveJavaObject(){
        InformationEntity informationEntity = new InformationEntity();
        informationEntity.setId(Long.valueOf(1));
        informationEntity.setInfo("my info");
        informationEntity.setTitle("my title");
        redisTemplate.opsForValue().set("test:javaobj", informationEntity);
        InformationEntity info = (InformationEntity) redisTemplate.opsForValue().get("test:javaobj");
        System.out.println("hello~");
    }

    @RequestMapping("/lock")
    public void getLock(){
        redisLockService.executeLockService();
    }

    @RequestMapping("/run/lock")
    public void runLock(){
        for (int i = 0; i < 10; i++) {
            ThreadLock threadA = new ThreadLock(redisLockService);
            threadA.run();
        }
    }
}
