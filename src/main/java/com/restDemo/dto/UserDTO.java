package com.restDemo.dto;

import com.restDemo.commons.Model;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO extends Model {
    private String userId;
    private String emailId;
    private  String name;
    private String mobileNo;
    private String city;
    private String userName;
}
