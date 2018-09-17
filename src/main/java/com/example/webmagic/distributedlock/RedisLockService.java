package com.example.webmagic.distributedlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class RedisLockService {

    @Autowired
    StringRedisTemplate redisTemplate;

    private int counter = 100;

    public String lockWithTimeout(String lockName, long lockTimeout, long acquireTimeout){
        // 锁名，即key值
        String lockKey = "lock:" + lockName;
        // 获取锁的超时时间，超过这个时间则放弃获取锁
        Long endTime = System.currentTimeMillis() + acquireTimeout;
        // 随机生成一个value,只有自己生成的标识才能释放自己的锁
        String identifier = UUID.randomUUID().toString();

        while(System.currentTimeMillis() < endTime){
            // 成功设置了锁
            if(redisTemplate.opsForValue().setIfAbsent(lockKey, identifier)){
                redisTemplate.expire(lockKey, lockTimeout, TimeUnit.MILLISECONDS);
                return identifier;
            }
            // Key没有超时时间
            if(redisTemplate.getExpire(lockKey) == -1){
                redisTemplate.expire(lockKey, lockTimeout, TimeUnit.MILLISECONDS);
            }
            // 没有抢占到锁,休息一下接着抢
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 最后都没抢到锁
        return "fail";
    }

    public Boolean releaseLock(String lockName, String identifier){
        // 锁名，即key值
        String lockKey = "lock:" + lockName;
        boolean flag = false;

        redisTemplate.setEnableTransactionSupport(true);
        // 开始监视key,当key发生变化时事务将会被打断
        redisTemplate.watch(lockKey);
        if(identifier.equals(redisTemplate.opsForValue().get(lockKey))){
            redisTemplate.multi();
            redisTemplate.delete(lockKey);
            flag = true;
        }
        List<Object> results = redisTemplate.exec();
        // redis事务回滚,事务没有执行说明Key超时被其他线程抢占
        if(results.isEmpty()){
            flag = false;
        }
        redisTemplate.unwatch();

        // flag=false说明identifier和Key对应的value不相符,说明任务执行时间大于锁的超时时间,锁已经超时被别的线程所拥有,需要视情况回滚所执行的事务
        return flag;
    }

    public void executeLockService(){
        String result = lockWithTimeout("mylock", 1000, 5000);
        if(result.equals("fail")){
            System.out.println("抢占锁失败");
            return;
        }
        System.out.println(Thread.currentThread().getName() + "获得了锁");
        System.out.println(--counter);
        releaseLock("mylock", result);
    }

}
