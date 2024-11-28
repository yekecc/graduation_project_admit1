package com.yk.graduation_project_admit.controller;

import cn.hutool.json.JSONObject;
import com.yk.graduation_project_admit.Service.RoomService;
import com.yk.graduation_project_admit.Service.WxService;
import com.yk.graduation_project_admit.pojo.Reservation;
import com.yk.graduation_project_admit.pojo.ResponseMessage;
import com.yk.graduation_project_admit.pojo.User;
import com.yk.graduation_project_admit.pojo.dto.BorrowDto;
import com.yk.graduation_project_admit.pojo.dto.ReservationDto;
import com.yk.graduation_project_admit.pojo.dto.UserDto;
import com.yk.graduation_project_admit.pojo.vo.RoomAvailabilityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * 微信小程序控制器
 * 处理微信端的请求,包括用户登录、预约管理等功能
 */
@RestController
@RequestMapping("/user")
public class WxController {
    @Autowired
    private WxService wxService;

    @Autowired
    private RoomService roomService;

    /**
     * 微信用户登录
     *
     * @param code 微信登录code
     * @return 包含openid和用户信息的响应
     */
    @PostMapping("/login")
    public ResponseMessage userLogin(@RequestParam String code) {
        if (code == null) {
            return ResponseMessage.fail("code不能为空");
        } else {
            String response = wxService.getUserpro(code);
            // System.out.println("code:" + code);
            // System.out.println(response);
            JSONObject jsonObject = new JSONObject(response);
            String openid = jsonObject.get("openid").toString();
            String sessionkey = jsonObject.get("session_key").toString();
            User userPro = wxService.getUser(openid);

            jsonObject.putOpt("userPro", Objects.requireNonNullElseGet(userPro, () -> jsonObject.isNull("userPro")));

            return ResponseMessage.success(jsonObject);
        }
    }

    /**
     * 更新用户信息
     *
     * @param user 用户信息DTO
     * @return 更新后的用户信息
     */
    @PostMapping("/updateUser")
    public ResponseMessage userLogin(UserDto user) {
        try {
            return ResponseMessage.success(wxService.addUser(user));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取可用教室列表
     *
     * @param date 查询日期
     * @return 可用教室列表
     */
    @GetMapping("/rooms/available")
    public ResponseMessage getRoomAvailability(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        try {
            List<RoomAvailabilityVO> rooms = roomService.getRoomAvailability(date);
            return ResponseMessage.success(rooms);
        } catch (Exception e) {
            return ResponseMessage.fail("获取房间信息失败：" + e.getMessage());
        }
    }

    /**
     * 创建预约
     *
     * @param borrowDto 预约信息DTO
     * @return 创建的预约信息
     */
    @PostMapping("/reservation")
    public ResponseMessage createReservation(@RequestBody BorrowDto borrowDto) {
        try {
            ReservationDto reservationDto = ReservationDto.fromBorrowDto(borrowDto);
            Reservation reservation = wxService.createReservation(reservationDto);
            return ResponseMessage.success(reservation);
        } catch (Exception e) {
            return ResponseMessage.fail("预约失败：" + e.getMessage());
        }
    }

    /**
     * 获取用户的预约记录
     *
     * @param openid 用户openid
     * @return 预约记录列表
     */
    @GetMapping("/reservations")
    public ResponseMessage getMyReservations(@RequestParam String openid) {
        try {
            List<Reservation> reservations = wxService.getUserReservations(openid);
            return ResponseMessage.success(reservations);
        } catch (Exception e) {
            return ResponseMessage.fail("获取预约记录失败：" + e.getMessage());
        }
    }

    /**
     * 取消预约
     *
     * @param id     预约ID
     * @param openid 用户openid
     * @return 取消结果
     */
    @PostMapping("/reservation/{id}/cancel")
    public ResponseMessage cancelReservation(@PathVariable Long id, @RequestParam String openid) {
        try {
            wxService.cancelReservation(id, openid);
            return ResponseMessage.success("取消成功");
        } catch (Exception e) {
            return ResponseMessage.fail("取消失败：" + e.getMessage());
        }
    }
}