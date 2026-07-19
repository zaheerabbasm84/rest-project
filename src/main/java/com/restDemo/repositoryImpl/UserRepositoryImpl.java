package com.restDemo.repositoryImpl;
import com.restDemo.dto.UserDTO;
import com.restDemo.mapper.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class UserRepositoryImpl {
    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate,UserMapper userMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getUsers(String query) throws SQLException {
        return jdbcTemplate.query(query, userMapper);
    }

}
