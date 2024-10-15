package com.yk.graduation_project_admit.pojo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "tb_user")
@Entity
@Data
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
    @Column(name = "openid")
    private String openid;

    public User() {
    }

    public User(Integer userID, String username, String password, String telephone, String userClass, String office, String userNumber, String openid) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.userClass = userClass;
        this.office = office;
        this.userNumber = userNumber;
        this.openid = openid;
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
                ", openid='" + openid + '\'' +
                '}';
    }
}