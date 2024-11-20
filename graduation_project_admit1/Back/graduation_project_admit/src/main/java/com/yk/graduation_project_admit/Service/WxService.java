package com.yk.graduation_project_admit.Service;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yk.graduation_project_admit.pojo.Reservation;
import com.yk.graduation_project_admit.pojo.Room;
import com.yk.graduation_project_admit.pojo.User;
import com.yk.graduation_project_admit.pojo.dto.BorrowDto;
import com.yk.graduation_project_admit.pojo.dto.ReservationDto;
import com.yk.graduation_project_admit.pojo.dto.UserDto;
import com.yk.graduation_project_admit.repository.ReservationRepository;
import com.yk.graduation_project_admit.repository.RoomRepository;
import com.yk.graduation_project_admit.repository.TimeSlotRepository;
import com.yk.graduation_project_admit.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
public class WxService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.secret}")
    private String secret;

    public String getUserpro(String code) {
        String url = String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
                appid, secret, code);
        return HttpUtil.get(url);
    }

    public User getUser(String openid) {
        return userRepository.findByOpenid(openid).orElse(null);
    }

    public User addUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return userRepository.save(user);
    }

    public Reservation createReservation(ReservationDto dto) {
        // 检查时间段是否已被预约
        if (reservationRepository.existsByRoomIdAndReservationDateAndTimeSlotAndStatusNot(
                dto.getRoomId(), dto.getReservationDate(), dto.getTimeSlot(), 3)) {
            throw new RuntimeException("该时间段已被预约");
        }

        Room room = roomRepository.findById(Math.toIntExact(dto.getRoomId()))
                .orElseThrow(() -> new RuntimeException("房间不存在"));

        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(dto, reservation);
        reservation.setRoom(room);
        reservation.setStatus(0);

        return reservationRepository.save(reservation);
    }

    public List<Reservation> getUserReservations(String openid) {
        return reservationRepository.findByOpenidOrderByCreateTimeDesc(openid);
    }

    public void cancelReservation(Long id, String openid) {
        Reservation reservation = reservationRepository.findByIdAndOpenid(id, openid)
                .orElseThrow(() -> new RuntimeException("预约记录不存在"));

        if (reservation.getStatus() != 0) {
            throw new RuntimeException("该预约已不能取消");
        }

        reservation.setStatus(3);
        reservationRepository.save(reservation);
    }
}
