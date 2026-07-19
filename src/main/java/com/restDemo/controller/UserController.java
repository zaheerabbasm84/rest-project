package com.restDemo.controller;

import com.restDemo.apiResponse.ApiResponse;
import com.restDemo.apiResponse.Payload;
import com.restDemo.dto.UserDTO;
import com.restDemo.model.User;
import com.restDemo.service.UserService;
import com.restDemo.commons.DataBaseResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/user")
@Tag(name = "UserController",description = "Controller to handle User")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @Operation(summary = "Insert a new user",description = "Creates a new user in the system and returns the created user object",requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User object to be inserted",required = true,content = @Content(schema = @Schema(implementation = User.class))))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",description = "User successfully created",content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500",description = "Internal Server Error")
    })
    @PostMapping("/insert")
    public ApiResponse insert(@RequestBody User user){
        User result = userService.insert(user);
        return ApiResponse.buildWithPayload(new Payload<User>().addObject(result)).setStatus("OK");
    }

    @GetMapping("/getAllUsers")
    public ApiResponse getAllUsers(){
        DataBaseResult<User> result = userService.getAllUsers();
        System.out.println("User is ::" + result.getResult());
        return ApiResponse.buildWithPayload(new Payload<User>().addObjects(result.getResult()).setCursor(result.getCursor())).setStatus("OK");
    }

    @PutMapping("/update/{id}")
    public ApiResponse update(@PathVariable("id") Long id, @RequestBody User user){
        User result = userService.update(id,user);
        return ApiResponse.buildWithPayload(new Payload<User>().addObject(result)).setStatus("OK");
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete (@PathVariable("id") Long id){
        User result = userService.delete(id);
        return ApiResponse.buildWithPayload(new Payload<User>().addObject(result).addMessage("User Deleted")).setStatus("OK");
    }

    @GetMapping("/getAllUsersDTO")
    public ApiResponse getAllUsersDTO() throws SQLException {
        DataBaseResult<UserDTO> result = userService.getAllUserDTO();
        System.out.println("User is ::" + result.getResult());
        return ApiResponse.buildWithPayload(new Payload<UserDTO>().addObjects(result.getResult()).setCursor(result.getCursor())).setStatus("OK");
    }
}