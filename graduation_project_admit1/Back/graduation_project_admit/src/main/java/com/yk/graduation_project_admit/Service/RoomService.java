package com.yk.graduation_project_admit.Service;

import com.yk.graduation_project_admit.pojo.Reservation;
import com.yk.graduation_project_admit.pojo.Room;
import com.yk.graduation_project_admit.pojo.dto.RoomDto;
import com.yk.graduation_project_admit.pojo.vo.RoomAvailabilityVO;
import com.yk.graduation_project_admit.repository.ReservationRepository;
import com.yk.graduation_project_admit.repository.RoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public List<RoomAvailabilityVO> getRoomAvailability(LocalDate date) {
        List<Room> rooms = roomRepository.findAllByStatusTrue();
        List<Reservation> reservations = reservationRepository.findByReservationDate(date);

        return rooms.stream().map(room -> {
            RoomAvailabilityVO vo = RoomAvailabilityVO.fromRoom(room);

            // 设置每个时间段的可用状态
            Map<String, Boolean> timeSlots = new HashMap<>();
            timeSlots.put("08:00-10:00", isTimeSlotAvailable(room, date, "08:00-10:00", reservations));
            timeSlots.put("10:00-12:00", isTimeSlotAvailable(room, date, "10:00-12:00", reservations));
            timeSlots.put("14:00-16:00", isTimeSlotAvailable(room, date, "14:00-16:00", reservations));
            timeSlots.put("16:00-18:00", isTimeSlotAvailable(room, date, "16:00-18:00", reservations));

            vo.setTimeSlots(timeSlots);
            return vo;
        }).collect(Collectors.toList());
    }

    private boolean isTimeSlotAvailable(Room room, LocalDate date,
                                        String timeSlot, List<Reservation> reservations) {
        return reservations.stream()
                .noneMatch(r -> r.getRoom().getId().equals(room.getId())
                        && r.getTimeSlot().equals(timeSlot)
                        && r.getStatus() != 3); // 3表示已取消
    }

    public Room addRoom(RoomDto roomDto) {
        Room room = new Room();
        BeanUtils.copyProperties(roomDto, room);
        return roomRepository.save(room);
    }

    public String deleteRoom(Long id) {
        try {
            roomRepository.deleteById(Math.toIntExact(id));
            return "删除成功";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Room add(RoomDto roomDto) {
        return null;
    }

    public Iterable<Room> getRoom() {
        return null;
    }
}