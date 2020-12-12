package com.kuang.function;

import java.util.function.Consumer;

/**
 * @author: spring du
 * @description:
 * @date: 2020/12/8 15:51
 */

/**
 * Consumer 消费型接口: 只有输入，没有返回值
 */

public class Demo03 {
    public static void main(String[] args) {
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String str) {
//                System.out.println(str);
//            }
//        };

//        Consumer<String> consumer = (str) -> {System.out.println(str);};

//        Consumer<String> consumer = str -> System.out.println(str);

        Consumer<String> consumer = System.out::println;


        consumer.accept("123");
    }
}
