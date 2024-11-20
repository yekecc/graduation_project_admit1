package com.yk.graduation_project_admit.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 用户实体类
 * 对应数据库中的user表
 * 包含用户的基本信息和角色权限
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
@Table(name = "user")
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String openid;

    private String username;

    private String userNumber;

    private String userClass;

    private String telephone;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer role = 0; // 0-学生 1-教师 2-管理员

    private String password;

    @Column(updatable = false)
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
        updateTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = LocalDateTime.now();
    }

    public User orElse(Object o) {
        return null;
    }
}