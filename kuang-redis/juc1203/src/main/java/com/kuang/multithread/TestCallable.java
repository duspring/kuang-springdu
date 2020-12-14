package com.kuang.multithread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * @author: spring du
 * @description: 创建线程的第三种方式：实现Callable接口
 * @date: 2020/12/14 16:22
 *
 *
 * 如何理解实现Callable接口的方式创建多线程比实现Runnable接口创建多线程方式强大？
 *  1. call()可以有返回值的。
 *  2. call()可以抛出异常，被外面的操作捕获，获取异常的信息
 *  3. Callable是支持泛型的
 */
public class TestCallable implements Callable<Boolean> {

    private String url; //网络图片地址
    private String name; // 保存的文件名

    public TestCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    // 下载图片线程的执行体
    @Override
    public Boolean call() {
        WebDownLoader01 webDownLoader01 = new WebDownLoader01();
        webDownLoader01.downloader(url, name);
        System.out.println("下载了文件名为:"+name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建Callable接口实现类的对象
        TestCallable t1 = new TestCallable("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1463683647,357676853&fm=26&gp=0.jpg", "1.jpg");
        TestCallable t2 = new TestCallable("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1463683647,357676853&fm=26&gp=0.jpg", "2.jpg");
        TestCallable t3 = new TestCallable("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1463683647,357676853&fm=26&gp=0.jpg", "3.jpg");

//        FutureTask<Boolean> futureTask1 = new FutureTask<>(t1);
//        FutureTask<Boolean> futureTask2 = new FutureTask<>(t2);
//        FutureTask<Boolean> futureTask3 = new FutureTask<>(t3);
//
//        new Thread(futureTask1).start();
//        new Thread(futureTask2).start();
//        new Thread(futureTask3).start();
//
//        //获取Callable中call方法的返回值
//        //get()返回值即为FutureTask构造器参数Callable实现类重写的call()的返回值。
//        Boolean boolean1 = futureTask1.get();
//        Boolean boolean2 = futureTask2.get();
//        Boolean boolean3 = futureTask3.get();
//
//        System.out.println(boolean1);
//        System.out.println(boolean2);
//        System.out.println(boolean3);


        // 创建执行服务
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // 提交执行
        Future<Boolean> r1 = executorService.submit(t1);
        Future<Boolean> r2 = executorService.submit(t2);
        Future<Boolean> r3 = executorService.submit(t3);

        // 获取结果
        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();

        System.out.println(rs1);
        System.out.println(rs2);
        System.out.println(rs3);

        // 关闭服务
        executorService.shutdown();

    }
}



// 下载器
class WebDownLoader01 {
    // 下载方法
    public void downloader(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }
}


