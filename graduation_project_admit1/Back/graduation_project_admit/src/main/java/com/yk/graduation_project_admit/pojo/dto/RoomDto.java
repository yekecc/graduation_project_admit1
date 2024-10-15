package com.yk.graduation_project_admit.pojo.dto;

import lombok.Data;

@Data
public class RoomDto {
    private Integer RoomID;//房间id
    private String RoomName;//房间名
    private String RoomDescription;//房间描述
    private String RoomAddress;//房间地址
    private String RoomType; //房间类型：音乐室、美术室…………
    private Integer status01;//早上8.是否可借
    private Integer status02;//早上10.是否可借
    private Integer status03;//下午1.是否可借
    private Integer status04;//下午3.是否可借


}