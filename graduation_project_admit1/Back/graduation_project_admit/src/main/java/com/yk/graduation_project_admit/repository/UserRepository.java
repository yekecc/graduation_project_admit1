package com.yk.graduation_project_admit.repository;

import com.yk.graduation_project_admit.pojo.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * @param username
     * @return
     */
    Optional<User> findByUsername(String username);

    User getByOpenid(String openid);

    boolean existsByOpenid(String openid);
    @Transactional
    @Modifying
    @Query("update User u SET u.username = ?1, u.telephone = ?2, u.userNumber = ?3 where u.openid = ?4")
    void updateUserDetails(String username, String telephone, String userNumber, String openid);
}
