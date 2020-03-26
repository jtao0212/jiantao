package com.cmb.jiantao.service;

import com.cmb.jiantao.entity.User;
import com.cmb.jiantao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserByAge(int age) {
        return userMapper.getUserByAge(age);
    }

    @Override
    public void addUser(User user) {
         userMapper.addUser(user);
    }
}
