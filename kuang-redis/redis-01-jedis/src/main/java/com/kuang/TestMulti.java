package com.kuang;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author: spring du
 * @description:
 * @date: 2020/11/26 15:57
 */
public class TestMulti {
    public static void main(String[] args) {
        // 创建客户端连接服务端，redis服务端需要被开启
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        jedis.flushDB();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "world");
        jsonObject.put("name", "java");

        // 开启事务
        Transaction multi = jedis.multi();
        String result = jsonObject.toJSONString();
//        jedis.watch(result);
        try {
            //向redis存入一条数据
            multi.set("user1", result);
            //再存入一条数据
            multi.set("user2", result);
            //这里引发了异常，用0作为被除数
            int i = 1/0; //代码抛出异常，事务执行失败！
            //如果没有引发异常，执行进入队列的命令
            multi.exec(); // 执行事务！
        } catch (Exception e) {
            multi.discard(); // 放弃事务
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close(); // 关闭连接
        }

    }
}
