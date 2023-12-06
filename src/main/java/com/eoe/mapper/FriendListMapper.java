package com.eoe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eoe.entity.FriendList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface FriendListMapper extends BaseMapper<FriendList> {

    /**
     * 插入好友列表
     * @param friendList
     * @return
     */
    @Insert("INSERT INTO friend_list (user_id, friend_user_id, relationship_time) " +
            "VALUES (#{userId}, #{friendUserId}, #{relationshipTime})")
    int insertFriendList(FriendList friendList);

}
