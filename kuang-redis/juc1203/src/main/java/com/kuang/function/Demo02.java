package com.kuang.function;

import java.util.function.Predicate;

/**
 * @author: spring du
 * 断定型接口：有一个输入参数，返回值只能是 布尔值！
 * @description: 断定型接口：有一个输入参数，返回值只能是 布尔值！
 * @date: 2020/12/8 15:47
 */
public class Demo02 {
    public static void main(String[] args) {
        // 判断字符串是否为空
//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String str) {
//                return str.isEmpty();
//            }
//        };

//        Predicate<String> predicate = (str) -> {return str.isEmpty();};

//        Predicate<String> predicate = str -> str.isEmpty();

        Predicate<String> predicate = String::isEmpty;

        System.out.println(predicate.test(""));

    }
}
