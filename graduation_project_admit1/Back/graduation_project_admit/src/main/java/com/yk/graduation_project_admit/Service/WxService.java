package com.yk.graduation_project_admit.Service;


import cn.hutool.json.JSONUtil;
import com.yk.graduation_project_admit.pojo.Borrow;
import com.yk.graduation_project_admit.pojo.User;
import com.yk.graduation_project_admit.pojo.dto.BorrowDto;
import com.yk.graduation_project_admit.pojo.dto.UserDto;
import com.yk.graduation_project_admit.repository.BorrowRepository;
import com.yk.graduation_project_admit.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class WxService {
    private final String appid = "wx3b2dd66cfe6d8d9b";
    private final String appsecret = "8032ae51432227662a9bb6b8009d8363";
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BorrowRepository borrowRepository;
    private String code;

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

    /**
     * @param openid
     * @return user
     */
    public User getUser(String openid) {
        User user = new User();
        if (openid == null) {
            return null;
        } else {
            user = userRepository.findByOpenid(openid);
            return user;
        }
    }

    /**
     * @param user
     * @return user
     */
    public User addUser(UserDto user) throws Exception {
        User user1 = new User();
        BeanUtils.copyProperties(user, user1);

        validata(user1);

        if (userRepository.findByOpenid(user1.getOpenid()) == null) {

            return userRepository.save(user1);
        } else {
            return updateUser(user);
        }
    }

    /**
     * @param userDto
     */
    public User updateUser(UserDto userDto) {

        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return userRepository.updateUser(user.getUsername(), user.getPassword(), user.getTelephone(), user.getUserClass(), user.getOffice(), user.getUserNumber(), user.getOpenid());

    }

    /**
     * @param borrowDto
     * @return Borrow
     */
    public Borrow addlog(BorrowDto borrowDto) {
        Borrow borrow = new Borrow();

        BeanUtils.copyProperties(borrowDto, borrow);
        try {
            validate(borrow);
            return borrowRepository.save(borrow);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Borrow verifycode(String openid, Integer roomID, Integer status01, Integer status02, Integer status03, Integer status04) {
        Borrow borrow = borrowRepository.findBorrowByOpenidAndRoomIDAndStatus01AndStatus02AndStatus03AndStatus04(openid, roomID, status01, status02, status03, status04);
        return borrow;
    }

    /**
     * @param user
     * @throws Exception
     */
    //校验用户表单
    public void validata(User user) throws Exception {
        // 校验用户名
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new Exception("用户名是必填项。");
        }
        if (user.getUsername().length() < 3 || user.getUsername().length() > 50) {
            throw new Exception("用户名必须在3到50个字符之间。");
        }

        // 校验密码
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new Exception("密码是必填项。");
        }
        if (user.getPassword().length() < 6 || user.getPassword().length() > 50) {
            throw new Exception("密码必须在6到50个字符之间。");
        }

        // 校验电话号码
        if (user.getTelephone() == null || user.getTelephone().trim().isEmpty()) {
            throw new Exception("电话号码是必填项。");
        }
        if (!user.getTelephone().matches("\\d{7,15}")) { // 简单的电话号码格式校验
            throw new Exception("电话号码必须是有效的数字。");
        }

        // 校验用户类别
        if (user.getUserClass() == null || user.getUserClass().trim().isEmpty()) {
            throw new Exception("用户类别是必填项。");
        }

        // 校验办公地点
        if (user.getOffice() == null || user.getOffice().trim().isEmpty()) {
            throw new Exception("办公地点是必填项。");
        }

        // 校验用户编号
        if (user.getUserNumber() == null || user.getUserNumber().trim().isEmpty()) {
            throw new Exception("用户编号是必填项。");
        }

        // 校验OpenID
        if (user.getOpenid() == null || user.getOpenid().trim().isEmpty()) {
            throw new Exception("OpenID是必填项。");
        }

    }

    /**
     * @param borrow
     * @throws Exception
     */
    //校验日志表单
    public void validate(Borrow borrow) throws Exception {
        // 校验openid
        if (borrow.getOpenid() == null || borrow.getOpenid().trim().isEmpty()) {
            throw new Exception("openid是必填项。");
        }
        if (borrow.getOpenid().length() > 50) { // 假设openid最大长度为50
            throw new Exception("openid长度不能超过50个字符。");
        }

        // 校验RoomID
        if (borrow.getRoomID() == null || borrow.getRoomID() <= 0) {
            throw new Exception("RoomID必须是正整数。");
        }

        // 校验RoomName
        if (borrow.getRoomName() == null || borrow.getRoomName().trim().isEmpty()) {
            throw new Exception("RoomName是必填项。");
        }
        if (borrow.getRoomName().length() > 100) { // 假设RoomName最大长度为100
            throw new Exception("RoomName长度不能超过100个字符。");
        }

        // 校验RoomAddress
        if (borrow.getRoomAddress() == null || borrow.getRoomAddress().trim().isEmpty()) {
            throw new Exception("RoomAddress是必填项。");
        }
        if (borrow.getRoomAddress().length() > 255) { // 假设RoomAddress最大长度为255
            throw new Exception("RoomAddress长度不能超过255个字符。");
        }

        // 校验RoomType
        if (borrow.getRoomType() == null || borrow.getRoomType().trim().isEmpty()) {
            throw new Exception("RoomType是必填项。");
        }
    }
}