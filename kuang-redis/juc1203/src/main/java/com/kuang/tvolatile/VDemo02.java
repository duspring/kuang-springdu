package com.kuang.tvolatile;

/**
 * @author springdu
 * @create 2020/12/12 11:13
 */

// volatile 不保证原子性
public class VDemo02 {

    // volatile 不保证原子性

    private volatile static int num = 0;

    public static void add() {
        num++; // 不是一个原子性操作
    }

    public static void main(String[] args) {

        //理论上num结果应该为 2 万
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount()>2) { // main gcc
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " " + num);

    }

}
