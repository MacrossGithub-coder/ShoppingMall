package org.macross.shopping_mall;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.junit.jupiter.api.Test;
import org.macross.shopping_mall.model.entity.User;
import org.macross.shopping_mall.service.ShoppingCartOrderService;
import org.macross.shopping_mall.service.impl.ShoppingCartOrderServiceImpl;
import org.macross.shopping_mall.utils.JsonData;
import org.macross.shopping_mall.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;


@SpringBootTest
class ShoppingMallApplicationTests {

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	@Qualifier("redisTemplateSlave1")
	private RedisTemplate redisTemplateSlave1;

	@Test
	void contextLoads() {

//		User user = new User();
//		user.setName("macross");
//		redisUtil.setObj("user",user);
//		System.out.println(redisUtil.getObj("user"));
//		redisTemplate.opsForValue().set("name","哈哈");
//		String name = (String) redisTemplate.opsForValue().get("name");
//		System.out.println(name);
//		redisTemplate.opsForValue().set("token",1,60, TimeUnit.SECONDS);



	}

}
