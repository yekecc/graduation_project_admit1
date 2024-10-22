package com.yk.graduation_project_admit.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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

}
