package com.geelaro.mybatis.mapper;

import com.geelaro.mybatis.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

    void deleteAll();

    void deleteOne(Integer id);

    void insertOne(String name,Integer age);

    void updateOne(User user);

    User findById(Integer id);

    User findByName(String name);

    Page<User> getAll();

}
