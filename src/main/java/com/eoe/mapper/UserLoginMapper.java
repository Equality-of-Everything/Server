package com.eoe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eoe.entity.UserLogin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author : Zhang
 * @Date : Created in 2023/11/28 19:30
 * @Decription : 登录的mapper文件，用于与数据库交互
 */


@Mapper
public interface UserLoginMapper extends BaseMapper<UserLogin> {
}
