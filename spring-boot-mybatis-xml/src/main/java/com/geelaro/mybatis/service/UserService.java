package com.geelaro.mybatis.service;

import com.geelaro.mybatis.entity.User;
import com.geelaro.mybatis.mapper.UserDao;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    @Autowired
    private UserDao userDao;

    public Page<User> getAll(){
        return userDao.getAll();
    }


    public User findUser(int id){
        return userDao.findById(id);
    }

    public User insertUser(String name, Integer age){
        userDao.insertOne(name,age);
        return new User(name,age);
    }
}
