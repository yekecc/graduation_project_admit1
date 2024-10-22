package com.yk.graduation_project_admit.controller;

import cn.hutool.json.JSONObject;
import com.yk.graduation_project_admit.Service.WxService;
import com.yk.graduation_project_admit.pojo.Borrow;
import com.yk.graduation_project_admit.pojo.ResponseMessage;
import com.yk.graduation_project_admit.pojo.User;
import com.yk.graduation_project_admit.pojo.dto.BorrowDto;
import com.yk.graduation_project_admit.pojo.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class WxController {
    @Autowired
    WxService wxService;

    /**
     * @param code
     * @return ResponseMessage
     */
    @PostMapping("/login")
    public ResponseMessage userLogin(String code) {
        String response = wxService.getUserpro(code);
//        System.out.println("code:" + code);
//        System.out.println(response);
        JSONObject jsonObject = new JSONObject(response);
        String openid = jsonObject.get("openid").toString();
        String sessionkey = jsonObject.get("session_key").toString();
        User userpro = wxService.getUser(openid);
        jsonObject.putOpt("userpro", userpro);
        return ResponseMessage.success(jsonObject);
    }

    /**
     * @param user
     * @return ResponseMessage
     */
    @PostMapping("/updateUser")
    public ResponseMessage userLogin(UserDto user) {
        return ResponseMessage.success(wxService.addUser(user));
    }

    /**
     * @param borrowDto
     * @return
     */
    @PostMapping("/getRoomLog")
    public ResponseMessage getRoomLog(BorrowDto borrowDto) {
        Borrow borrow = new Borrow();
        BeanUtils.copyProperties(borrowDto, borrow);
        return ResponseMessage.success(wxService.addlog(borrowDto));
    }

    /**
     * @param borrowDto
     * @return
     */
    @PostMapping("/verifyLog")
    public ResponseMessage verifyLog(BorrowDto borrowDto) {
        Borrow borrow = new Borrow();
        BeanUtils.copyProperties(borrowDto, borrow);
        return ResponseMessage.success(wxService.verifycode(borrow.getOpenid(),
                borrow.getRoomID(),
                borrow.getStatus01(),
                borrow.getStatus02(),
                borrow.getStatus03(),
                borrow.getStatus04()));

    }

}