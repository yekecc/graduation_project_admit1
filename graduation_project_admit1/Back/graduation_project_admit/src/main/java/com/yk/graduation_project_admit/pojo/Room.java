package com.yk.graduation_project_admit.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer RoomID;
    @Column(name = "room_name", nullable = false)
    private String RoomName;
    @Column(name = "room_description")
    private String RoomDescription;
    @Column(name = "room_address", nullable = false)
    private String RoomAddress;
    @Column(name = "room_type", nullable = false)
    private String RoomType;
    @Column(name = "room_status01", columnDefinition = "INTEGER DEFAULT 0 ")
    private Integer status01;
    @Column(name = "room_status02", columnDefinition = "INTEGER DEFAULT 0 ")
    private Integer status02;
    @Column(name = "room_status03", columnDefinition = "INTEGER DEFAULT 0 ")
    private Integer status03;
    @Column(name = "room_status04", columnDefinition = "INTEGER DEFAULT 0 ")
    private Integer status04;

    public Room() {
    }

    public Room(Integer roomID, String roomName, String roomDescription, String roomAddress, String roomType, Integer status01, Integer status02, Integer status03, Integer status04) {
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

    public String getRoomType() {
        return RoomType;
    }

    public void setRoomType(String roomType) {
        RoomType = roomType;
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

    @Override
    public String toString() {
        return "Room{" +
                "RoomID=" + RoomID +
                ", RoomName='" + RoomName + '\'' +
                ", RoomDescription='" + RoomDescription + '\'' +
                ", RoomAddress='" + RoomAddress + '\'' +
                ", RoomType='" + RoomType + '\'' +
                ", status01=" + status01 +
                ", status02=" + status02 +
                ", status03=" + status03 +
                ", status04=" + status04 +
                '}';
    }
}