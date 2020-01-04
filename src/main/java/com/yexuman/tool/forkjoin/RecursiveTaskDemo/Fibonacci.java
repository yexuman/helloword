package com.yexuman.tool.forkjoin.RecursiveTaskDemo;

import java.util.concurrent.RecursiveTask;

/**
 * @author yexuman
 * @date 2019/12/18 11:54
 * RecursiveTask 会返回最终结果
 */
public class Fibonacci extends RecursiveTask<Integer> {

    final int[] n;
    final int start;
    final int end;

    /**
     * 任务分段 判断值
     */
    final int part = 1000_0;

    /**
     * @param n     数组
     * @param start 计算区间 开始
     * @param end   计算区间 结束
     */
    public Fibonacci(int[] n, int start, int end) {
        this.n = n;
        this.start = start;
        this.end = end;
        if (start > end || start < 0 || end > n.length) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    protected Integer compute() {
        //小于计算空间 直接求和
        if ((end - start) <= part) {
            //可变参数本质就是先创建了一个数组，该数组的大小就是可变参数的个数
            return sum(start, end, n);
        }
        //进行任务切割 划分
        Fibonacci f1 = new Fibonacci(n, start, start + part);
        //安排在当前任务运行的池中异步执行此任务（如果适用）
        f1.fork();//异步执行 也就是用另一个线程进行运算
        Fibonacci f2 = new Fibonacci(n, start + part, end);
        //join 返回计算结果,这个类似于get， 这个不会抛出异常
        return f2.compute() + f1.join();
    }


    private int sum(int start, int end, int... ints) {
        int temp = 0;
        for (int i = start; i < end; i++) {
            temp += ints[i];
        }
        return temp;
    }

}

