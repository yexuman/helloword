package com.yexuman.tool.delayTask;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.MessageFormat;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yexuman
 * @date 2019/11/18 19:23
 */
public class DelayTaskConsumer {
    //@Resource(name = "redisPoolConfigFactory")
    private JedisPool jedisPool=new JedisPool("192.168.218.131",6379);
    Jedis jedis = jedisPool.getResource();


    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    public void start(){
        scheduledExecutorService.scheduleWithFixedDelay(new DelayTaskHandler(),5,5, TimeUnit.SECONDS);
    }

    public  class DelayTaskHandler implements Runnable{

        @Override
        public void run() {

            try {
                Set<String> ids = jedis.zrangeByScore(Constants.DELAY_TASK_QUEUE, 0, System.currentTimeMillis(), 0, 1);
                if(ids==null||ids.isEmpty()){
                    return;
                }
                for(String id:ids){
                    Long count = jedis.zrem(Constants.DELAY_TASK_QUEUE, id);
                    if(count!=null&&count==1){
                        System.out.println(MessageFormat.format("发布资讯。id - {0} , timeStamp - {1} , " +
                                "threadName - {2}",id,System.currentTimeMillis(),Thread.currentThread().getName()));
                    }
                }
            }finally {
                jedis.close();
            }
        }
    }


}
