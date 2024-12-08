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
            Map<String, Boolean> timeSlots = new HashMap<>();
            timeSlots.put("08:00-10:00", isTimeSlotAvailable(room, date, "08:00-10:00", reservations));
            timeSlots.put("10:00-12:00", isTimeSlotAvailable(room, date, "10:00-12:00", reservations));
            timeSlots.put("14:00-16:00", isTimeSlotAvailable(room, date, "14:00-16:00", reservations));
            timeSlots.put("16:00-18:00", isTimeSlotAvailable(room, date, "16:00-18:00", reservations));
            vo.setTimeSlots(timeSlots);
            return vo;
        }).collect(Collectors.toList());
    }

    private boolean isTimeSlotAvailable(Room room, LocalDate date, String timeSlot, List<Reservation> reservations) {
        return reservations.stream()
                .noneMatch(r -> r.getRoom().getId().equals(room.getId())
                        && r.getTimeSlot().equals(timeSlot)
                        && r.getStatus() != 2); // 2表示已取消
    }

    public Room addRoom(RoomDto roomDto) {
        Room room = new Room();
        BeanUtils.copyProperties(roomDto, room);
        return roomRepository.save(room);
    }

    // public Iterable<Room> getRoom() {
    // return roomRepository.findAll();
    // }

    // public Room add(RoomDto roomDto) {
    // if (roomDto != null) {
    // Room roompojo = new Room();
    // BeanUtils.copyProperties(roomDto, roompojo);
    // roompojo.setStatus01(0);
    // roompojo.setStatus02(0);
    // roompojo.setStatus03(0);
    // roompojo.setStatus04(0);
    // try {
    // validateRoom(roompojo);
    // } catch (Exception e) {
    // System.out.println(e.getMessage());
    // throw new RuntimeException(e);
    // }
    // System.out.println("roomDto" + roomDto);
    // return roomRepository.save(roompojo);
    // } else {
    // return null;
    // }
    //
    // }

    // public String deleteRoom(Integer roomId) {
    // try {
    // roomRepository.deleteById(roomId);
    // return "删除成功";
    // } catch (Exception e) {
    // System.out.println(e.getMessage());
    // return e.getMessage();
    // }
    // }

}