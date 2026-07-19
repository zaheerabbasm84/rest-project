package com.restDemo.mapper;

import com.restDemo.dto.UserDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<UserDTO> {
    @Override
    public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(rs.getLong("id"));
        userDTO.setUserId(rs.getString("user_id"));
        userDTO.setName(rs.getString("name"));
        userDTO.setEmailId(rs.getString("email_id"));
        userDTO.setCity(rs.getString("city"));
        userDTO.setMobileNo(rs.getString("mobile_no"));
        userDTO.setCreatedAt(rs.getLong("created_at"));
        userDTO.setCreatedBy(rs.getLong("created_by"));
        userDTO.setUpdatedAt(rs.getLong("updated_at"));
        userDTO.setUpdatedBy(rs.getLong("updated_by"));
//        userDTO.setUpdatedBy(rs.getLong("updated_by_d"));
        return userDTO;
    }
}
