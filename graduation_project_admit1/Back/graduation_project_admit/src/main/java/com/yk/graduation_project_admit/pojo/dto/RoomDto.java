package com.yk.graduation_project_admit.pojo.dto;

import jakarta.persistence.Column;

public class RoomDto {
    private Integer RoomID;//房间id
    private String RoomName;//房间名
    private String RoomDescription;//房间描述
    private String RoomAddress;//房间地址
    private String RoomeType; //房间类型：音乐室、美术室…………
    private Integer status01;//早上8.是否可借
    private Integer status02;//早上10.是否可借
    private Integer status03;//下午1.是否可借
    private Integer status04;//下午3.是否可借


    public String getRoomeType() {
        return RoomeType;
    }

    public void setRoomeType(String roomeType) {
        RoomeType = roomeType;
    }

    public RoomDto() {
    }

    public RoomDto(Integer roomID) {
        RoomID = roomID;
    }

    public Integer getRoomID() {
        return RoomID;
    }

    public void setRoomID(Integer roomID) {
        RoomID = roomID;
    }


    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public String getRoomDescription() {
        return RoomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        RoomDescription = roomDescription;
    }

    public String getRoomAddress() {
        return RoomAddress;
    }

    public void setRoomAddress(String roomAddress) {
        RoomAddress = roomAddress;
    }


    public Integer getStatus01() {
        return status01;
    }

    public void setStatus01(Integer status01) {
        this.status01 = status01;
    }

    public Integer getStatus02() {
        return status02;
    }

    public void setStatus02(Integer status02) {
        this.status02 = status02;
    }

    public Integer getStatus03() {
        return status03;
    }

    public void setStatus03(Integer status03) {
        this.status03 = status03;
    }

    public Integer getStatus04() {
        return status04;
    }

    public void setStatus04(Integer status04) {
        this.status04 = status04;
    }

    public RoomDto(Integer roomID, String roomName, String roomDescription, String roomAddress, String roomeType, Integer status01, Integer status02, Integer status03, Integer status04) {
        RoomID = roomID;
        RoomName = roomName;
        RoomDescription = roomDescription;
        RoomAddress = roomAddress;
        RoomeType = roomeType;
        this.status01 = status01;
        this.status02 = status02;
        this.status03 = status03;
        this.status04 = status04;
    }

    @Override
    public String toString() {
        return "RoomDto{" + "RoomID=" + RoomID + ", RoomName='" + RoomName + '\'' + ", RoomDescription='" + RoomDescription + '\'' + ", RoomAddress='" + RoomAddress + '\'' + ", RoomeType='" + RoomeType + '\'' + ", status01=" + status01 + ", status02=" + status02 + ", status03=" + status03 + ", status04=" + status04 + '}';
    }
}