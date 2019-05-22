package com.geelaro.multimongo.repository.primary;

import com.geelaro.multimongo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,Long> {

    User findByUserName(String userName);
}
