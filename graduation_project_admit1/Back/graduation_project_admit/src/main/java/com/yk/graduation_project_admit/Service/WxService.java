package com.yk.graduation_project_admit.Service;


import cn.hutool.json.JSONUtil;
import com.yk.graduation_project_admit.pojo.User;
import com.yk.graduation_project_admit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class WxService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    private String appid = "wx3b2dd66cfe6d8d9b";
    private String appsecret = "8032ae51432227662a9bb6b8009d8363";
    private String code;


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

    public User getUser(String openid) {
        User user = new User();
        user = userRepository.findByOpenid(openid);
        return user;
    }
}