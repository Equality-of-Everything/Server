package com.eoe.service.impl;

import com.eoe.entity.UserLogin;
import com.eoe.mapper.UserLoginMapper;
import com.eoe.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author : Zhang
 * @Date : Created in 2023/11/29 10:43
 * @Decription :
 */

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserLoginMapper userLoginMapper;


    @Override
    public boolean register(UserLogin userLogin) {
        if (userLoginMapper.selectByUsername(userLogin.getUsername())!= null) {
            return false;
        }
        userLogin.setRegistrationTime(new Timestamp(System.currentTimeMillis()));
        userLogin.setAvatar("static/butter.jpg");
        userLogin.setLoginErrorCount(0);
        return userLoginMapper.insert(userLogin) > 0;
    }

    @Override
    public boolean login(UserLogin userLogin) {
        UserLogin res = userLoginMapper.selectByUsernameAndPassword(userLogin.getUsername(), userLogin.getPassword());
        return res != null;
    }
}