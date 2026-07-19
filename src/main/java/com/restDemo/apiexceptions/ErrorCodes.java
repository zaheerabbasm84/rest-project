package com.restDemo.apiexceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(
        shape = JsonFormat.Shape.OBJECT
)
@Getter
public enum ErrorCodes {

    USER_NOT_FOUNT(1001,"User Is Not Present In The System"),
    USER_NAME_ALREADY_EXIST(1002,"User Name Already Exist"),
    EMAIL_ALREADY_EXIST(1003,"Email Id Already Exist");

    int code;
    String message;

    private ErrorCodes(int code,String message){
        this.code=code;
        this.message=message;
    }
}
