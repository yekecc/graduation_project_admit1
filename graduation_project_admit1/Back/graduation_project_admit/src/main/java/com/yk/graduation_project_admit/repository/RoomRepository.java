package com.yk.graduation_project_admit.repository;

import com.yk.graduation_project_admit.pojo.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Integer> {
    List<Room> findAllByStatusTrue();
}