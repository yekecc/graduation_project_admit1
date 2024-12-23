package com.yk.graduation_project_admit.pojo;

import lombok.*;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
public class ResponseMessage<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> ResponseMessage<T> success(T data) {
        return new ResponseMessage<>(HttpStatus.OK.value(), "success", data);
    }

    public static <T> ResponseMessage<T> success() {
        return new ResponseMessage<>(HttpStatus.OK.value(), "success", null);
    }

    public static ResponseMessage fail(String message) {
        return new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null);
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}