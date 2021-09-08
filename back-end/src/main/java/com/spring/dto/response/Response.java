package com.spring.dto.response;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private T data;

    private Object errors;

    public void addErrorMsgToResponse(String msgError) {
        ResponseError error = new ResponseError()
                                .setDetails(msgError)
                                .setTimestamp(LocalDateTime.now());

        setErrors(error);
    }
}
