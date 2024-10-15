package com.yk.graduation_project_admit.controller;

import com.yk.graduation_project_admit.Service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class WxController {
    @Autowired
    WxService wxService;

    @PostMapping("/login")
    public String getUser(String code) {
        System.out.println("code:" + code);
        String response = wxService.login(code);
        System.out.println(response);
        return response;
    }
}