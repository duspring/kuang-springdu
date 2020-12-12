package com.kuang;

import redis.clients.jedis.Jedis;

/**
 * @author: spring du
 * @description:
 * @date: 2020/11/26 15:56
 */
public class TestPassword {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        //验证密码，如果没有设置密码这段代码省略
//        jedis.auth("password");

        jedis.connect(); //连接
        jedis.disconnect(); //断开连接

        jedis.flushAll(); //清空所有的key
    }
}
