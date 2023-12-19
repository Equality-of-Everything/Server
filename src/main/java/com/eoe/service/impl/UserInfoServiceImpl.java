package com.eoe.service.impl;

import com.eoe.entity.UserInfo;
import com.eoe.entity.UserLogin;
import com.eoe.exception.UserInfoUpdateAvatarException;
import com.eoe.mapper.UserInfoMapper;
import com.eoe.mapper.UserLoginMapper;
import com.eoe.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/4 14:29
 * @Decription : Service层用于返回Map所需要的数据源
 */

@Service
public class UserInfoServiceImpl implements UserInfoService {


    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserLoginMapper userLoginMapper;

    /**
     * 获取个人页面数据
     * @param userLogin
     * @return
     */
    @Override
    public UserInfo getUserInfo(UserLogin userLogin) {
        UserInfo user = userInfoMapper.getUserInfo(userLogin);
        if(user!= null){
            return user;
        }else{
            return null;
        }
    }

    @Override
    public boolean setUserInfo(UserInfo userInfo) {
        boolean flag = userInfoMapper.insert(userInfo) > 0;
        return flag;
    }


    /**
     * 通过用户名更新用户头像,用户信息表更新的同时，也更新用户登录表
     * @param username
     * @param avatar
     * @return
     */
    @Override
    @Transactional
    public boolean setUserAvatarByUsername(String username, String avatar) {
        if(userLoginMapper.setUserAvatarByUsername(username,avatar) <= 0){
            throw new UserInfoUpdateAvatarException("更新用户头像失败");
        }

        return userInfoMapper.setUserAvatarByUsername(username,avatar) > 0;
    }

}
