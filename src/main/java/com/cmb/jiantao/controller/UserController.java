package com.cmb.jiantao.controller;

import com.cmb.jiantao.entity.User;
import com.cmb.jiantao.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/getUserByage")
    public List<User> getUserByAge(int age) {
        return userService.getUserByAge(age);
    }

    @RequestMapping(value = "/addUser")
    public String addUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userService.addUser(user);
    }
}
