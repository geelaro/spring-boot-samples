package com.geelaro.redis;

import com.geelaro.redis.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RedisApplicationTests{



	@Autowired
	private StringRedisTemplate stredisTemplate;

	@Autowired
	private RedisTemplate<String,User> redisTemplate;

	@Test
	public void test() {

		stredisTemplate.opsForValue().set("aaa" +
				"","222");

		Assert.assertEquals("222",stredisTemplate.opsForValue().get("aaa"));

		//对象
		User user = new User("超人",23);
		redisTemplate.opsForValue().set(user.getName(),user);

		//
		user = new User("二郎神",34);
		redisTemplate.opsForValue().set(user.getName(),user);
		//
		user = new User("余杭大地",1002);
		redisTemplate.opsForValue().set(user.getName(),user);

		Assert.assertEquals(23,redisTemplate.opsForValue().get("超人").getAge().longValue());

	}

}
