package com.restDemo.model;

import com.restDemo.commons.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User extends Model {
    private String emailId;
    private String userName;
    private String password;
    private  String name;
    private String mobileNo;
    private String city;
    private String userId;

    public void merge(User user){
        if(!Objects.equals(user.getName(),null)){
            this.setName(user.getName());
        }
        if(!Objects.equals(user.getMobileNo(),null)){
            this.setMobileNo(user.getMobileNo());
        }
        if(!Objects.equals(user.getEmailId(),null)){
            this.setEmailId(user.getEmailId());
        }
        if(!Objects.equals(user.getUserName(),null)){
            this.setUserName(user.getUserName());
        }
        if(!Objects.equals(user.getPassword(),null)){
            this.setPassword(user.getPassword());
        }
        if(!Objects.equals(user.getCity(),null)){
            this.setCity(user.getCity());
        }
        if(!Objects.equals(user.getUserId(),null)){
            this.setUserId(user.getUserId());
        }
    }
}
