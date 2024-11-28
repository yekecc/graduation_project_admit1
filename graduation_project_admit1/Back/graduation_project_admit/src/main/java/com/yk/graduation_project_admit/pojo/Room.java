package com.yk.graduation_project_admit.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "room")

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    /**
     * 房间名
     */
    private String roomName;
    /**
     * 房间位置
     */
    private String roomAddress;
    /**
     * 房间类型
     */
    private String roomType;
    /**
     * 房间容量
     */
    private Integer capacity;
    /**
     * 房间设备情况
     */
    private String equipment;
    /**
     * 房间状态
     */
    @Column(columnDefinition = "TINYINT DEFAULT 1")
    private Boolean status = true;
    /**
     * 房间创建时间
     */
    @Column(updatable = false)
    private LocalDateTime createTime;
    /**
     * 房间更新时间
     */
    private LocalDateTime updateTime;

    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
        updateTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = LocalDateTime.now();
    }

}