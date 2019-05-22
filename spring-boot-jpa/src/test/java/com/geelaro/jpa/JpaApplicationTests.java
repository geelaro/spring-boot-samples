package com.geelaro.jpa;

import com.geelaro.jpa.domain.UerRepository;
import com.geelaro.jpa.domain.User;
import javafx.application.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.StreamSupport;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaApplicationTests {

    @Autowired
    private UerRepository uerRepository;


    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() {
        uerRepository.save(new User("AAA", 10));
    }

    @Test
    public void testUser() {

        //save 5
        uerRepository.save(new User("AAA", 10));
        uerRepository.save(new User("BBB", 32));
        uerRepository.save(new User("CCC", 52));
        uerRepository.save(new User("DDD", 34));
        uerRepository.save(new User("EEE", 36));

        Assert.assertEquals(5, uerRepository.findAll().size());

        Assert.assertEquals(10, uerRepository.findByName("AAA").getAge().longValue());

        Assert.assertEquals(32, uerRepository.findUser("BBB").getAge().longValue());

        Assert.assertEquals("EEE", uerRepository.findByNameAndAge("EEE", 36).getName());

        uerRepository.delete(uerRepository.findByName("DDD"));

        Assert.assertEquals(4, uerRepository.findAll().size());
    }

    @Test
    public void testJpaCache() {
        User u1 = uerRepository.findByName("AAA");
        System.out.println("第一次查询：" + u1.getAge());

        //
        User u2 = uerRepository.findByName("AAA");
        System.out.println("第二次查询：" + u2.getAge());
        //
        u1.setAge(20);
        uerRepository.save(u1);
        User u3 = uerRepository.findByName("AAA");
        System.out.println("第三次查询：" + u3.getAge());

        System.out.println("Cache Type:" + cacheManager.getClass());
    }
}
