package com.yk.graduation_project_admit.repository;

import com.yk.graduation_project_admit.pojo.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    /**
     * @param username
     * @return
     */
    Optional<User> findByUsername(String username);

    /**
     * @param openid
     * @return
     */
    User findByOpenid(String openid);

//    @Query("update User u  SET u.username = ?1 ,u.password = ?2,u.telephone = ?3,u.userClass = ?4,u.office = ?5,u.userNumber = ?6 where u.openid = ?7")
//    User updateUser(String username, String password, String telephone, String userClass, String office, String userNumber, String openid);
}
