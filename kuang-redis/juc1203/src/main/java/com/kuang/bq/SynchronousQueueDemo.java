package com.kuang.bq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: spring du
 * @description: 同步队列 和其他的BlockingQueue 不一样， SynchronousQueue 不存储元素
 * put了一个元素，必须从里面先take取出来，否则不能在put进去值！
 * @date: 2020/12/7 16:01
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>(); // 同步队列

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+ " put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+ " put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+ " put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "T1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+ "=>" + blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+ "=>" + blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+ "=>" + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();

        /**
         * T1 put 1
         * T2=>1
         * T1 put 2
         * T2=>2
         * T1 put 3
         * T2=>3
         */
    }
}
