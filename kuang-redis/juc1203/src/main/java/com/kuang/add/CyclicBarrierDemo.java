package com.kuang.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: spring du
 * @description:
 * @date: 2020/12/4 16:51
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        /**
         * 集齐7颗龙珠召唤神龙
         */
        // 召唤龙珠的线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{
            System.out.println("召唤神龙成功！");
        });

        for (int i = 1; i <= 7; i++) {
            final int temp = i;
            // lambda这里不能操作到i
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"收集"+temp+"颗龙珠");

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }

}
