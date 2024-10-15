package com.yk.graduation_project_admit.pojo;

import jakarta.persistence.*;
import lombok.Data;

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
}