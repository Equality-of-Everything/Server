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


    /**
     * 注册
     * @param userLogin
     * @return
     */
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

    /**
     * 登录
     * @param userLogin
     * @return
     */
    @Override
    public boolean login(UserLogin userLogin) {
        UserLogin res = userLoginMapper.selectByUsernameAndPassword(userLogin.getUsername(), userLogin.getPassword());
        return res != null;
    }

    /**
     * 查询用户名是否存在
     * @param username
     * @return
     */
    @Override
    public boolean checkUsername(String username) {
        return userLoginMapper.selectByUsername(username) != null;
    }

    @Override
    public boolean resetPasswordByMail(String mail, String password) {
        return userLoginMapper.resetPasswordByMail(mail, password) > 0;
    }

    /**
     * 查询邮箱是否存在
     * @param email
     * @return
     */
    @Override
    public boolean checkEmail(String email) {
        if (userLoginMapper.checkEmail(email) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据用户名获取用户id
     * @param userLogin
     * @return
     */
    @Override
    public Integer getUserIdByUsername(UserLogin userLogin) {
        Integer id = userLoginMapper.getUserIdByUsername(userLogin);
        return id;
    }
}
