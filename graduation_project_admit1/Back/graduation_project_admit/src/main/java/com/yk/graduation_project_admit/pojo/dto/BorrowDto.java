package com.yk.graduation_project_admit.pojo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

    public BorrowDto() {
    }

    public BorrowDto(Long id, String openid, Integer roomID, String roomName, String roomDescription, String roomAddress, String roomType, Integer status01, Integer status02, Integer status03, Integer status04) {
        this.id = id;
        this.openid = openid;
        RoomID = roomID;
        RoomName = roomName;
        RoomDescription = roomDescription;
        RoomAddress = roomAddress;
        RoomType = roomType;
        this.status01 = status01;
        this.status02 = status02;
        this.status03 = status03;
        this.status04 = status04;
    }
}
