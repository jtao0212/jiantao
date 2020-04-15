package com.cmb.jiantao.mapper;

import com.cmb.jiantao.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    List<User> getUserByAge(@Param("name") String name);

    void addUser(User user);
}
