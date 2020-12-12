package com.kuang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author: spring du
 * @description:
 * @date: 2020/11/27 16:07
 */

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
// 在企业中，我们的所有pojo 都会序列化！
public class User implements Serializable {

    private String name;

    private int age;
}
