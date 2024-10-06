package com.yk.graduation_project_admit.pojo.dto;

public class admit_login_dto {
    private String userNumber;
    private String password;

    public admit_login_dto(String userNumber, String password) {
        this.userNumber = userNumber;
        this.password = password;
    }

    public admit_login_dto() {
    }

    @Override
    public String toString() {
        return "admit_login_dto{" +
                "username='" + userNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String username) {
        this.userNumber = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}