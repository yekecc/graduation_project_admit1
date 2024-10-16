package com.yk.graduation_project_admit.controller;

import com.yk.graduation_project_admit.pojo.ResponseMessage;
import com.yk.graduation_project_admit.pojo.Room;
import com.yk.graduation_project_admit.pojo.dto.RoomDto;
import com.yk.graduation_project_admit.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;


    @GetMapping     //http://dahle.natapp1.cc/room   get
    public ResponseMessage getRoom() {
        Iterable<Room> data = roomService.getRoom();

        return ResponseMessage.success(data);
    }

    /**
     * @param roomDto {
     *                "roomName": string,
     *                "roomDescription": string,
     *                "roomAddress":string,
     *                "roomType":string
     *                }
     * @return
     */
    @PostMapping //http://dahle.natapp1.cc/room   post
    public ResponseMessage add(@RequestBody RoomDto roomDto) {
        Room data = roomService.add(roomDto);
        return ResponseMessage.success(data);
    }

    /**
     * @param roomId
     * @return
     */
    @DeleteMapping //http://dahle.natapp1.cc/room   delete
    public ResponseMessage deleteRoom(Integer roomId) {
        roomService.deleteRoom(roomId);
        return ResponseMessage.success();
    }
}