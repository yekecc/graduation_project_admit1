package com.yk.graduation_project_admit.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_room")
@Data
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
    @Column(name = "room_type")
    private String RoomType;
    @Column(name = "room_status01", columnDefinition = "INTEGER DEFAULT 0 ")
    private Integer status01;
    @Column(name = "room_status02", columnDefinition = "INTEGER DEFAULT 0 ")
    private Integer status02;
    @Column(name = "room_status03", columnDefinition = "INTEGER DEFAULT 0 ")
    private Integer status03;
    @Column(name = "room_status04", columnDefinition = "INTEGER DEFAULT 0 ")
    private Integer status04;
}