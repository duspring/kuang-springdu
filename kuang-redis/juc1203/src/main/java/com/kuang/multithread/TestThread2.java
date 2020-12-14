package com.kuang.multithread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author: spring du
 * @description: 练习Thread，实现多线程同步下载图片
 * @date: 2020/12/14 14:41
 */
public class TestThread2 implements  Runnable { // extends Thread{

    private String url; //网络图片地址
    private String name; // 保存的文件名

    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    // 下载图片线程的执行体
    @Override
    public void run() {
        WebDownLoader webDownLoader = new WebDownLoader();
        webDownLoader.downloader(url, name);
        System.out.println("下载了文件名为:"+name);
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1463683647,357676853&fm=26&gp=0.jpg", "1.jpg");
        TestThread2 t2 = new TestThread2("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1463683647,357676853&fm=26&gp=0.jpg", "2.jpg");
        TestThread2 t3 = new TestThread2("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1463683647,357676853&fm=26&gp=0.jpg", "3.jpg");

//        t1.start();
//        t2.start();
//        t3.start();
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
    }
}


// 下载器
class WebDownLoader {
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
