package com.eoe.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eoe.entity.UserInfo;
import com.eoe.entity.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author : hsl
 * @Date : Created in 2023/12/5 9:22
 * @Decription : 用于查询个人信息数据
 */

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Select("select * from user_info where user_id = #{userId}")
    UserInfo getUserInfo(UserLogin userLogin);

    @Select("select max(user_id) from user_info")
    int getMaxUserId();
}
