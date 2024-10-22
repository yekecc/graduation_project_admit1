package com.yk.graduation_project_admit.pojo;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
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
    @Column(name = "openid")
    private String openid;

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