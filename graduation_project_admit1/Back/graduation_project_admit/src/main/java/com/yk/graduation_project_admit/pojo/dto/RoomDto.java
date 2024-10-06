package com.yk.graduation_project_admit.pojo.dto;

import jakarta.persistence.Column;

public class RoomDto {
    private Integer RoomID;
    private String RoomName;
    private String RoomDescription;
    private String RoomAddress;
    private Integer RoomStatus;

    public Integer getRoomStatus() {
        return RoomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        RoomStatus = roomStatus;
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

    public RoomDto(Integer roomID, String roomName, String roomDescription, String roomAddress, Integer roomStatus) {
        RoomID = roomID;
        RoomName = roomName;
        RoomDescription = roomDescription;
        RoomAddress = roomAddress;
        RoomStatus = roomStatus;
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

    @Override
    public String toString() {
        return "RoomDto{" +
                "RoomID=" + RoomID +
                ", RoomName='" + RoomName + '\'' +
                ", RoomDescription='" + RoomDescription + '\'' +
                ", RoomAddress='" + RoomAddress + '\'' +
                ", RoomStatus=" + RoomStatus +
                '}';
    }
}