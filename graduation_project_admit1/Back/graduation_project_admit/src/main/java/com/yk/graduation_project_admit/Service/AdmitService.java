package com.yk.graduation_project_admit.Service;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yk.graduation_project_admit.pojo.Admit;
import com.yk.graduation_project_admit.pojo.Reservation;
import com.yk.graduation_project_admit.pojo.Room;
import com.yk.graduation_project_admit.pojo.User;
import com.yk.graduation_project_admit.repository.AdmitRepository;
import com.yk.graduation_project_admit.repository.ReservationRepository;
import com.yk.graduation_project_admit.repository.RoomRepository;
import com.yk.graduation_project_admit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class AdmitService {

    @Autowired
    UserRepository UserRepository;

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AdmitRepository admitRepository;
    @Autowired
    private UserRepository userRepository;

    public void delReservation(long reservationId) {
        reservationRepository.deleteAllById(Collections.singleton(reservationId));
    }

    /**
     * @param userNumber
     * @return
     */
    public Admit getAdmit(String userNumber) {
        return admitRepository.findAdmitByUserNumber(userNumber);
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

    public int verifycode(String code) {
        System.out.println(code);
        // 解析外层 JSON 以获取 data 字段
        JSONObject outerJsonObject = JSONUtil.parseObj(code);
        String data = outerJsonObject.getStr("data");
        // 解析 data 字段中的 JSON 字符串
        JSONObject jsonObject = JSONUtil.parseObj(data);
        int roomId = jsonObject.getInt("roomID");
        String openid = jsonObject.getStr("openid");
        LocalDate reservationDate = LocalDate.parse(jsonObject.getStr("reservationDate"));
        String timeslot = "";
        if (jsonObject.getInt("status01") == 1) {
            timeslot = "1";
        } else if (jsonObject.getInt("status02") == 1) {
            timeslot = "2";
        } else if (jsonObject.getInt("status03") == 1) {
            timeslot = "3";
        } else if (jsonObject.getInt("status04") == 1) {
            timeslot = "4";
        }
        if (reservationRepository.existsByOpenidAndReservationDateAndTimeSlotAndRoomId(openid, reservationDate,
                timeslot, (long) roomId) != null) {
            return reservationRepository.updateStatusToConfirmed(openid, reservationDate, timeslot, (long) roomId);
        } else {
            return 0;
        }
    }

    public void delUser(int userID) {
        userRepository.deleteById(userID);
    }

    public void delRoom(int roomID) {
        roomRepository.deleteById(roomID);
    }
}