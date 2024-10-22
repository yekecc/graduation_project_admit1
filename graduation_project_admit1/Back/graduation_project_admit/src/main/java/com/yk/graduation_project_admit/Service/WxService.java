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

    /**
     * @param openid
     * @return user
     */
    public User getUser(String openid) {
        User user = new User();
        user = userRepository.findByOpenid(openid);
        return user;
    }

    /**
     * @param user
     * @return user
     */
    public User addUser(UserDto user) {
        User user1 = new User();
        BeanUtils.copyProperties(user, user1);
        return userRepository.save(user1);
    }

    /**
     * @param openid
     * @param user
     */
    public void updateUser(String openid, User user) {
    }

    /**
     * @param borrowDto
     * @return Borrow
     */
    public Borrow addlog(BorrowDto borrowDto) {
        Borrow borrow = new Borrow();
        BeanUtils.copyProperties(borrowDto, borrow);
        return borrowRepository.save(borrow);
    }
}