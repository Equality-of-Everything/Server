package com.eoe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eoe.entity.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

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

    /**
     * 根据邮箱重置密码
     * @param mail
     * @param password
     * @return
     */
    @Update("update user_login set password=#{password} where email=#{mail}")
    int resetPasswordByMail(String mail, String password);

    /**
     * 查询邮箱是否存在
     * @param email
     * @return
     */
    @Select("select count(*) from user_login where email = #{email}")
    int checkEmail(String email);

    /**
     * 根据用户名查找id
     * @param userLogin
     * @return
     */
    @Select("select user_id from user_login where username = #{username}")
    Integer getUserIdByUsername(UserLogin userLogin);

    @Update("update user_login set avatar = #{avatar} where username = #{username}")
    Integer setUserAvatarByUsername(String username,String avatar);

    @Update("update user_login set last_login_date = #{timestamp} where username = #{username}")
    void setLastLoginDate(String username, Timestamp timestamp);
}
