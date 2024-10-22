package com.yk.graduation_project_admit.pojo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "t_borrow")
@Data
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_Id")
    private Long id;
    @Column(name = "b_openid")
    private String openid;
    @Column(name = "b_roomId")
    private Integer RoomID;
    @Column(name = "b_roomName")
    private String RoomName;
    @Column(name = "b_roomDescription")
    private String RoomDescription;
    @Column(name = "b_roomAddress")
    private String RoomAddress;
    @Column(name = "b_roomType")
    private String RoomType;
    @Column(name = "b_status01")
    private Integer status01;
    @Column(name = "b_status02")
    private Integer status02;
    @Column(name = "b_status03")
    private Integer status03;
    @Column(name = "b_status04")
    private Integer status04;

    public Borrow(Long id, String openid, Integer roomID, String roomName, String roomDescription, String roomAddress, String roomType, Integer status01, Integer status02, Integer status03, Integer status04) {
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

    public Borrow() {

    }
}
