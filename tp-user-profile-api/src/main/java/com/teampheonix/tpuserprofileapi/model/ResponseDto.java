package com.teampheonix.tpuserprofileapi.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto <T> {

    private String status;
    private T data;
    private List<ErrorDto> errors;

    public static <T> ResponseDto<T> forSuccess(T data) {
        return new ResponseDto<>("SUCCESS", data, null);
    }

    public static ResponseDto<Object> forError(List<ErrorDto> errors) {
        return new ResponseDto<>("ERROR", null, errors);
    }

}