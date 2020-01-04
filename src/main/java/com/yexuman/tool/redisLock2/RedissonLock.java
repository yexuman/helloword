package com.yexuman.tool.redisLock2;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author yexuman
 * @date 2019/10/24 17:07
 */
@Component
@Slf4j
public class RedissonLock {
    /**
     * lock 方法是加锁操作，unLock 方法是解锁操作。
     *
     * 注释中的代码列举类 3中lock 的方法，大家学习更多操作请查看下面博客
     *
     * https://blog.csdn.net/l1028386804/article/details/73523810
     *
     * 教你 RedissonClient 所有操作。
     */
    private static RedissonClient redissonClient = RedissonManager.getRedisson();

    public void lock(String lockName) {
        String key = lockName;
        RLock myLock = redissonClient.getLock(key);
        //lock提供带timeout参数，timeout结束强制解锁，防止死锁
        myLock.lock(2, TimeUnit.SECONDS);
        // 1. 最常见的使用方法
        //lock.lock();
        // 2. 支持过期解锁功能,10秒以后自动解锁, 无需调用unlock方法手动解锁
        //lock.lock(10, TimeUnit.SECONDS);
        // 3. 尝试加锁，最多等待3秒，上锁以后10秒自动解锁
//        try {
//            boolean res = mylock.tryLock(3, 10, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.err.println("======lock======" + Thread.currentThread().getName());
    }

    public void unLock(String lockName) {
        String key = lockName;
        RLock myLock = redissonClient.getLock(key);
        myLock.unlock();
        System.err.println("======unlock======" + Thread.currentThread().getName());
    }
}
