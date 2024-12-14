package com.yk.graduation_project_admit.pojo.dto;

import lombok.*;

/**
 * 用户数据传输对象
 * 用于前后端交互时传递用户信息
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
public class UserDto {
    private long id;
    private String openid;
    private String username;
    private String userNumber;
    private String telephone;
    private int role;
}