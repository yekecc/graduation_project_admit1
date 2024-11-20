package com.yk.graduation_project_admit.Service;

import com.yk.graduation_project_admit.pojo.Reservation;
import com.yk.graduation_project_admit.pojo.ReservationAudit;
import com.yk.graduation_project_admit.pojo.User;
import com.yk.graduation_project_admit.repository.ReservationAuditRepository;
import com.yk.graduation_project_admit.repository.ReservationRepository;
import com.yk.graduation_project_admit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdmitService {

    @Autowired
    UserRepository UserRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationAuditRepository auditRepository;

    /**
     * @param userNumber
     * @return
     */
    public Optional<User> getAdmit(String userNumber) {
        return UserRepository.findByUsername(userNumber);
    }

    public List<User> getAllUser() {
        return (List<User>) UserRepository.findAll();
    }

    public Page<Reservation> getPendingReservations(int page, int size) {
        return reservationRepository.findByStatusOrderByCreateTimeDesc(
                0, PageRequest.of(page, size));
    }

    public ReservationAudit auditReservation(
            Long reservationId, String auditorId, Integer auditResult, String auditRemark) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("预约记录不存在"));

        if (reservation.getStatus() != 0) {
            throw new RuntimeException("该预约已被审核");
        }

        reservation.setStatus(auditResult);
        reservation.setAuditRemark(auditRemark);
        reservationRepository.save(reservation);

        ReservationAudit audit = new ReservationAudit();
        audit.setReservation(reservation);
        audit.setAuditorId(auditorId);
        audit.setAuditResult(auditResult);
        audit.setAuditRemark(auditRemark);

        return auditRepository.save(audit);
    }

    public Map<String, Object> getReservationStatistics(LocalDate startDate, LocalDate endDate) {
        Map<String, Object> statistics = new HashMap<>();

        List<Reservation> approvedReservations = reservationRepository
                .findByDateRangeAndStatus(startDate, endDate, 1);
        List<Reservation> rejectedReservations = reservationRepository
                .findByDateRangeAndStatus(startDate, endDate, 2);

        statistics.put("totalApproved", approvedReservations.size());
        statistics.put("totalRejected", rejectedReservations.size());

        Map<String, Long> roomTypeStats = approvedReservations.stream()
                .collect(Collectors.groupingBy(
                        r -> r.getRoom().getRoomType(),
                        Collectors.counting()));
        statistics.put("roomTypeDistribution", roomTypeStats);

        return statistics;
    }
}