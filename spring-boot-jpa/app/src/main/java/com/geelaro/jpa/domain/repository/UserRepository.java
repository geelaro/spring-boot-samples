package com.geelaro.jpa.domain.repository;

import com.geelaro.jpa.domain.entity.User;
import org.hibernate.annotations.Cache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {


    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

    @Query("from j_user u where u.name=:name")
    User findUser(@Param("name") String name);

    Long countUserById(Long id);

    User queryAllById(Long id);

    Page<User> findAll(Pageable pageable);

    Page<User> findAllByName(String name, Pageable pageable);

}

