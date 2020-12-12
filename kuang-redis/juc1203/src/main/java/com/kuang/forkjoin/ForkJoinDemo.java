package com.kuang.forkjoin;

/**
 * @author: spring du
 * @description:
 * @date: 2020/12/8 17:09
 */

import java.util.concurrent.RecursiveTask;

/**
 * 求和计算的任务！
 * 3000  6000（ForkJoin） 9000（Stream并行流）
 * // 如何使用 forkjoin
 * // 1、forkjoinPool 通过它来执行
 * // 2、计算任务 forkjoinPool.execute(ForkJoinTask task)
 * // 3. 计算类要继承 ForkJoinTask
 */
public class ForkJoinDemo extends RecursiveTask<Long> {
//    public static void main(String[] args) {
//        int sum = 0;
//        for (int i = 1; i <= 10_0000_0000; i++) {
//            sum += i;
//        }
//        System.out.println(sum);
//    }

    private Long start; // 1
    private Long end; // 1999900000

    // 临界值
    private Long temp = 10000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

//    public void test() {
//        if ((end - start) > temp) {
//            // 分支合并计算
//        } else {
//            int sum = 0;
//            for (int i = 1; i <= 10_0000_0000; i++) {
//                sum += i;
//            }
//            System.out.println(sum);
//        }
//    }

    // 计算方法
    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            System.out.println(sum);
            return sum;
        } else { // forkjoin 递归
            // 分支合并计算
            long middle = (start + end) / 2; //中间值
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            task1.fork(); // 拆分任务， 把任务压入线程队列
            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);
            task2.fork(); // 拆分任务， 把任务压入线程队列
            return task1.join() + task2.join();

        }
    }
}
