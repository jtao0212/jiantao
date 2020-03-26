package com.cmb.jiantao.services;

import com.cmb.jiantao.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 获得用户列表
     */
    List<User> getAllUser();
}
