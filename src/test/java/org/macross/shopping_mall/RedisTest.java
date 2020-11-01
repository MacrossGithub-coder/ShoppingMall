package org.macross.shopping_mall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    @Qualifier("redisTemplateMaster")
    private RedisTemplate<String, Object> redisTemplateMaster;

    @Test
    public void main(){
        Long aLong = redisTemplateMaster.opsForList().leftPush("10", "3121231233");
        System.out.println(aLong.toString());
        Object o = redisTemplateMaster.opsForList().rightPop("10");
        System.out.println(o.toString());
//        redisTemplateMaster.opsForList().leftPush("10", "3");
//        redisTemplateMaster.opsForList().leftPush("10", "4");

//        Long size = redisTemplateMaster.opsForList().size("10");
//        System.out.println(size.toString());
//
//        Object o = redisTemplateMaster.opsForList().rightPop("10");
//        System.out.println(o.toString());
//
//        Long size1 = redisTemplateMaster.opsForList().size("10");
//        System.out.println(size1.toString());
        return;
    }
}
