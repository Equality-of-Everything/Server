package com.eoe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eoe.entity.InstantMessaging;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface InstantMessagingMapper extends BaseMapper<InstantMessaging> {


    /**
     * 插入一条消息
     * @param instantMessaging
     * @return
     */
    @Insert("INSERT INTO instant_messaging (sender_user_id, receiver_user_id, content, time) " +
            "VALUES (#{senderUserId}, #{receiverUserId}, #{content}, #{time})")
    int insertInstantMessaging(InstantMessaging instantMessaging);


}
