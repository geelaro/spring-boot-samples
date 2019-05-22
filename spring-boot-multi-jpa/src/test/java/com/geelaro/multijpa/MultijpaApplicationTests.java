package com.geelaro.multijpa;

import com.geelaro.multijpa.domain.msg.Message;
import com.geelaro.multijpa.domain.msg.MessageRepository;
import com.geelaro.multijpa.domain.user.User;
import com.geelaro.multijpa.domain.user.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultijpaApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageRepository messageRepository;

	@Test
	public void testMultiJpa() {

		//save 5
		userRepository.save(new User("AAA",10));
		userRepository.save(new User("BBB",32));
		userRepository.save(new User("CCC",52));
		userRepository.save(new User("DDD",34));
		userRepository.save(new User("EEE",36));

		Assert.assertEquals(5,userRepository.findAll().size());


		userRepository.delete(userRepository.findByName("CCC"));

		Assert.assertEquals(4,userRepository.findAll().size());

		messageRepository.save(new Message("sam","wonderful"));
		messageRepository.save(new Message("james","you are right"));
		messageRepository.save(new Message("lily","thanks"));

		Assert.assertEquals(3,messageRepository.findAll().size());

	}

}
