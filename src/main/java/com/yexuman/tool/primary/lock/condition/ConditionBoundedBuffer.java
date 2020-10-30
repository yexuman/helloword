package com.yexuman.tool.primary.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yexuman
 * @version 1.0
 * @date 2020/10/30 10:29 上午
 */
public class ConditionBoundedBuffer<T> {
    protected final Lock lock = new ReentrantLock();

    //条件谓词：notFull
    private final Condition notFull = lock.newCondition();
    //条件谓词：notEmpty
    private final Condition notEmpty = lock.newCondition();
    private final T[] items;
    private int tail, head, count;

    protected ConditionBoundedBuffer(int size) {
        items = (T[]) new Object[size];
    }

    /**
     * 阻塞并直到notFull
     *
     * @param x
     * @throws InterruptedException
     */
    public void put(T x) throws InterruptedException {
        System.out.println("尝试获得锁2");
        lock.lock();
        try {
            System.out.println("加锁2");
            while (count == items.length) {
                // 阻塞，等待非满条件
                System.out.println("缓存已满");
                notFull.await();
            }

            items[tail] = x;
            if (++tail == items.length) {
                tail = 0;
            }

            ++count;
            notEmpty.signal();
            System.out.println("缓存已加入元素，通知非空状态");

        } finally {
            lock.unlock();
            System.out.println("解锁2");
        }
    }


    /**
     * 阻塞并直到notEmpty
     *
     * @return
     * @throws InterruptedException
     */
    public T take() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("加锁1");
            while (count == 0) {
                // 阻塞，等待非空条件
                System.out.println("缓存为空");
                notEmpty.await(); //现在有界缓存为空，要等到非空状态才能取出元素
            }

            T x = items[head];
            items[head] = null;
            if (++head == items.length) {
                head = 0;
            }
            --count;
            notFull.signal(); //元素已被取出，通知非满状态
            System.out.println("元素已被取出，通知非满状态");

            return x;
        } finally {
            lock.unlock();
            System.out.println("解锁1");
        }

    }

    public static void main(String args[]) throws InterruptedException {

        final ConditionBoundedBuffer buffer = new ConditionBoundedBuffer(10);

        //线程t2打印缓存中的消息
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        buffer.take();
                        //System.out.println(buffer.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread.sleep(2000);
        //线程t1放入缓存消息
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        buffer.put("sadsasd");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t2.start();
        t1.start();
    }

}
