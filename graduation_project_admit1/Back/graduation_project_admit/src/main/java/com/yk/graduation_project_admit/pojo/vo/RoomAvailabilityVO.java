package com.yk.graduation_project_admit.pojo.vo;

import com.yk.graduation_project_admit.pojo.Room;
import lombok.*;
import org.springframework.beans.BeanUtils;
import java.util.HashMap;
import java.util.Map;

/**
 * 教室可用性视图对象
 * 用于展示教室的可用性信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomAvailabilityVO {
    private Long id;
    private String roomName;
    private String roomAddress;
    private String roomType;
    private Integer capacity;
    private String equipment;
    private Map<String, Boolean> timeSlots;
    
    /**
     * 从Room对象转换为RoomAvailabilityVO
     * @param room Room对象
     * @return RoomAvailabilityVO对象
     */
    public static RoomAvailabilityVO fromRoom(Room room) {
        RoomAvailabilityVO vo = new RoomAvailabilityVO();
        BeanUtils.copyProperties(room, vo);
        vo.setTimeSlots(new HashMap<>());
        return vo;
    }
} 