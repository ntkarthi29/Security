package com.basic.base.repository;

import com.basic.base.enums.ERole;
import com.basic.base.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUserName(String userName);
    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    User findByRole(ERole role);
}