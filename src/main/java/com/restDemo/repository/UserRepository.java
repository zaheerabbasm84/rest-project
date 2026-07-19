package com.restDemo.repository;

import com.restDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public List<User> getUserByName(String name);
    public Optional<User> findByEmailId(String emailId);
    public Optional<User> findByUserName(String emailId);
    boolean existsByEmailId(String emailId);
}
