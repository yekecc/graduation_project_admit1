package com.yk.graduation_project_admit.pojo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class RoomDto {
    private Integer RoomID;//房间id
    private String RoomName;//房间名
    private String RoomDescription;//房间描述
    private String RoomAddress;//房间地址
    private String RoomType; //房间类型：音乐室、美术室…………
    private Integer status01;//早上8.是否可借
    private Integer status02;//早上10.是否可借
    private Integer status03;//下午1.是否可借
    private Integer status04;//下午3.是否可借

    public RoomDto(Integer roomID, String roomName, String roomDescription, String roomAddress, String roomType, Integer status01, Integer status02, Integer status03, Integer status04) {
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

    public RoomDto() {
    }
}