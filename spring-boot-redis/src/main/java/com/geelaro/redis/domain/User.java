package com.geelaro.redis.domain;

import java.io.Serializable;

public class User implements Serializable {

    public static final Long serialVersionUID = -1L;


    private String name;
    private Integer age;

    public User(String name,Integer age){
        this.name = name;
        this.age = age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
