package com.yk.graduation_project_admit.pojo.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class addRoom_dto {
    private String roomName;
    private String roomAddress;
    private String roomType;
    private String capacity;
    private String equipment;
    private boolean status;// 默认状态为可用
}
