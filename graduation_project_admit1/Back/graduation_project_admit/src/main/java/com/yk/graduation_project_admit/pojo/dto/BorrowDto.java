package com.yk.graduation_project_admit.pojo.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
public class BorrowDto {
    private Long id;
    private String openid;
    private Integer RoomID;
    private String RoomName;
    private String RoomDescription;
    private String RoomAddress;
    private String RoomType;
    private Integer status01;
    private Integer status02;
    private Integer status03;
    private Integer status04;

}
