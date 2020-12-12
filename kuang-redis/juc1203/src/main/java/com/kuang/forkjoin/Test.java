package com.kuang.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author: spring du
 * @description:
 * @date: 2020/12/8 18:01
 */

/**
 * 同一个任务，别人效率高你几十倍！
 */

// 3000  6000（ForkJoin） 9000（Stream并行流）
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test1(); // 22099
//        test2(); // 8777
        test3(); // 604
    }

    // 【普通程序猿】
    public static void test1() {
        Long sum = 0L;
        long start = System.currentTimeMillis();
        for (Long i = 1L; i <= 10_0000_0000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        // sum=500000000500000000 时间：22099
        System.out.println("sum="+ sum+" 时间："+(end-start));
    }

    // 会使用ForkJoin 的【中级程序猿】
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task); // 提交任务
        Long sum = submit.get();

        long end = System.currentTimeMillis();

        // sum=500000000500000000 时间：8777
        System.out.println("sum="+ sum +" 时间："+(end-start));
    }

    // 会使用Stream并行流的【高级程序猿】
    public static void test3() {
        long start = System.currentTimeMillis();

        // Stream并行流 (]
//        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, ((left, right) -> {return Long.sum(left, right);}));
        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, ((left, right) -> Long.sum(left, right)));
//        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);

        long end = System.currentTimeMillis();

        // sum=500000000500000000 时间：604
        System.out.println("sum="+ sum + " 时间："+(end-start));
    }
}
