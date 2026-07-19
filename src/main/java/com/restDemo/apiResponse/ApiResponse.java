package com.restDemo.apiResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
public class ApiResponse {
    private Payload payload;
    private transient String status;

    private ApiResponse(Payload payload) {
        this.payload = payload;
    }


    public static ApiResponse build() {
        return new ApiResponse();
    }

    public static ApiResponse buildWithPayload(Payload payload) {
        return new ApiResponse(payload);
    }

    public ApiResponse setStatus(String status){
        this.status= status;
        return this;
    }
}
