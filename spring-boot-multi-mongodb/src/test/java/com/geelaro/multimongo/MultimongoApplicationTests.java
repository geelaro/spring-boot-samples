package com.geelaro.multimongo;

import com.geelaro.multimongo.entity.Role;
import com.geelaro.multimongo.entity.User;
import com.geelaro.multimongo.repository.primary.UserRepository;
import com.geelaro.multimongo.repository.secondary.SecondRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultimongoApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SecondRepository secondRepository;

	@Before
	public void setUp() {
		userRepository.deleteAll();
		secondRepository.deleteAll();
	}

	@Test
	public void testMongo(){
		userRepository.save(new User("1","alice",22));
		userRepository.save(new User("2","James",34));
		userRepository.save(new User("3","Lee",28));

		Assert.assertEquals(3, userRepository.findAll().size());

		User u = userRepository.findByUserName("alice");
		Assert.assertEquals(22,u.getAge().intValue());

		//delete 一个user
		userRepository.delete(u);
		//
		Assert.assertEquals(2, userRepository.findAll().size());


		//Role

		secondRepository.save(new Role(1L,"Admin","admin"));
		secondRepository.save(new Role(2L,"User","user role"));


	}
}
