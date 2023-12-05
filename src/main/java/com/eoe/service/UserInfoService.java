package com.eoe.service;

import com.eoe.entity.UserInfo;
import com.eoe.entity.UserLogin;


public interface UserInfoService {

    /**
     * 获取个人页面数据
     * @param userLogin
     * @return
     */
    UserInfo getUserInfo(UserLogin userLogin);

    /**
     * 更新个人页面数据
     * @param userInfo
     * @return
     */

    boolean setUserInfo(UserInfo userInfo);


    /**
     * 获取最大的用户id
     * @return
     */
    int getMaxUserId();
}
