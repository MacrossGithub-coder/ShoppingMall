package org.macross.shopping_mall;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
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

import java.io.IOException;
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
