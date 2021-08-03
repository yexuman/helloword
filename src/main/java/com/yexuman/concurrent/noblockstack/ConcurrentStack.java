package com.yexuman.concurrent.noblockstack;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author yexuman
 * @version 1.0
 * @date 2020/11/2 19:52
 */
public class ConcurrentStack<E> {
    AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();

    //push方法创建一个新的节点，该节点的next域指向当前的栈顶，然后使用CAS把这个新节点放入栈顶。
    //如果在开始插入节点时，位于栈顶的节点没有发生变化，那么CAS就会成功。
    public void push(E item) {  //根据栈的当前状态来更新节点
        Node<E> newHead = new Node<E>(item);
        Node<E> oldHead;
        do {
            oldHead = top.get();
            newHead.next = oldHead;
        } while (!top.compareAndSet(oldHead, newHead));//如果栈顶点发生变化则CAS失败,重新执行
    }

    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = top.get();
            if (oldHead == null)
                return null;
            newHead = oldHead.next;
        } while (!top.compareAndSet(oldHead, newHead)); //如果栈顶点发生变化则CAS失败,重新执行
        return oldHead.item;
    }

    private static class Node<E> {
        public final E item;
        public Node<E> next;  //下一个节点

        public Node(E item) {
            this.item = item;
        }
    }

    public static void main(String[] args) {
        AtomicStampedReference atomicStampedReference = new AtomicStampedReference("abc",1);
    }
}