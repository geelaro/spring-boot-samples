package com.geelaro.mybatis;

import com.geelaro.mybatis.entity.User;
import com.geelaro.mybatis.mapper.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {

    @Autowired
    private UserDao userDao;

    @Before
    public void setUp(){
        userDao.deleteAll();
        userDao.insertOne("alice",21);
        userDao.insertOne("sam",34);
    }

    @Test
    public void testUserByXml(){
        User u = userDao.findByName("alice");
        System.out.println("alice: "+ u.toString());

        List<User> list = userDao.getAll();
        list.forEach(user -> System.out.println("UserList: "+user.toString()));
    }


}

