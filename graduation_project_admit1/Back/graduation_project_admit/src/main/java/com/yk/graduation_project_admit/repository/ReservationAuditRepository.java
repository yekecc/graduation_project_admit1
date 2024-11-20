package com.yk.graduation_project_admit.repository;

import com.yk.graduation_project_admit.pojo.ReservationAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationAuditRepository extends JpaRepository<ReservationAudit, Long> {
    Optional<ReservationAudit> findByReservationId(Long reservationId);

    List<ReservationAudit> findByAuditorIdOrderByAuditTimeDesc(String auditorId);

    @Query("SELECT ra FROM ReservationAudit ra WHERE ra.auditTime >= :startTime " +
            "AND ra.auditTime <= :endTime")
    List<ReservationAudit> findByAuditTimeRange(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);
}