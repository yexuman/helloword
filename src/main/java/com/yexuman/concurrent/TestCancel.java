package com.yexuman.concurrent;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.concurrent.*;

/**
 * @author yexuman
 * @version 1.0
 * @date 2020/11/13 14:33
 */
public class TestCancel {
    public static void main(String[] args) {
        RunnableWithTimeout<Object> objectRunnableWithTimeout = new RunnableWithTimeout<Object>(1000, TimeUnit.MILLISECONDS) {
            @Override
            public Object runNotingCompletion() {
                System.out.println("开始了");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("结束了");
                return null;
            }

            @Override
            public void executeIfNotDoneAfterTimeout() {
                System.out.println("还没完成，直接结束！");
            }
        };
        ExecutorService threadPool = Executors.newCachedThreadPool();
        System.out.println("提交任务");
        threadPool.submit(objectRunnableWithTimeout);
        try {
            objectRunnableWithTimeout.waitFor();
        } catch (InterruptedException e) {
            System.out.println("等你");

        }
    }
}
