package com.geelaro.mybatis.controller;

import com.geelaro.mybatis.entity.User;
import com.geelaro.mybatis.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public Page<User> getAll(@RequestParam(defaultValue = "1") int count, @RequestParam(defaultValue = "10") int size) {
        PageHelper.startPage(count, size);
//        PageInfo<User> pageInfo = new PageInfo<>(userService.getAll());
        return userService.getAll();
    }

    @PostMapping("/one")
    public User addUser(@RequestParam String name, @RequestParam Integer age) {
        return userService.insertUser(name, age);
    }
}
