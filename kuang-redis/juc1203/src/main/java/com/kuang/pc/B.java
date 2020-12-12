package com.kuang.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: spring du
 * @description:
 * @date: 2020/12/4 10:02
 */
public class B {
    public static void main(String[] args) {
        Data2 data2 = new Data2();

        new Thread(()->{for (int i = 0; i < 10; i++) data2.increment();}, "A").start();
        new Thread(()->{for (int i = 0; i < 10; i++) data2.decrement();}, "B").start();
        new Thread(()->{for (int i = 0; i < 10; i++) data2.increment();}, "C").start();
        new Thread(()->{for (int i = 0; i < 10; i++) data2.decrement();}, "D").start();

    }
}


// 判断等待，业务，通知
class Data2{ // 数字 资源类

    private int number = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    //condition.await(); // 等待
    //condition.signalAll(); // 唤醒全部

    // +1
    public void increment() {
        lock.lock();
        try {
            // 业务代码
            while (number!=0) { // 0
                // 等待
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"=>"+number);
            //通知其他线程，我+1完毕了
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // -1
    public void decrement() {
        lock.lock();
        try {
            // 业务代码
            while (number==0){ // 1
                // 等待
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"=>"+number);
            //通知其他线程，我-1完毕了
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}
