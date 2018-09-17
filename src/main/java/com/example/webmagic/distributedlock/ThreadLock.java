package com.example.webmagic.distributedlock;

public class ThreadLock implements Runnable {

    private RedisLockService redisLockService;

    public ThreadLock(RedisLockService redisLockService){
        this.redisLockService = redisLockService;
    }

    @Override
    public void run() {
        redisLockService.executeLockService();
    }
}