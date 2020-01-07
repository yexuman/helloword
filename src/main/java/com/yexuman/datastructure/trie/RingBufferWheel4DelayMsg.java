package com.yexuman.datastructure.trie;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yexuman
 * @date 2020/1/7 10:57
 */
public class RingBufferWheel4DelayMsg {

    public static void main(String[] args) {
        RingBufferWheel4DelayMsg test=new RingBufferWheel4DelayMsg();
        test.test();
    }
    public void test(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        RingBufferWheel ringBufferWheel = new RingBufferWheel(executorService);
        RingBufferWheel.Task task=new DelayMsgJob("这是一条延时消息");
        task.setKey(10);
        ringBufferWheel.addTask(task);
    }



    private class DelayMsgJob extends RingBufferWheel.Task {

        private String msg;

        public DelayMsgJob(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            System.out.println(msg);

        }
    }
}
