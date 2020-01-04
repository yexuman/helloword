package com.yexuman.tool.threadlocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author yexuman
 * @date 2019/12/24 17:28
 */
public class ThreadLocalTest01 {
    public static void main(String[] args) {
        //新建一个ThreadLocal
        ThreadLocal<String> local = new ThreadLocal<>();
        //新建一个随机数类
        Random random = new Random();
        //使用java8的Stream新建5个线程
        IntStream.range(0, 5).forEach(a -> new Thread(() -> {
            //为每一个线程设置相应的local值
            local.set(a + ":" + random.nextInt(10));
            System.out.println("线程和local值分别是  " + local.get());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start());
    }
    /**
     * 每一个线程都有各自的local值，我们设置了一个休眠时间，就是为了另外一个线程也能够及时的读取当前的local值
     */

    /**
     * 内部源码很简单，现在我们总结一波
     *
     * （1）每个Thread维护着一个ThreadLocalMap的引用
     *
     * （2）ThreadLocalMap是ThreadLocal的内部类，用Entry来进行存储
     *
     * （3）ThreadLocal创建的副本是存储在自己的threadLocals中的，也就是自己的ThreadLocalMap。
     *
     * （4）ThreadLocalMap的键值为ThreadLocal对象，而且可以有多个threadLocal变量，因此保存在map中
     *
     * （5）在进行get之前，必须先set，否则会报空指针异常，当然也可以初始化一个，但是必须重写initialValue()方法。
     *
     * （6）ThreadLocal本身并不存储值，它只是作为一个key来让线程从ThreadLocalMap获取value。
     *
     * 对于ThreadLocal来说关键就是内部的ThreadLocalMap。
     *
     * 最常见的ThreadLocal使用场景为 用来解决数据库连接、Session管理
     */

    /**
     * 线程和local值分别是  1:0
     * 线程和local值分别是  0:6
     * 线程和local值分别是  2:7
     * 线程和local值分别是  3:3
     * 线程和local值分别是  4:9
     */
}
