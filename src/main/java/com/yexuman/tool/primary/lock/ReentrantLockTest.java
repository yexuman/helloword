package com.yexuman.tool.primary.lock;

/**
 * @author yexuman
 * @date 2020/1/9 18:54
 */

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 是一把可重入锁和互斥锁，它具有与 synchronized 关键字相同的含有隐式监视器锁（monitor）的基本行为和语义，
 * 但是它比 synchronized 具有更多的方法和功能。
 *
 * ReentrantLock 基本方法：
 * 构造方法
 * ReentrantLock 类中带有两个构造函数，一个是默认的构造函数，不带任何参数；一个是带有 fair 参数的构造函数
 *   public ReentrantLock() {
 *    sync = new NonfairSync();
 *  }
 *
 *  public ReentrantLock(boolean fair) {
 *   sync = fair ? new FairSync() : new NonfairSync();
 *  }
 *  FairSync 和 NonfairSync 都是 ReentrantLock 的内部类，继承于 Sync 类
 *  第二个构造函数也是判断 ReentrantLock 是否是公平锁的条件，如果 fair 为 true，则会创建一个公平锁的实现，也就是
 *  new FairSync()，如果 fair 为 false，则会创建一个 非公平锁的实现，也就是 new NonfairSync()，默认的情况下创建的是非公平锁
 *
 *  通常情况下，使用多线程访问公平锁的效率会非常低（通常情况下会慢很多），但是 ReentrantLock 会保证每个线程都会公平的持有锁，
 *  线程饥饿的次数比较小。锁的公平性并不能保证线程调度的公平性。
 *
 *  在创建完公平锁/非公平锁后，调用lock方法会进行加锁，最终都会调用到acquire()方法
 *  1、首先是第一条路线，tryAcquire 方法，
 *        顾名思义尝试获取，也就是说可以成功获取锁，也可以获取锁失败。
 *        首先会取得当前线程，然后去读取当前锁的同步状态，还记得锁的四种状态吗？分别是 无锁、偏向锁、轻量级锁和重量级锁，
 *        如果是无锁（也就是没有加锁），说明是第一次上锁，首先会先判断一下队列中是否有比当前线程等待时间更长的线程（hasQueuedPredecessors）；
 *        然后通过 CAS 方法原子性的更新锁的状态，CAS 方法更新的要求涉及三个变量，
 *        currentValue(当前线程的值)，expectedValue(期望更新的值)，updateValue(更新的值)，如果既没有排队的线程而且使用 CAS 方法成功的把 0 -> 1 （偏向锁），
 *        那么当前线程就会获得偏向锁，记录获取锁的线程为当前线程。
 *        如果读取的同步状态是1，说明已经线程获取到了锁，那么就先判断当前线程是不是获取锁的线程，如果是的话，记录一下获取锁的次数 + 1，也就是说，
 *        只有同步状态为 0 的时候是无锁状态。如果当前线程不是获取锁的线程，直接返回 false。
 *        acquire 方法会先查看同步状态是否获取成功，如果成功则方法结束返回，也就是 !tryAcquire == false ，若失败则先调用 addWaiter 方法再调用 acquireQueued 方法
 *
 *  2、然后看一下第二条路线 addWaiter
 *       这里首先把当前线程和 Node 的节点类型进行封装，Node 节点的类型有两种，EXCLUSIVE 和 SHARED ，前者为独占模式，后者为共享模式。
 *       首先会进行 tail 节点的判断，有没有尾节点，其实没有头节点也就相当于没有尾节点，如果有尾节点，就会原子性的将当前节点插入同步队列中，
 *       再执行 enq 入队操作，入队操作相当于原子性的把节点插入队列中。如果当前同步队列尾节点为null，说明当前线程是第一个加入同步队列进行等待的线程。
 *
 *  3、在看第三条路线 acquireQueued
 *       主要会有两个分支判断，首先会进行无限循环中，循环中每次都会判断给定当前节点的先驱节点，如果没有先驱节点会直接抛出空指针异常，直到返回 true。
 *       然后判断给定节点的先驱节点是不是头节点，并且当前节点能否获取独占式锁，如果是头节点并且成功获取独占锁后，队列头指针用指向当前节点，然后释放前驱节点。
 *       如果没有获取到独占锁，就会进入 shouldParkAfterFailedAcquire 和 parkAndCheckInterrupt 方法中
 */
public class ReentrantLockTest {

    public void test(){
        ReentrantLock unFairLock= new ReentrantLock(false);
    }
}
