package com.kuang.function;

import java.util.function.Supplier;

/**
 * @author: spring du
 * @description: Supplier 供给型接口 没有参数，只有返回值
 * @date: 2020/12/8 15:56
 */
public class Demo04 {
    public static void main(String[] args) {
//        Supplier<Integer> supplier = new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                System.out.println("get()");
//                return 1024;
//            }
//        };

//        Supplier<Integer> supplier = () -> {return 1024;};
        Supplier<Integer> supplier = () -> 1024;

        System.out.println(supplier.get());
    }
}
