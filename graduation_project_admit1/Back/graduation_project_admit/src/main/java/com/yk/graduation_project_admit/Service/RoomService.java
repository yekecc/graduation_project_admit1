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
        Room roompojo = new Room();
        BeanUtils.copyProperties(roomDto, roompojo);
        roompojo.setStatus01(0);
        roompojo.setStatus02(0);
        roompojo.setStatus03(0);
        roompojo.setStatus04(0);
        System.out.println("roomDto" + roomDto);
        return roomRepository.save(roompojo);
    }

    public void deleteRoom(Integer roomId) {
        roomRepository.deleteById(roomId);
    }
}