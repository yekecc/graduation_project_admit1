package com.yk.graduation_project_admit.serivice;

import com.yk.graduation_project_admit.pojo.User;
import com.yk.graduation_project_admit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdmitSerivice {

    @Autowired
    UserRepository UserRepository;

    /**
     * @param userNumber
     * @return
     */
    public Optional<User> getAdmit(String userNumber) {
        return UserRepository.findByUsername(userNumber);
    }

}