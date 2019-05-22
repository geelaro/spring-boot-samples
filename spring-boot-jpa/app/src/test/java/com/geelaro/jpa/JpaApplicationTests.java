package com.geelaro.jpa;

import com.geelaro.jpa.domain.repository.IpRepository;
import com.geelaro.jpa.domain.repository.UserRepository;
import com.geelaro.jpa.domain.entity.IpAddress;
import com.geelaro.jpa.domain.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IpRepository ipRepository;


    @Autowired
//    private CacheManager cacheManager;

    @Before
    public void setUp() {
        ipRepository.deleteAll();
//        userRepository.save(new User("BBB",22));
    }


    @Test
    public void testIp() {
        ipRepository.save(new IpAddress(1, "127.0.0.1"));

        IpAddress ip = ipRepository.findByIp("127.0.0.1");
        Assert.assertEquals(1, ip.getTimes());

    }

    @Test
    public void testUser() {

        List<User> list = new ArrayList<>();
        for (int i = 1; i < 60; i++) {
            Random random = new Random();
            list.add(new User("A" + i, Math.abs(random.nextInt(100))));
            list.add(new User("B" + i, Math.abs(random.nextInt(100))));
            list.add(new User("C" + i, Math.abs(random.nextInt(100))));
            list.add(new User("D" + i, Math.abs(random.nextInt(100))));
            list.add(new User("E" + i, Math.abs(random.nextInt(100))));
            userRepository.saveAll(list);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //save 5
//        userRepository.save(new User("AAA", 10));
//        userRepository.save(new User("BBB", 32));
//        userRepository.save(new User("CCC", 52));
//        userRepository.save(new User("DDD", 34));
//        userRepository.save(new User("EEE", 36));
//
//        Assert.assertEquals(5, userRepository.findAll().size());
//
//        Assert.assertEquals(10, userRepository.findByName("AAA").getAge().longValue());
//
//        Assert.assertEquals(32, userRepository.findUser("BBB").getAge().longValue());
//
//        Assert.assertEquals("EEE", userRepository.findByNameAndAge("EEE", 36).getName());
//
//        userRepository.delete(userRepository.findByName("DDD"));
//
//        Assert.assertEquals(4, userRepository.findAll().size());
    }

//    @Test
//    public void testJpaCache() {
//        User u1 = userRepository.findByName("AAA");
//        System.out.println("第一次查询：" + u1.getIp());
//
//        //
//        User u2 = userRepository.findByName("AAA");
//        System.out.println("第二次查询：" + u2.getIp());
//        //
//        u1.setIp(20);
//        userRepository.save(u1);
//        User u3 = userRepository.findByName("AAA");
//        System.out.println("第三次查询：" + u3.getIp());
//
//        System.out.println("Cache Type:" + cacheManager.getClass());
//    }
}
