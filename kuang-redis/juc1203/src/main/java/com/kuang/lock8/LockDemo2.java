package com.kuang.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author: spring du
 * @description:
 * @date: 2020/12/4 10:53
 */
public class LockDemo2 {
    /**
     * sendMessage()休眠三秒后  是先执行sendMessage() 还是 callPhone()
     * 答案： sendMessage
     * 解释：被 synchronized 修饰的方式，锁的对象是方法的调用者
     * 所以说这里两个方法调用的对象是同一个，先调用的先执行！
     */
    public static void main(String[] args) throws InterruptedException {
        Phone2 phone2 = new Phone2();
        new Thread(()-> {
            try {
                phone2.sendMessage();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        TimeUnit.SECONDS.sleep(2); // 休眠2秒
        new Thread(()-> {
            phone2.callPhone();
        }, "B").start();
    }
}

class Phone2 {

    public synchronized void sendMessage() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sendMessage");
    }

    public synchronized void callPhone() {
        System.out.println("callPhone");
    }
}
