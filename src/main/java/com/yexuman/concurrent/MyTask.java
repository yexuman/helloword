package com.yexuman.concurrent;

/**
 * @author yexuman
 * @version 1.0
 * @date 2020/11/16 10:30
 */
public class MyTask  implements Runnable   {
    @Override
    public void run()  {
        int count = 0;
        System.out.println("开始了##"+Thread.currentThread().getName());
        for (int i = 0; i < 30000; i++) {
            for (int j = 0; j < 300000; j++) {
                if (i == j) {

                    count++;

                }
            }
        }

        System.out.println("结束了##"+Thread.currentThread().getName());

    }
}
