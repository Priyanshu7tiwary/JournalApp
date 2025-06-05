package com.journal.journal.controller;

import com.journal.journal.dto.UserDto;
import com.journal.journal.entity.User;
import com.journal.journal.service.AdminService;
import com.journal.journal.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/public")
@Tag(name="public apis")
public class PublicController {
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
    @PostMapping("/create-user")

    public void createUser(@RequestBody UserDto U){

        User newUser = new User();
        newUser.setUserName(U.getUserName());
        newUser.setPassword(U.getPassword());

        userService.saveUser(new User());
    }
    @PostMapping("/create-admin")

    public void createAdmin(@RequestBody User U){
        adminService.saveUser(U);

    }

    @GetMapping("/all")
    public List<User> getAllUser(){
        return userService.getAll();
    }
}

