package com.yexuman.tool.forkjoin.RecursiveTaskDemo;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * @author yexuman
 * @date 2019/12/18 11:55
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int[] longs = new int[1000_000_0];
        for (int i = 0; i < 1000_000_0; i++) {
            longs[i] = i;
        }
        Fibonacci fibonacci = new Fibonacci(longs, 0, longs.length);
        long before = System.currentTimeMillis();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>" + fibonacci.isDone());
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 1000);
        //可以自定义 forkjoin线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool(100);
        //使用公共线程池提交任务
        int result = ForkJoinPool.commonPool().invoke(fibonacci);
        long time = System.currentTimeMillis() - before;
        long before1 = System.currentTimeMillis();
        int result1 = sum(longs);
        long time1 = System.currentTimeMillis() - before1;
        System.out.println(result + " " + time + "   " + result1 + "  " + time1);

    }

    public static int sum(int[] ints) {
        int temp = 0;
        for (int i = 0; i < ints.length; i++) {
            temp += ints[i];
        }
        return temp;
    }
}

