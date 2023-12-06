package com.eoe.service.impl;

import com.eoe.entity.UserInfo;
import com.eoe.entity.UserLogin;
import com.eoe.mapper.UserInfoMapper;
import com.eoe.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/4 14:29
 * @Decription : Service层用于返回Map所需要的数据源
 */

@Service
public class UserInfoServiceImpl implements UserInfoService {


    @Autowired
    private UserInfoMapper userInfoMapper;

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
     * 获取用户最大id
     *
     * @return
     */
    @Override
    public int getMaxUserId() {
        if(userInfoMapper.getMaxUserId() == null){
            return 1;
        }
        if(userInfoMapper.getMaxUserId() > 0){
            return userInfoMapper.getMaxUserId();
        }
        return 1;
    }
}
