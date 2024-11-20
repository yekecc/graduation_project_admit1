package com.yk.graduation_project_admit.repository;

import com.yk.graduation_project_admit.pojo.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findAllByOrderBySortOrderAsc();
}
