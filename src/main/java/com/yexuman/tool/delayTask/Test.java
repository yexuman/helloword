package com.yexuman.tool.delayTask;

/**
 * @author yexuman
 * @date 2019/11/20 15:47
 */
public class Test {

    /**
     * Redis实现延时任务，是通过其数据结构ZSET来实现的。ZSET会储存一个score和一个value，可以将value按照score进行排序，而SET是无序的。
     * 延时任务的实现分为以下几步来实现：
     * (1) 将任务的执行时间作为score，要执行的任务数据作为value，存放在zset中；
     * (2) 用一个进程定时查询zset的score分数最小的元素，可以用ZRANGEBYSCORE key -inf +inf limit 0 1 withscores命令来实现;
     * (3) 如果最小的分数小于等于当前时间戳，就将该任务取出来执行，否则休眠一段时间后再查询
     * redis的ZSET是通过跳跃表来实现的，复杂度为O(logN)，N是存放在ZSET中元素的个数。用redis来实现可以依赖于redis自身的持久化来实现持久化，redis的集群来支持高并发和高可用。因此开发成本很小，可以做到很实时。
     * @param args
     */
    public static void main(String[] args) {
        DelayTaskProducer producer=new DelayTaskProducer();
        DelayTaskConsumer consumer=new DelayTaskConsumer();
        producer.produce("新闻一号",System.currentTimeMillis());
        consumer.start();

    }
}
