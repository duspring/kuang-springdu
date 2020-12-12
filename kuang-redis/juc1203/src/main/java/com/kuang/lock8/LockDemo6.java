package com.kuang.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author: spring du
 * @description:
 * @date: 2020/12/4 11:18
 */
public class LockDemo6 {
    /**
     * 被synchronized 修饰的普通方法和静态方法  是先sendMessage() 还是 callPhone()?
     * 答案：callPhone
     * 解释：只要被static修饰锁的是class模板, 而synchronized 锁的是调用的对象
     * 这里是两个锁互不影响，按时间先后执行
     */
    public static void main(String[] args) throws InterruptedException {
        Phone6 phone6 = new Phone6();

        new Thread(() -> {
            try {
                phone6.sendMessage();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            phone6.callPhone();
        }, "B").start();
    }
}

class Phone6 {

    public static synchronized void sendMessage() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sendMessage");
    }

    public synchronized void callPhone() {
        System.out.println("callPhone");
    }
}
