package com.eoe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eoe.entity.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author : Zhang
 * @Date : Created in 2023/11/28 19:30
 * @Decription : 登录的mapper文件，用于与数据库交互
 */


@Mapper
public interface UserLoginMapper extends BaseMapper<UserLogin> {

    /**
     * 根据用户名查询用户信息,用于验证是否存在该用户
     * @param username
     * @return
     */
    @Select("select * from user_login where username = #{username}")
    UserLogin selectByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息，用于验证用户是否存在
     * @param username
     * @param password
     * @return
     */
    @Select("select * from user_login where username = #{username} and password = #{password}")
    UserLogin selectByUsernameAndPassword(String username, String password);
}
