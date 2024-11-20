package com.yk.graduation_project_admit.Service;

import cn.hutool.json.JSONUtil;
import com.yk.graduation_project_admit.pojo.Reservation;
import com.yk.graduation_project_admit.pojo.Room;
import com.yk.graduation_project_admit.pojo.User;
import com.yk.graduation_project_admit.pojo.dto.ReservationDto;
import com.yk.graduation_project_admit.pojo.dto.UserDto;
import com.yk.graduation_project_admit.repository.ReservationRepository;
import com.yk.graduation_project_admit.repository.RoomRepository;
import com.yk.graduation_project_admit.repository.TimeSlotRepository;
import com.yk.graduation_project_admit.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final String appid = "wx3b2dd66cfe6d8d9b";
    private final String appsecret = "8032ae51432227662a9bb6b8009d8363";
    
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private TimeSlotRepository timeSlotRepository;

    /**
     * @param code
     * @return openid session_key
     */
    public String getUserpro(String code) {
        if (code == null) {

            return "请求失败，code为空";
        } else {
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code ";
            url = url.replace("APPID", appid);
            url = url.replace("SECRET", appsecret);
            url = url.replace("JSCODE", code);

            String responseBody;

            try {
                ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
                responseBody = responseEntity.getBody();
                MediaType contentType = responseEntity.getHeaders().getContentType();
                JSONUtil.toJsonStr(responseBody);
                System.out.println("Response Body: " + responseBody);
                System.out.println("Content Type: " + contentType);
            } catch (RestClientException e) {
                throw new RuntimeException(e);
            }

            return responseBody;
        }
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
