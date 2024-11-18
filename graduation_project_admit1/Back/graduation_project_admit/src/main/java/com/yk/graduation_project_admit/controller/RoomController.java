package com.yk.graduation_project_admit.controller;

import com.yk.graduation_project_admit.Service.RoomService;
import com.yk.graduation_project_admit.pojo.ResponseMessage;
import com.yk.graduation_project_admit.pojo.Room;
import com.yk.graduation_project_admit.pojo.dto.RoomDto;
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
        if (roomDto == null) {
            return ResponseMessage.fail("数据不能为空");
        } else {
            return ResponseMessage.success(data);
        }

    }

    /**
     * @param roomId
     * @return
     */
    @DeleteMapping //http://dahle.natapp1.cc/room   delete
    public ResponseMessage deleteRoom(Integer roomId) {
        String result = roomService.deleteRoom(roomId);
        if (roomId == null) {
            return ResponseMessage.fail("ID不能为空");
        } else {
            return ResponseMessage.success(result);
        }

    }
}