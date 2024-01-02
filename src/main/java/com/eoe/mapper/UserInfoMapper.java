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

    @Select("select * from user_info where username = #{username}")
    UserInfo getUserInfo(String username);

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
    // 通过用户名获取分享信息id
    @Select("select share_info_id from user_info where username=#{username}")
    int getShareInfoIdByUsername(String username);

    // 通过用户名获取头像
    @Select("select avatar from user_info where username=#{username}")
    String getAvatarByUsername(String username);

    @Select("select avatar from user_info where share_info_id=#{shareInfoId}")
    String getAvatarByShareInfoId(int shareInfoId);

    @Select("select username from user_info where share_info_id=#{shareInfoId}")
    String getUsernameByShareInfoId(int shareInfoId);

}
