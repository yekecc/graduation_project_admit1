package com.yk.graduation_project_admit.controller;

import com.yk.graduation_project_admit.Service.AdmitService;
import com.yk.graduation_project_admit.pojo.Admit;
import com.yk.graduation_project_admit.pojo.ResponseMessage;
import com.yk.graduation_project_admit.pojo.dto.admit_login_dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
@RequestMapping("/admit")
public class AdmitController {
    @Autowired
    AdmitService admitService;
    /**
     *
     * @param loginDto
     * @return
     */
    @PostMapping("/login")
    public ResponseMessage login(@RequestBody admit_login_dto loginDto) {
        if (loginDto == null) {
            return ResponseMessage.fail("账号密码为空，数据异常");
        } else {
            Admit admit = admitService.getAdmit(loginDto.getUserNumber());
            if (admit == null) {
                return ResponseMessage.fail("该用户不是管理员");
            } else {
                if (admit.getPassword().equals(loginDto.getPassword())) {
                    return ResponseMessage.success(admit);
                } else {
                    return ResponseMessage.fail("密码有误");
                }
            }
        }
    }

    /**
     * 获取所有用户信息
     *
     * @return 用户列表
     */
    @GetMapping("/getAllUser")
    public ResponseMessage getAllUser() {
        if (admitService.getAllUser() != null) {
            return ResponseMessage.success(admitService.getAllUser());
        } else {
            return ResponseMessage.fail("获取失败");
        }
    }

    @GetMapping("/getAllRoom")
    public ResponseMessage getAllRoom() {
        if (admitService.getAllRoom() != null) {
            return ResponseMessage.success(admitService.getAllRoom());
        } else {
            return ResponseMessage.fail("获取失败");
        }
    }

    @GetMapping("/getAllReservation")
    public ResponseMessage getAllReservation() {
        if (admitService.getAllReservation() != null) {
            return ResponseMessage.success(admitService.getAllReservation());
        } else {
            return ResponseMessage.fail("获取失败");
        }
    }

    @PostMapping("/verifycode")
    public ResponseMessage verifycode(@RequestBody String code) {
        if (code != null && !code.isEmpty()) {
            return ResponseMessage.success(admitService.verifycode(code));
        } else {
            return ResponseMessage.fail("數據有誤");
        }
    }
}

//    public ResponseMessage getPendingReservations(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        try {
//            Page<Reservation> reservations = admitService.getPendingReservations(page, size);
//            return ResponseMessage.success(reservations);
//        } catch (Exception e) {
//            return ResponseMessage.fail("获取待审核预约失败：" + e.getMessage());
//        }
//    }
//
//
//    /**
//     * @param startDate
//     * @param endDate
//     * @return
//     */
//    @GetMapping("/reservations/statistics")
//    public ResponseMessage getReservationStatistics(
//            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
//            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
//        try {
//            Map<String, Object> statistics = admitService.getReservationStatistics(startDate, endDate);
//            return ResponseMessage.success(statistics);
//        } catch (Exception e) {
//            return ResponseMessage.fail("获取统计信息失败：" + e.getMessage());
//        }
//    }
//}