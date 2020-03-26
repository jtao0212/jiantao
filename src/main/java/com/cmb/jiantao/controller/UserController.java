package com.cmb.jiantao.controller;

import com.cmb.jiantao.entity.User;
import com.cmb.jiantao.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/getUserByAge")
    public List<User> getUserByAge(int age) {
        return userService.getUserByAge(age);
    }

    @RequestMapping(value = "/addUser")
    public void addUser(@RequestBody User user) {
        user.setUserId(UUID.randomUUID().toString());
        userService.addUser(user);
    }
}
