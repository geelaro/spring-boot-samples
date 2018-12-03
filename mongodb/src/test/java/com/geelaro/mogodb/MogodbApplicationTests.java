package com.geelaro.mogodb;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MogodbApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Before
	public void setUp() {
		userRepository.deleteAll();
	}

	@Test
	public void testMongo(){
		userRepository.save(new User(1L,"alice",22));
		userRepository.save(new User(2L,"James",34));
		userRepository.save(new User(3L,"Lee",28));

		Assert.assertEquals(3, userRepository.findAll().size());

		User u = userRepository.findByUserName("alice");
		Assert.assertEquals(22,u.getAge().intValue());

		//添加一个user
		mongoTemplate.save(new User(4L,"Sam	",45));

		//验证4个user
		Assert.assertEquals(4, userRepository.findAll().size());

		//delete 一个user
		userRepository.deleteById(1L);
		//
		Assert.assertEquals(3, userRepository.findAll().size());


	}

}
