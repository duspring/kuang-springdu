package com.kuang.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author: spring du
 * @description:
 * @date: 2020/12/4 11:23
 */
public class LockDemo7 {
    /**
     * 同被static+synchronized 修饰的两个方法，是先sendMessage()还是callPhone()?
     *  答案：sendMessage
     *  解释：只要方法被 static 修饰，锁的对象就是 Class模板对象,这个则全局唯一
     *  所以说这里是同一个锁，并不是因为synchronized
     */
    public static void main(String[] args) throws InterruptedException {
        Phone7 phoneA = new Phone7();
        Phone7 phoneB = new Phone7();

        new Thread(() -> {
            try {
                phoneA.sendMessage();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            phoneB.callPhone();
        }, "B").start();
    }
}

class Phone7 {

    public static synchronized void sendMessage() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sendMessage");
    }

    public static synchronized void callPhone() {
        System.out.println("callPhone");
    }

}
