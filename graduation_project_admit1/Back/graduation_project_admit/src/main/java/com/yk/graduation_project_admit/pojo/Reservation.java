package com.yk.graduation_project_admit.pojo;

import com.yk.graduation_project_admit.pojo.Room;

import com.yk.graduation_project_admit.pojo.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 预约实体类
 * 对应数据库中的t_reservation表
 * 记录预约的详细信息
 */
@Entity
@Table(name = "t_reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "openid", nullable = false)
    private String openid;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    private String roomName;
    private String roomAddress;
    private String roomType;

    @Column(nullable = false)
    private LocalDate reservationDate;

    private String timeSlot; // 1-早上8点 2-早上10点 3-下午1点 4-下午3点

    private String usePurpose;

    @Column(columnDefinition = "TINYINT DEFAULT 0")
    private Integer status = 0; // 0-待审核 1-已通过 2-已拒绝 3-已取消

    private String remark;
    private String auditRemark;

    @Column(updatable = false)
    private LocalDateTime createTime;

    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
    }
}