package com.eoe.service;

import com.eoe.entity.UserInfo;
import com.eoe.entity.UserLogin;


public interface UserInfoService {

    /**
     * 根据username查id
     * @param username
     * @return
     */
    int getUserIdByUsername(String username);

    /**
     * 获取个人页面数据
     * @param username
     * @return
     */
    UserInfo getUserInfo(String username);


    /**
     * 通过用户名更新用户头像
     * @param username
     * @param avatar
     * @return
     */
    boolean setUserAvatarByUsername(String username,String avatar);


    /**
     * 更新个人信息
     * @param userInfo
     * @return
     */
    boolean updateByName(UserInfo userInfo);
}
