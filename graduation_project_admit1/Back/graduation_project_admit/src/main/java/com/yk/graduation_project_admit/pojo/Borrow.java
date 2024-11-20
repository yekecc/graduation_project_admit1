// package com.yk.graduation_project_admit.pojo;

// import jakarta.persistence.*;
// import lombok.*;

// import java.sql.Date;

// /**
// * 借用记录实体类
// * 对应数据库中的t_borrow表
// * 记录教室借用的历史信息
// */
// @Entity
// @NoArgsConstructor
// @AllArgsConstructor
// @Setter
// @Getter
// @Table(name = "t_borrow")
// @Data
// public class Borrow {
// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// @Column(name = "b_Id")
// private Long id;
// @Column(name = "b_openid")
// private String openid;
// @Column(name = "b_roomId")
// private Integer RoomID;
// @Column(name = "b_roomName")
// private String RoomName;
// @Column(name = "b_roomAddress")
// private String RoomAddress;
// @Column(name = "b_roomType")
// private String RoomType;
// @Column(name = "b_status01")
// private Integer status01;
// @Column(name = "b_status02")
// private Integer status02;
// @Column(name = "b_status03")
// private Integer status03;
// @Column(name = "b_status04")
// private Integer status04;
// @Column(name = "b_time")
// private Date bTime;
// @Column(name = "b_useTo")
// private String useTo;
// }
