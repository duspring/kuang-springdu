package com.kuang.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author: spring du
 * @description:
 * @date: 2020/12/4 11:07
 */
public class LockDemo4 {
    /**
     * 被synchronized 修饰的不同方法 先执行sendMessage() 还是callPhone()？
     * 答案：callPhone
     * 解释：被synchronized 修饰的不同方法 锁的对象是调用者
     * 这里锁的是两个不同的调用者，所有互不影响
     */
    public static void main(String[] args) throws InterruptedException {
        Phone4 phoneA = new Phone4();
        Phone4 phoneB = new Phone4();

        new Thread(()-> {
            try {
                phoneA.sendMessage();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()-> phoneB.callPhone(), "B").start();

    }
}

class Phone4 {

    public synchronized void sendMessage() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sendMessage");
    }

    public synchronized void callPhone() {
        System.out.println("callPhone");
    }

}
