package com.yexuman.concurrent.semaphore;

import lombok.val;

import java.util.*;
import java.util.concurrent.Semaphore;

/**
 * @author yexuman
 * @version 1.0
 * @date 2020/10/19 3:12 下午
 */
public class BoundedHashSet<T> {
    //可以使用 Semaphore将任何一种容器变成有界阻塞容器。
    private final Set<T> set;
    private final Semaphore semaphore;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        this.semaphore = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        semaphore.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            if (!wasAdded)
                semaphore.release();
        }
    }

    public boolean remove(Object o) {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved)
            semaphore.release();
        return wasRemoved;
    }


    public static void main(String[] args) throws Exception {
        BoundedHashSet<String> set = new BoundedHashSet<String>(2);
        boolean add = set.add("1");
        System.out.println("add = " + add);

        boolean add1 = set.add("2");
        System.out.println("add1 = " + add1);

        boolean add2 = set.add("3");
        System.out.println("add2 = " + add2);

        boolean remove = set.remove("1");
        System.out.println("remove = " + remove);
    }
}
