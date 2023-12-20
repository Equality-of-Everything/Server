package com.eoe.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eoe.entity.UserInfo;
import com.eoe.entity.UserLogin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Author : hsl
 * @Date : Created in 2023/12/5 9:22
 * @Decription : 用于更新个人信息数据
 */

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Select("select * from user_info where user_id = #{userId}")
    UserInfo getUserInfo(UserLogin userLogin);

    @Select("select max(user_id) from user_info")
    Integer getMaxUserId();

    /**
     * 根据用户名更新用户头像
     * @param username
     * @param avatar
     * @return
     */
    @Update("update user_info set avatar = #{avatar} where username = #{username}")
    Integer setUserAvatarByUsername(String username,String avatar);

    /**
     * 根据用户名更新用户信息
     * @param userInfo
     * @return
     */
    @Update("update user_info set birthday = #{birthday}, gender = #{gender}, signature = #{signature}, email = #{email} where username = #{username}")
    boolean updateByName(UserInfo userInfo);
}
