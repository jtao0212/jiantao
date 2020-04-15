package com.cmb.jiantao.service;

import com.cmb.jiantao.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 根据年龄获取用户列表
     */
    List<User> getUserByAge(String name);

    void addUser(User user);
}
