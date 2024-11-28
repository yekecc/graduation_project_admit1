package com.yk.graduation_project_admit.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

/**
 * 借用数据传输对象
 * 用于前后端交互时传递借用信息
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BorrowDto {
    /**
     * 用户的微信openid
     */
    @Getter
    private String openid;

    /**
     * 借用教室ID
     */
    private Long roomID;
    /**
     *
     */
    private String roomName;
    /**
     *
     */
    private String roomAddress;
    /**
     *
     */
    private String roomType;
    /**
     * 借用时间
     */
    private LocalDate reservationDate;

    /**
     * 使用目的
     */
    private String useTo;

    /**
     * 早上8点是否可借
     */
    private Integer status01;

    /**
     * 早上10点是否可借
     */
    private Integer status02;

    /**
     * 下午1点是否可借
     */
    private Integer status03;

    /**
     * 下午3点是否可借
     */
    private Integer status04;


}
