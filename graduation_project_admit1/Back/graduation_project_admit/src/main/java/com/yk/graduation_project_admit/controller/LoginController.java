package com.yk.graduation_project_admit.controller;

import com.yk.graduation_project_admit.pojo.User;
import com.yk.graduation_project_admit.pojo.dto.admit_login_dto;
import com.yk.graduation_project_admit.serivice.AdmitSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    AdmitSerivice admitSerivice = new AdmitSerivice();

    @PostMapping     //url:localhost:8080/login
    public ResponseEntity<User> login(@RequestBody admit_login_dto loginDto) {

        Optional<User> UserOptional = admitSerivice.getAdmit(loginDto.getUserNumber());
        if (UserOptional.isPresent()) {
            User user = UserOptional.get();
            // 验证密码
            if (user.getPassword().equals(loginDto.getPassword())) {
                // 登录成功，返回用户对象
                return ResponseEntity.ok(user);
            } else {
                // 密码错误
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // 明确返回 null 用户对象
            }
        } else {
            // 用户不存在
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 明确返回 null 用户对象
        }
    }
}