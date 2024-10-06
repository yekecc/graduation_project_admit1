package com.yk.graduation_project_admit.controller;

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
    public Iterable<Room> getRoom() {
        return roomSerivice.getRoom();
    }

    /**
     * @param roomDto
     * {
     *     "roomName": "音乐室01",
     *     "roomDescription": "里面有钢琴*2，小提琴*50",
     *     "roomAddress": "科技楼605"
     * }
     * @return
     */
    @PostMapping
    public Room add(@RequestBody RoomDto roomDto) {
        return roomSerivice.add(roomDto);
    }
    

}