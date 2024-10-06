package com.yk.graduation_project_admit.serivice;

import com.yk.graduation_project_admit.pojo.User;

import java.util.Optional;

public interface IAdmitSerivice {
    /**
     *
     * @param userName
     * @return
     */
    Optional<User> getAdmit(String userName);
}