package com.eoe.service.impl;

import com.eoe.entity.UserInfo;
import com.eoe.entity.UserLogin;
import com.eoe.exception.RegisterFailException;
import com.eoe.mapper.UserInfoMapper;
import com.eoe.mapper.UserLoginMapper;
import com.eoe.result.MessageConstant;
import com.eoe.service.UserInfoService;
import com.eoe.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @Author : Zhang
 * @Date : Created in 2023/11/29 10:43
 * @Decription :
 */

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;



    /**
     * 注册
     * @param userLogin
     * @return
     * 注册成功的同时在UserInfo里面插入一条数据
     */
    @Override
    @Transactional
    public boolean register(UserLogin userLogin)  {
        if (userLoginMapper.selectByUsername(userLogin.getUsername())!= null)
            throw new RegisterFailException(MessageConstant.UsernameAlreadyExist);

        if (userLoginMapper.checkEmail(userLogin.getEmail()) > 0)
            throw new RegisterFailException(MessageConstant.EmailAlreadyExist);

        userLogin.setRegistrationTime(new Timestamp(System.currentTimeMillis()));
        userLogin.setAvatar("static/butter.jpg");
        userLogin.setLoginErrorCount(0);
        int userId = Objects.hash(UUID.randomUUID().toString());
        userLogin.setUserId(userId);
        int resLogin = userLoginMapper.insert(userLogin);

        //Integer userId = userLoginMapper.getUserIdByUsername(userLogin);
        int shareInfoId = Objects.hash(UUID.randomUUID().toString());
        int resInfo = userInfoMapper.insert(new UserInfo(userId, userLogin.getUsername(), userLogin.getAvatar(),shareInfoId));
        if (resLogin > 0 && resInfo > 0)  return true;

        return false;
    }

    /**
     * 登录
     * @param userLogin
     * @return
     */
    @Override
    public boolean login(UserLogin userLogin) {
        UserLogin res = userLoginMapper.selectByUsernameAndPassword(userLogin.getUsername(), userLogin.getPassword());
        userLoginMapper.setLastLoginDate(userLogin.getUsername(), new Timestamp(System.currentTimeMillis()));
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
