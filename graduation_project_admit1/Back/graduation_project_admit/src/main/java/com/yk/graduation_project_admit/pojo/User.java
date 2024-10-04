package com.yk.graduation_project_admit.pojo;

import jakarta.persistence.*;

@Table(name = "tb_user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Integer userID;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "userClass")
    private String userClass;
    @Column(name = "office")
    private String office;
    @Column(name = "userNumber")
    private String userNumber;

    public User() {

    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public User(Integer userID, String username, String password, String telephone, String userClass, String office, String userNumber) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.userClass = userClass;
        this.office = office;
        this.userNumber = userNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", telephone='" + telephone + '\'' +
                ", userClass='" + userClass + '\'' +
                ", office='" + office + '\'' +
                ", userNumber='" + userNumber + '\'' +
                '}';
    }
}