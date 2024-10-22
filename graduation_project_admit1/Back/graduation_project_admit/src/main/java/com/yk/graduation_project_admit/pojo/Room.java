package com.yk.graduation_project_admit.pojo;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
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