package com.yk.graduation_project_admit.pojo.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
public class UserDto {
    private Integer userID;
    private String username;
    private String password;
    private String telephone;
    private String userClass;
    private String office;
    private String userNumber;
    private String openid;
}