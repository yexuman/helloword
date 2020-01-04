package com.yexuman.tool.forkjoin.RecursiveActionDemo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author yexuman
 * @date 2019/12/18 11:52
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoin = new ForkJoinPool();
        forkJoin.submit(new PrintTask(0, 200));
        forkJoin.awaitTermination(2, TimeUnit.SECONDS);
        forkJoin.shutdown();
    }
}
