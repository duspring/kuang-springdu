package com.kuang.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: spring du
 * @description: A 执行完调用B，B执行完调用C，C执行完调用A
 * @date: 2020/12/4 10:24
 */
public class C {
    public static void main(String[] args) {
        Data3 data3 = new Data3();
        
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printA();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printB();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.printC();
            }
        }, "C").start();
    }
}

class Data3 {  // 资源类 Lock

    private int number = 1; // 1-A 2-B 3-C
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();


    // printA
    public void printA() {
        lock.lock();
        try {
            // 业务代码：判断-> 执行 -> 通知
            while (number != 1) {
                // 等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>AAAAAAA");
            number = 2;
            // 通知其他线程。唤醒，唤醒指定的人，B
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    // printB
    public void printB() {
        lock.lock();
        try {
            // 业务代码：判断-> 执行 -> 通知
            while (number != 2) {
                // 等待
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>BBBBBBB");
            number = 3;
            // 通知其他线程。唤醒，唤醒指定的人，C
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    // printC
    public void printC() {
        lock.lock();
        try {
            // 业务代码：判断-> 执行 -> 通知
            while (number != 3) {
                // 等待
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>CCCCCCC");
            number = 1;
            // 通知其他线程。唤醒，唤醒指定的人，A
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}
