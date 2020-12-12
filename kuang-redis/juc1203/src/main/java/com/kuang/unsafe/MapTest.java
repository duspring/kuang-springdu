package com.kuang.unsafe;

import javax.swing.plaf.nimbus.State;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: spring du
 * @description:
 * @date: 2020/12/4 15:13
 *
 * ConcurrentModificationException
 *
 */
public class MapTest {
    public static void main(String[] args) {
        // map 是这样用的吗？ 不是，工作中不用HashMap
        // 默认等价于什么？ new HashMap<>(16, 0.75); 加载因子、初始化容量

        // Map<String, Object> map = new HashMap<>();
        // 唯一的一个家庭作业：研究ConcurrentHashMap的原理
        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }


    }
}
