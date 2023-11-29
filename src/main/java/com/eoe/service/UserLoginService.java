package com.eoe.service;

import com.eoe.entity.UserLogin;
import com.eoe.mapper.UserLoginMapper;

/**
 * @Author : Zhang
 * @Date : Created in 2023/11/28 19:43
 * @Decription : 用户登录Service接口
 */

public interface UserLoginService  {

    boolean register(UserLogin userLogin);

    boolean login(UserLogin userLogin);
}
