package com.yk.graduation_project_admit.Service;

import com.yk.graduation_project_admit.pojo.Room;
import com.yk.graduation_project_admit.pojo.dto.RoomDto;
import com.yk.graduation_project_admit.repository.RoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    public Iterable<Room> getRoom() {
        return roomRepository.findAll();
    }

    public Room add(RoomDto roomDto) {
        if (roomDto != null) {
            Room roompojo = new Room();
            BeanUtils.copyProperties(roomDto, roompojo);
            roompojo.setStatus01(0);
            roompojo.setStatus02(0);
            roompojo.setStatus03(0);
            roompojo.setStatus04(0);
            try {
                validateRoom(roompojo);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
            System.out.println("roomDto" + roomDto);
            return roomRepository.save(roompojo);
        } else {
            return null;
        }

    }

    public String deleteRoom(Integer roomId) {
        try {
            roomRepository.deleteById(roomId);
            return "删除成功";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    private void validateRoom(Room room) throws Exception {
        if (room.getRoomID() == null || room.getRoomID() <= 0) {
            throw new Exception("RoomID 有误");
        }
        if (room.getRoomName() == null || room.getRoomName().trim().isEmpty()) {
            throw new Exception("RoomName 不能为空");
        }
        if (room.getRoomDescription() != null && room.getRoomDescription().length() > 500) {
            throw new Exception("RoomDescription格式有误");
        }
        if (room.getRoomAddress() == null || room.getRoomAddress().trim().isEmpty()) {
            throw new Exception("RoomAddress 不能为空");
        }
    }
}