package com.kuang;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kuang.pojo.User;
import com.kuang.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @author: spring du
 * @description:
 * @date: 2020/11/27 16:41
 */
@SpringBootTest
public class TestMyRedisTemplate {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test1() {
        redisUtil.set("name", "springdu");
        System.out.println(redisUtil.get("name"));
    }

    @Test
    void contextLoads() {

        // redisTemplate 操作不同的的数据类型，api和我们的指令是一样的
        // opsForValue 操作字符串 类似String
        // opsForList 操作List 类似List
        // opsForSet
        // opsForHash
        // opsForZSet
        // opsForGeo
        // opsForHyperLogLog

        // 除了基本的操作，我们常用的方法可以直接通过redisTemplate操作，比如事务，和基本的CRUD

        // 获取redis的连接对象
//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.flushDb();
//        connection.flushAll();

        redisTemplate.opsForValue().set("mykey", "关注我");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
    }

    @Test
    public void test() throws JsonProcessingException {
        // 真实的开发一般都还使用json来传递对象
        User user = new User("狂神说", 3);
//        String jsonUser = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user",user);
        System.out.println(redisTemplate.opsForValue().get("user")); // User(name=狂神说, age=3)
    }
}
