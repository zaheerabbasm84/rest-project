package com.restDemo.apiexceptions;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ApiException extends RuntimeException {

    private final HttpStatus status;
    private final String error;
    private List<Object> errors;

    public ApiException(String message , HttpStatus status , Throwable cause) {
        super(cause);
        this.status = status;
        this.error = message;
    }

    public ApiException(String message , HttpStatus status) {
        super(message);
        this.status = status;
        this.error = message;
    }

    public ApiException addErrors(Object error){
        if (this.getErrors() == null) {
            this.setErrors(new ArrayList<>());
        }
        this.getErrors().add(error);
        return this;
    }
}
