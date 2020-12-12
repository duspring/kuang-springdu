package com.kuang.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: spring du
 * @description:
 * @date: 2020/12/4 14:11
 */
// java.util.ConcurrentModificationException 并发修改异常！
public class ListTest {
    public static void main(String[] args) {
//        List<String> list = Arrays.asList("1", "2", "3");
//        list.forEach(System.out::println);

//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            list.add(UUID.randomUUID().toString().substring(0,5));
//            System.out.println(list);
//        }

        // 并发下 ArrayList 不安全的 Synchronized
        /**
         * 解决方案：
         * 1、 List<String> list = new Vector<>();
         * 2、 List<String> list = Collections.synchronizedList(new ArrayList<>());
         * 3、 List<String> list = new CopyOnWriteArrayList<>();
         *
         */
//        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        // CopyOnWrite 写入时复制 COW 计算机程序设计领域的一种优化策略；
        // 多个线程调用的时候，list, 读取的时候，固定的，写入（存在覆盖操作问题）
        // 解决方案：CopyOnWrite 即写入时复制一份，复制完后给调用者，调用者写完之后放回去创建
        // 在写入的时候避免覆盖，造成数据问题！

        // 读写分离 Mycat
        // CopyOnWriteArrayList 比 Vector 牛逼在哪？ 效率高  Vector用Synchronized效率低 CopyWriteArrayList用的lock锁

        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }
}
