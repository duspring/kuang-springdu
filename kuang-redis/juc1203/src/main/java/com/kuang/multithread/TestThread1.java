package com.kuang.multithread;

/**
 * @author: spring du
 * @description:
 * @date: 2020/12/14 14:42
 */
public class TestThread1 extends Thread{

    @Override
    public void run() {
        // run方法线程体
        for (int i = 0; i < 20; i++) {
            System.out.println("我在看代码---" + i);
        }
    }

    public static void main(String[] args) {
        // main 线程，主线程

        // 创建一个线程对象
        TestThread1 testThread1 = new TestThread1();

        // 调用start()方法开启线程
        testThread1.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("我在学习多线程---"+i);
        }
    }
}
