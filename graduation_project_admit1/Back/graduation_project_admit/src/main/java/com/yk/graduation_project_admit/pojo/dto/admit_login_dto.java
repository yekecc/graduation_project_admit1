package com.yk.graduation_project_admit.pojo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class admit_login_dto {
    private String userNumber;
    private String password;

}