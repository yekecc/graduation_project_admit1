package com.yk.graduation_project_admit.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 预约数据传输对象
 * 用于前后端交互时传递预约信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    /**
     * 用户的微信openid
     */
    private String openid;

    /**
     * 预约教室ID
     */
    private Long roomId;

    /**
     * 预约教室名称
     */
    private String roomName;

    /**
     * 预约教室地址
     */
    private String roomAddress;

    /**
     * 预约教室类型
     */
    private String roomType;

    /**
     * 预约日期
     */
    private LocalDate reservationDate;

    /**
     * 预约时段 1-早上8点 2-早上10点 3-下午1点 4-下午3点
     */
    private String timeSlot;

    /**
     * 使用目的
     */
    private String usePurpose;

    // 从 BorrowDto 转换为 ReservationDto
    public static ReservationDto fromBorrowDto(BorrowDto borrowDto) {
        ReservationDto dto = new ReservationDto();
        dto.setOpenid(borrowDto.getOpenid());
        dto.setRoomId(borrowDto.getRoomID());
        dto.setRoomName(borrowDto.getRoomName());
        dto.setRoomAddress(borrowDto.getRoomAddress());
        dto.setRoomType(borrowDto.getRoomType());
        dto.setReservationDate(borrowDto.getReservationDate());
        dto.setUsePurpose(borrowDto.getRemark());

        if (borrowDto.getStatus01() == 1) dto.setTimeSlot("1");
        else if (borrowDto.getStatus02() == 1) dto.setTimeSlot("2");
        else if (borrowDto.getStatus03() == 1) dto.setTimeSlot("3");
        else if (borrowDto.getStatus04() == 1) dto.setTimeSlot("4");

        return dto;
    }
}
