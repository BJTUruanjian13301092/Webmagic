package com.example.webmagic.controller;

import com.example.webmagic.distributedlock.RedisLockService;
import com.example.webmagic.distributedlock.ThreadLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    RedisLockService redisLockService;

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
