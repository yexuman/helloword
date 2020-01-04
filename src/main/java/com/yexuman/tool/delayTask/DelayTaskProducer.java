package com.yexuman.tool.delayTask;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author yexuman
 * @date 2019/11/18 19:23
 */

public class DelayTaskProducer {
//    @Resource(name = "redisPoolConfigFactory")
    private JedisPool jedisPool=new JedisPool("192.168.218.131",6379);
    Jedis jedis = jedisPool.getResource();
    public void produce(String newsId,long timeStamp){
        try {
            jedis.zadd(Constants.DELAY_TASK_QUEUE,timeStamp,newsId);
        }finally {
            jedis.close();
        }
    }
}
