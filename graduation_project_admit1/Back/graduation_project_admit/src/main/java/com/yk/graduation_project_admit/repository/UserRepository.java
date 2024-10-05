package com.yk.graduation_project_admit.repository;

import com.yk.graduation_project_admit.pojo.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}