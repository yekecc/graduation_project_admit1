package com.yk.graduation_project_admit.pojo.dto;

import lombok.*;

/**
 * 教室数据传输对象
 * 用于前后端交互时传递教室信息
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDto {
    /** 教室ID */
    private Integer RoomID; // 房间id

    /** 教室名称 */
    private String RoomName; // 房间名

    /** 教室描述 */
    private String RoomDescription; // 房间描述

    /** 教室地址 */
    private String RoomAddress; // 房间地址

    /** 教室类型 */
    private String RoomType; // 房间类型：音乐室、美术室等

    /** 早上8点是否可借 */
    private Integer status01;

    /** 早上10点是否可借 */
    private Integer status02;

    /** 下午1点是否可借 */
    private Integer status03;

    /** 下午3点是否可借 */
    private Integer status04;
}