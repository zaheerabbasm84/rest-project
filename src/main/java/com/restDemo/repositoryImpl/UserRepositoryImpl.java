package com.restDemo.repositoryImpl;
import com.restDemo.dto.UserDTO;
import com.restDemo.mapper.UserJdbiMapper;
import com.restDemo.mapper.UserMapper;
import org.jdbi.v3.core.Jdbi;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl {
    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;
    private final Jdbi jdbi;
    private final UserJdbiMapper userJdbiMapper;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate,UserMapper userMapper,Jdbi jdbi,UserJdbiMapper userJdbiMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
        this.jdbi=jdbi;
        this.userJdbiMapper = userJdbiMapper;
    }

    public List<UserDTO> getUsers(String query) throws SQLException {
        return jdbcTemplate.query(query, userMapper);
    }

    public List<UserDTO> getUsersJdbi() throws SQLException {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM users")
                        .map(userJdbiMapper)
                        .list()
        );
    }





}
