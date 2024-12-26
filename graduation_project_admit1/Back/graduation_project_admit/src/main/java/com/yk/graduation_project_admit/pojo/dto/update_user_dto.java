package com.yk.graduation_project_admit.pojo.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
public class update_user_dto {
    private String id;
    private String username;
    private String userNumber;
    private String telephone;
    private int role;
}
