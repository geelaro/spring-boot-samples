package com.geelaro.multimongo.repository.secondary;

import com.geelaro.multimongo.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SecondRepository extends MongoRepository<Role,Long> {

    Role findByRole(String role);
}
