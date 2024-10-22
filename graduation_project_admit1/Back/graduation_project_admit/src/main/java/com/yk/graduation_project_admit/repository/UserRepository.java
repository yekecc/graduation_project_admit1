package com.yk.graduation_project_admit.repository;

import com.yk.graduation_project_admit.pojo.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    User findByOpenid(String openid);
}