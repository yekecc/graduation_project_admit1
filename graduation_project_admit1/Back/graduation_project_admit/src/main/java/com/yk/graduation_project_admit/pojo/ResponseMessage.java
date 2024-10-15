package com.yk.graduation_project_admit.pojo;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseMessage<T> {
    private Integer code;
    private String message;
    private T data;

    public <T> ResponseMessage(int value, String success, T data) {
    }


    public static <T> ResponseMessage<T> success(T data) {
        return new ResponseMessage(HttpStatus.OK.value(), "success", data);
    }

    public static <T> ResponseMessage<T> success() {
        return new ResponseMessage(HttpStatus.OK.value(), "success", null);
    }
}