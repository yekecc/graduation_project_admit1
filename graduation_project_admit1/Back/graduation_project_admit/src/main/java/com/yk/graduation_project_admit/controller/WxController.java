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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
    public ResponseMessage userLogin(@RequestParam String code) {
        if (code == null) {
            return ResponseMessage.fail("code不能为空");
        } else {
            String response = wxService.getUserpro(code);
//        System.out.println("code:" + code);
//        System.out.println(response);
            JSONObject jsonObject = new JSONObject(response);
            String openid = jsonObject.get("openid").toString();
            String sessionkey = jsonObject.get("session_key").toString();
            User userPro = wxService.getUser(openid);

            if (userPro == null) {
                jsonObject.putOpt("userPro",jsonObject.isNull("userPro"));
            }else{
                jsonObject.putOpt("userPro", userPro);
            }


            return ResponseMessage.success(jsonObject);
        }
    }

    /**
     * @param user
     * @return ResponseMessage
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
     * @param borrowDto
     * @return
     */
    @PostMapping("/getRoomLog")
    public ResponseMessage getRoomLog(@RequestBody BorrowDto borrowDto) {
        Borrow borrow = new Borrow();
        if (borrowDto.getOpenid() == null) {
            return ResponseMessage.fail("请先登录");
        } else if (borrowDto.getRoomID() == null || borrowDto.getRoomAddress() == null   || borrowDto.getBTime() == null & borrowDto.getRoomName() == null || borrowDto.getRoomType() == null) {
            return ResponseMessage.fail("数据有误");
        } else {
            BeanUtils.copyProperties(borrowDto, borrow);
            return ResponseMessage.success(wxService.addlog(borrowDto));
        }
    }

    /**
     * @param borrowDto
     * @return ResponseMessage
     */
    @PostMapping("/verifyLog")
    public ResponseMessage verifyLog(BorrowDto borrowDto) {
        Borrow borrow = new Borrow();
        BeanUtils.copyProperties(borrowDto, borrow);
        LocalDate today = LocalDate.now();
        Borrow result = wxService.verifycode(borrow.getOpenid(), borrow.getRoomID(), borrow.getStatus01(), borrow.getStatus02(), borrow.getStatus03(), borrow.getStatus04());
        if (result == null) {
            return ResponseMessage.fail("借阅信息错误");
        } else if (borrowDto.getBTime().equals(today)) {
            return ResponseMessage.fail("请在合适日期扫码");
        } else {
            return ResponseMessage.success(result);
        }

    }
}