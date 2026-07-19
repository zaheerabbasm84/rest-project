package com.restDemo.service;

import com.restDemo.apiexceptions.ApiException;
import com.restDemo.apiexceptions.ErrorCodes;
import com.restDemo.dto.UserDTO;
import com.restDemo.model.User;
import com.restDemo.repository.UserRepository;
import com.restDemo.commons.DataBaseResult;
import com.restDemo.repositoryImpl.UserRepositoryImpl;
import com.restDemo.tenant.TenantContext;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRepositoryImpl userRepositoryImpl;
    private final EntityManager entityManager;

    public UserService(UserRepository userRepository,UserRepositoryImpl userRepositoryImpl,EntityManager entityManager){
        this.userRepository = userRepository;
        this.userRepositoryImpl = userRepositoryImpl;
        this.entityManager = entityManager;
    }

    public List<User> getUserByName(String name){
        return userRepository.getUserByName(name);
    }

    public User insert(User user) throws ApiException {
        Optional<User> optionalUser = userRepository.findByUserName(user.getUserName());

        if (userRepository.existsByEmailId(user.getEmailId())) {
            throw new ApiException("Email id Already Exist", HttpStatus.BAD_REQUEST,new IllegalStateException("Email id Already Exist")).addErrors(ErrorCodes.EMAIL_ALREADY_EXIST);
        }
        if (optionalUser.isPresent()) {
            throw new ApiException("User Name Already Exist", HttpStatus.BAD_REQUEST,new IllegalStateException("User Name Already Exist")).addErrors(ErrorCodes.USER_NAME_ALREADY_EXIST);
        }
//        Long.parseLong(null);
        return userRepository.save(user);
    }

    public User update(Long id,User user) throws ApiException{
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new ApiException("user not found", HttpStatus.NOT_FOUND).addErrors(ErrorCodes.USER_NOT_FOUNT);
        }
        User existingUser = optionalUser.get();
        existingUser.merge(user);
        existingUser.setUpdatedAt(System.currentTimeMillis());
        userRepository.save(existingUser);
        return existingUser;
    }

    public User delete(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new ApiException("user not found", HttpStatus.NOT_FOUND).addErrors(ErrorCodes.USER_NOT_FOUNT);
        }
        User existingUser = optionalUser.get();
        userRepository.deleteById(id);
        return existingUser;
    }

//    @Transactional
    public DataBaseResult<User> getAllUsers(){
//        Long.parseLong(null);
        System.out.println(
                entityManager.unwrap(org.hibernate.Session.class).hashCode()
        );
        List<User> allUser=userRepository.findAll();

        TenantContext.setTenant("authentication");
        System.out.println(TenantContext.getTenant());

        List<User> allUser1=userRepository.findAll();
        System.out.println(
                entityManager.unwrap(org.hibernate.Session.class).hashCode()
        );
        allUser.addAll(allUser1);
        return new DataBaseResult<>(allUser,1,1,allUser.size(),1);
    }
    
    public DataBaseResult<UserDTO> getAllUserDTO() throws SQLException {
        System.out.println("CURRENT TENANT"+TenantContext.getTenant());
        List<UserDTO> allUser= userRepositoryImpl.getUsers("SELECT * FROM users");
        log.info("ERROR WHILE GETTING");
        TenantContext.setTenant("authentication");
        System.out.println("CURRENT TENANT"+TenantContext.getTenant());
        allUser.addAll(userRepositoryImpl.getUsers("SELECT * FROM users"));
        return new DataBaseResult<>(allUser,1,1,allUser.size(),1);
    }
    
}
