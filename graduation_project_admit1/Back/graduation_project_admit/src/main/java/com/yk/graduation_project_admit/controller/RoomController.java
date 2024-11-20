package com.yk.graduation_project_admit.controller;

import com.yk.graduation_project_admit.Service.RoomService;
import com.yk.graduation_project_admit.pojo.ResponseMessage;
import com.yk.graduation_project_admit.pojo.Room;
import com.yk.graduation_project_admit.pojo.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 教室管理控制器
 * 处理教室的CRUD操作
 */
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    /**
     * 获取所有教室信息
     *
     * @return 教室列表
     */
    @GetMapping
    public ResponseMessage getRoom() {
        Iterable<Room> data = roomService.getRoom();
        return ResponseMessage.success(data);
    }

    /**
     * 添加新教室
     *
     * @param roomDto 教室信息DTO
     * @return 创建的教室信息
     */
    @PostMapping
    public ResponseMessage add(@RequestBody RoomDto roomDto) {
        Room data = roomService.add(roomDto);
        if (roomDto == null) {
            return ResponseMessage.fail("数据不能为空");
        } else {
            return ResponseMessage.success(data);
        }
    }

    /**
     *
     * 删除教室
     *
     * @param roomId 教室ID
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseMessage deleteRoom(Integer roomId) {
        String result = roomService.deleteRoom(Long.valueOf(roomId));
        return ResponseMessage.success(result);
    }
}