package com.yexuman.tool.forkjoin.RecursiveActionDemo;

import java.util.concurrent.RecursiveAction;

/**
 * @author yexuman
 * @date 2019/12/18 11:51
 * RecursiveAction 用于大多数不返回结果的计算
 */
public class PrintTask extends RecursiveAction {
    private final int Max = 50;
    private int start;
    private int end;
    public PrintTask(int start,int end){
        this.start = start;
        this.end = end;
    }
    @Override
    protected void compute() {
        if((end - start)<Max){
            for(int i=start;i<end;i++){
                System.out.println("当前线程："+Thread.currentThread().getName()+" i :"+i);
            }
        }else{
            int middle = (start+end)/2;
            PrintTask left = new PrintTask(start, middle);
            PrintTask right = new PrintTask(middle, end);
            left.fork();
            right.fork();
        }
    }

}

