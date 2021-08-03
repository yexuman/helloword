package com.yexuman.tool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yexuman
 * @version 1.0
 * @date 2021/6/19 上午11:38
 */
public class Test {

    Queue<Integer> queue = new LinkedList<>();
    int cap = 10;
    Object lock = new Object();

    public void put(Integer e) {
        synchronized (lock) {
            while (!queue.isEmpty() && queue.size() > cap) {
                try {
                    lock.wait();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
            queue.add(e);
            lock.notifyAll();
        }
    }

    public Integer take() {
        int res = 0;
        synchronized (lock) {
            while (queue.isEmpty()) {
                try {
                    lock.wait();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
            res = queue.poll();
            lock.notifyAll();
        }
        return res;
    }

}
