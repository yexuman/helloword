package com.yexuman.tool.threadlocal;

import java.util.concurrent.*;

/**
 * @author yexuman
 * @version 1.0
 * @date 2020/10/15 8:58 下午
 */

public class Test2 {

    //参考链接：https://blog.csdn.net/hbtj_1216/article/details/100511851?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.edu_weight&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.edu_weight

    public static void main(String[] args) {
        /**
         * InheritableThreadLocal继承自ThreadLocal；
         * 重写了ThreadLocal的3个函数；
         * 重写的getMap()、createMap()函数非常重要，操作的是t.inheritableThreadLocals，这两个函数在调用set(T value)和get()函数的时候起到了非常重要的作用；
         * 和ThreadLocal互不影响。
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Boolean> future = executorService.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                if (!Thread.currentThread().isInterrupted()){
                    //业务逻辑
                }
                return true;
            }
        });
        try {
            Boolean o = future.get(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            future.cancel(true);
        }
        // 父线程
        Thread parentThread = new Thread(new Runnable() {

            // 父线程中的线程局部变量
            // private ThreadLocal<String> threadLocal = new ThreadLocal<>();
            private InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

            @Override
            public void run() {
                System.out.println("> 父线程中设置线程的本地变量");
                //set  和  get  都是用的Thread的inheritableThreadLocals属性
                inheritableThreadLocal.set("local variable");
                System.out.println("> 父线程中拿到的线程本地变量是：" + inheritableThreadLocal.get());

                // 在父线程中再起一个子线程
                //new Thread的时候  把父线程的inheritableThreadLocals属性赋给了子线程
                //在创建子线程的时候，将父线程中inheritableThreadLocals对应的ThreadLocalMap中的所有的
                // Entry全部复制到一个新的ThreadLocalMap中，最后将这个ThreadLocalMap赋值给了子线程的inheritableThreadLocals。
                Thread childThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("> 子线程中获取父线程的本地变量");
                        System.out.println("> 子线程中拿到的父线程本地变量是：" + inheritableThreadLocal.get());
                    }
                });
                childThread.setName("子线程!");
                childThread.start();
            }
        });
        parentThread.setName("父线程!");
        parentThread.start();

    }

    public static class MyThreadFactory implements ThreadFactory{
        @Override
        public Thread newThread(Runnable r) {
            return null;
        }
    }
}

