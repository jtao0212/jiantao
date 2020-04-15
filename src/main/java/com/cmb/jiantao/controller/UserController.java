package com.cmb.jiantao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cmb.jiantao.entity.User;
import com.cmb.jiantao.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/getUser")
    public List<User> getUserByAge(String name) {
        return userService.getUserByAge(name);
    }

    @RequestMapping(value = "/addUser")
    public String addUser(@RequestBody User user) {
        user.setUserId(UUID.randomUUID().toString());
        userService.addUser(user);
        Map<String, String> result = new HashMap();
        result.put("result", "success");
        return JSON.toJSONString(result);
    }
}
