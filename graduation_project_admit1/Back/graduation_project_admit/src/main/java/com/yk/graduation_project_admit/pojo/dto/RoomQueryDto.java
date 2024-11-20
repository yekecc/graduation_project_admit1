package com.yk.graduation_project_admit.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomQueryDto {
    private LocalDate date;
    private String roomType;
    private Integer capacity;
    private String timeSlot;
    private Boolean available;
}