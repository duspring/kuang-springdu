package com.kuang.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: spring du
 * @description:
 * @date: 2020/12/8 16:38
 */
// 有参，无参构造，get/set/toString方法
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;

    private String name;

    private int age;
}
