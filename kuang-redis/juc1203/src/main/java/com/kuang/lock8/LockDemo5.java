package com.kuang.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author: spring du
 * @description:
 * @date: 2020/12/4 11:12
 */
public class LockDemo5 {
    /**
     * 两个静态同步方法 都被synchronized 修饰 是先sendMessage() 还是callPhone()？
     * 答案：sendMessage
     * 解释：只要方法被 static 修饰，锁的对象就是 Class模板对象,这个则全局唯一！
     *      所以说这里是同一个锁，并不是因为synchronized  这里程序会从上往下依次执行
     */
    public static void main(String[] args) throws InterruptedException {
        Phone5 phone5 = new Phone5();
        new Thread(()-> {
            try {
                phone5.sendMessage();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> phone5.callPhone(), "B").start();
    }
}

class Phone5 {

    public static synchronized void sendMessage() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sendMessage");
    }

    public static synchronized void callPhone() {
        System.out.println("callPhone");
    }
}
