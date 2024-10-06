package com.yk.graduation_project_admit.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer RoomID;
    @Column(name = "room_name")
    private String RoomName;
    @Column(name = "room_description")
    private String RoomDescription;
    @Column(name = "room_address")
    private String RoomAddress;
    @Column(name = "room_status", columnDefinition = "INTEGER DEFAULT 0 ")
    private Integer status;

    public Room(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Room() {
    }

    public Room(Integer roomID, String roomName, String roomDescription, String roomAddress) {
        RoomID = roomID;
        RoomName = roomName;
        RoomDescription = roomDescription;
        RoomAddress = roomAddress;
    }

    @Override
    public String toString() {
        return "Room{" +
                "RoomID=" + RoomID +
                ", RoomName='" + RoomName + '\'' +
                ", RoomDescription='" + RoomDescription + '\'' +
                ", RoomAddress='" + RoomAddress + '\'' +
                ", status=" + status +
                '}';
    }
}