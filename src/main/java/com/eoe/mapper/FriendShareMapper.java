package com.eoe.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eoe.entity.FriendShare;
import com.eoe.entity.ImageLibrary;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface FriendShareMapper extends BaseMapper<FriendShare> {

    /**
     * 上传朋友圈
     * @param friendShare
     * @return
     */
    @Insert("INSERT INTO friend_share (user_info_id, text_content, like_count,like_user_id,image_library_id) " +
            "VALUES (#{userInfoId}, #{textContent},#{likeCount}, #{likeUserId}, #{image_library_id})")
    int insert(FriendShare friendShare);

    /**
     * 将朋友圈的图文信息上传到图文库
     * @param imageLibrary
     */
    @Insert("INSERT INTO image_library (image_url, upload_time, friend_share_id) " +
            "VALUES (#{imageUrl}, #{uploadTime}, #{friendShareId})")
    void insertvideo(ImageLibrary imageLibrary);

    /**
     * 根据姓名查询分享id
     * @param username
     * @return
     */
    @Select("select friend_share_id from user_info where username = #{username}")
    int getShareId(String username);
}
