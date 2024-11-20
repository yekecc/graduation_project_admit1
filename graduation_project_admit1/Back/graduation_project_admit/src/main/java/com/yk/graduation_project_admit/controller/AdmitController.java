package com.yk.graduation_project_admit.controller;

import com.yk.graduation_project_admit.Service.AdmitService;
import com.yk.graduation_project_admit.pojo.Reservation;
import com.yk.graduation_project_admit.pojo.ReservationAudit;
import com.yk.graduation_project_admit.pojo.ResponseMessage;
import com.yk.graduation_project_admit.pojo.User;
import com.yk.graduation_project_admit.pojo.dto.admit_login_dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

/**
 * 管理员控制器
 * 处理管理员相关的请求,包括登录、用户管理、预约审核等功能
 */
@RestController
@RequestMapping("/admit")
public class AdmitController {
    @Autowired
    AdmitService admitService;

    /**
     * 管理员登录
     * 
     * @param loginDto 登录信息DTO
     * @return 登录结果
     */
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody admit_login_dto loginDto) {
        Optional<User> UserOptional = admitService.getAdmit(loginDto.getUserNumber());
        if (UserOptional.isPresent()) {
            User user = UserOptional.get();
            // 验证密码
            if (user.getPassword().equals(loginDto.getPassword())) {
                // 登录成功，返回用户对象
                return ResponseEntity.ok(user);
            } else {
                // 密码错误
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } else {
            // 用户不存在
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
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

    @GetMapping("/reservations/pending")
    public ResponseMessage getPendingReservations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<Reservation> reservations = admitService.getPendingReservations(page, size);
            return ResponseMessage.success(reservations);
        } catch (Exception e) {
            return ResponseMessage.fail("获取待审核预约失败：" + e.getMessage());
        }
    }

    @PostMapping("/reservations/{id}/audit")
    public ResponseMessage auditReservation(
            @PathVariable Long id,
            @RequestParam String auditorId,
            @RequestParam Integer auditResult,
            @RequestParam(required = false) String auditRemark) {
        try {
            ReservationAudit audit = admitService.auditReservation(id, auditorId, auditResult, auditRemark);
            return ResponseMessage.success(audit);
        } catch (Exception e) {
            return ResponseMessage.fail("审核失败：" + e.getMessage());
        }
    }

    /**
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/reservations/statistics")
    public ResponseMessage getReservationStatistics(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        try {
            Map<String, Object> statistics = admitService.getReservationStatistics(startDate, endDate);
            return ResponseMessage.success(statistics);
        } catch (Exception e) {
            return ResponseMessage.fail("获取统计信息失败：" + e.getMessage());
        }
    }
}