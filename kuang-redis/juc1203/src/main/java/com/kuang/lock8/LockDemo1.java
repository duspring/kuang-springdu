package com.kuang.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author: spring du
 * @description:
 * @date: 2020/12/4 10:47
 */
public class LockDemo1 {
    /**
     * 标准情况下 是先sendMessage()　还是先callPhone()?
     * 答案：sendMessage
     * 解释：被 synchronized 修饰的方式，锁的对象是方法的调用者
     * 所以说这里两个方法调用的对象是同一个，先调用的先执行！
     */
    public static void main(String[] args) throws InterruptedException {
        Phone1 phone1 = new Phone1();

        new Thread(()-> phone1.sendMessage(), "A").start();

        TimeUnit.SECONDS.sleep(3);

        new Thread(()-> phone1.callPhone(),"B").start();
    }
}

class Phone1 {

    public synchronized void sendMessage() {
        System.out.println("sendMessage");
    }

    public synchronized void callPhone() {
        System.out.println("callPhone");
    }

}
