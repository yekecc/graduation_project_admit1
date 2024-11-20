package com.yk.graduation_project_admit.repository;

import com.yk.graduation_project_admit.pojo.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room,Integer> {
    List<Room> findAllByStatusTrue();
}