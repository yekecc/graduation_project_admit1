package com.yk.graduation_project_admit.Service;

import com.yk.graduation_project_admit.pojo.Reservation;
import com.yk.graduation_project_admit.pojo.Room;
import com.yk.graduation_project_admit.pojo.User;
import com.yk.graduation_project_admit.repository.ReservationRepository;
import com.yk.graduation_project_admit.repository.RoomRepository;
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
    private RoomRepository roomRepository;

    /**
     * @param userNumber
     * @return
     */
    public Optional<User> getAdmit(String userNumber) {
        return UserRepository.findByUsername(userNumber);
    }

    public List<User> getAllUser() {
        return UserRepository.findAll();
    }

    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }

    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    public Page<Reservation> getPendingReservations(int page, int size) {
        return reservationRepository.findByStatusOrderByIdDesc(0, PageRequest.of(page, size));
    }

    public Map<String, Object> getReservationStatistics(LocalDate startDate, LocalDate endDate) {
        Map<String, Object> statistics = new HashMap<>();

        List<Reservation> approvedReservations = reservationRepository
                .findByReservationDateBetweenAndStatus(startDate, endDate, 1);
        List<Reservation> rejectedReservations = reservationRepository
                .findByReservationDateBetweenAndStatus(startDate, endDate, 2);

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