package com.yk.graduation_project_admit.repository;

import com.yk.graduation_project_admit.pojo.Reservation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByIdAndOpenid(Long id, String openid);

    boolean existsByRoomIdAndReservationDateAndTimeSlotAndStatusNot(Long roomId, LocalDate date, String timeSlot,
            Integer status);

    Page<Reservation> findByStatusOrderByIdDesc(int status, PageRequest pageRequest);

    List<Reservation> findByReservationDateBetweenAndStatus(LocalDate startDate, LocalDate endDate, int status);

    List<Reservation> findByReservationDate(LocalDate date);

    List<Reservation> findByOpenidOrderByIdDesc(String openid);
}