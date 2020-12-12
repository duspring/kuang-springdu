package com.kuang.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: spring du
 * @description:
 * @date: 2020/12/4 15:35
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // new Thread(new MyThread()).start();
        // new Thread(new Runnable()).start();
        // new Thread(new FutureTask<V>()).start();
        // new Thread(new FutureTask<V>(Callable<V> callable)).start();
        new Thread().start();  // 怎么启动Callable

        MyThread myThread = new MyThread();
        FutureTask futureTask = new FutureTask(myThread); // 适配类

        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start(); // 结果会被缓存，效率高

        // 这个get方法可能会产生阻塞，把他放到最后
        Integer o = (Integer) futureTask.get(); // 获取 Callable的返回结果
        // 或者使用异步通信来处理！
        System.out.println(o);
    }
}

//class MyThread implements Runnable {
//    @Override
//    public void run() {
//      // TODO
//    }
//}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call()"); // 会打印几个call? 一个
        //耗时的操作
        return 1024;
    }
}
