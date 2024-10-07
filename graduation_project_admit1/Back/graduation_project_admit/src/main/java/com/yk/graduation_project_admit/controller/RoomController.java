package com.yk.graduation_project_admit.controller;

import com.yk.graduation_project_admit.pojo.ResponseMessage;
import com.yk.graduation_project_admit.pojo.Room;
import com.yk.graduation_project_admit.pojo.dto.RoomDto;
import com.yk.graduation_project_admit.serivice.RoomSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomSerivice roomSerivice;


    @GetMapping
    public ResponseMessage getRoom() {
        Iterable<Room> data = roomSerivice.getRoom();
        return ResponseMessage.success(data);
    }

    /**
     * @param roomDto {
     *                "roomName": string,
     *                "roomDescription": string,
     *                "roomAddress":string
     *                }
     * @return
     */
    @PostMapping
    public ResponseMessage add(@RequestBody RoomDto roomDto) {
        Room data = roomSerivice.add(roomDto);
        return ResponseMessage.success(data);
    }

    /**
     *
     * @param roomId
     * @return
     */
    @DeleteMapping
    public ResponseMessage deleteRoom(Integer roomId) {
        roomSerivice.deleteRoom(roomId);
        return ResponseMessage.success();
    }
}