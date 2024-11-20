package com.yk.graduation_project_admit.pojo;

import com.yk.graduation_project_admit.pojo.Reservation;
import com.yk.graduation_project_admit.pojo.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/**
 * 预约审核实体类
 * 对应数据库中的t_reservation_audit表
 * 记录预约的审核信息
 */
@Entity
@Table(name = "t_reservation_audit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @Column(nullable = false)
    private String auditorId;

    private String auditorName;

    @Column(nullable = false)
    private Integer auditResult; // 1-通过 2-拒绝

    private String auditRemark;

    @Column(updatable = false)
    private LocalDateTime auditTime;

    @PrePersist
    protected void onCreate() {
        auditTime = LocalDateTime.now();
    }
}