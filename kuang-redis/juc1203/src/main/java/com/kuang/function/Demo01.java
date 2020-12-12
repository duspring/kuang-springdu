package com.kuang.function;

import java.util.function.Function;

/**
 * @author: spring du
 * @description:
 * @date: 2020/12/8 15:41
 */

/**
 * Function 函数型接口, 有一个输入参数，有一个输出
 * 只要是 函数型接口 可以 用 lambda表达式简化
 */
public class Demo01 {
    public static void main(String[] args) {
        // 工具类：输出输入的值
//        Function<String, String> function = new Function<String, String>() {
//            @Override
//            public String apply(String str) {
//                return str;
//            }
//        };

//        Function<String, String> function = (str) -> {return str;};

//        Function<String, String> function = str -> str;

        Function<String, String> function = str -> str;


        System.out.println(function.apply("springdu"));

    }
}
