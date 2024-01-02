package com.eoe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eoe.entity.Comment;
import com.eoe.entity.Likes;
import com.eoe.entity.MapInfo;
import com.eoe.entity.ShareInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.javassist.runtime.Inner;

import java.util.List;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/1 16:21
 * @Decription : 用于地图信息存储，并关联数据源（shareInfo表）
 */

@Mapper
public interface MapInfoMapper extends BaseMapper<MapInfo> {

    // 删除评论
    @Delete("delete from comments where username = #{username} and video_id = #{videoId} and comment_date=#{commentDate}")
    Integer deleteVideoCommentByUsernameAndVideoIdAndCommentDate(Comment comment);

    @Select("select comment_id from share_info where id=#{videoId}")
    Integer getCommentIdFromShareInfo(int videoId);

    @Select("select is_liked from likes where user_id=#{userId} and video_id=#{videoId}")
    Integer getLikeStatus(int videoId, int userId);

    /**
     * 评论视频
     * @param comment
     * @return
     */
    @Insert("INSERT INTO comments(id,share_info_id,comment_text,avatar,type,friend_share_id,user_id,comment_time,video_id,comment_date,username) VALUES(#{id},#{shareInfoId},#{commentText},#{avatar},#{type},#{friendShareId},#{userId},#{commentTime},#{videoId},#{commentDate},#{username})")
    boolean commentVideo(Comment comment);

    /**
     * 根据用户和视频id查询是否点赞
     *
     * @param userId
     * @param videoId
     * @return
     */
    @Select("select * from likes where user_id=#{userId} and video_id=#{videoId}")
    Likes findLikesByUserIdAndVideoId(int userId, int videoId);

    @Select("select * from share_info where map_info_id in\n" +
            "            (select share_info_id from map_info where place_name=#{placeName})")
    List<ShareInfo> getSourceByPlaceName(String placeName);

    /**
     * 修改点赞状态
     * @param existingLike
     */
    @Update("UPDATE likes SET is_liked = #{isLiked}, timestamp = #{timestamp} WHERE id = #{id}")
    void saveLikes(Likes existingLike);

    /**
     * 插入点赞
     * @param newLike
     */
    @Insert("INSERT INTO likes(id,user_id,video_id,is_liked,timestamp) VALUES(#{id},#{userId},#{videoId},#{isLiked},#{timestamp})")
    void insertLikes(Likes newLike);

    /**
     * 查询视频点赞数
     * @param videoId
     * @return
     */
    @Select("select count(*) from likes where video_id=#{videoId} and is_liked=1")
    int likeVideoCount(int videoId);

    // 判断地名是否存在
    @Select("select * from map_info where place_name=#{placeName}")
    List<MapInfo> judgePlaceName(String placeName);

    // 获取shareInfoId
    @Select("select share_info_id from map_info where place_name=#{placeName}")
    Integer getShareInfoIdByPlaceName(String placeName);

    /**
     * 获取评论
     * @param videoId
     * @return
     */
    @Select("select * from comments where video_id=#{videoId}")
    List<Comment> getComment(Integer videoId);

    // 通过用户名去查询地点名，三张表联查，user_info(share_info_id),share_info(user_info_id,map_info_id),map_info(share_info_id)
    @Select("select place_name from map_info where share_info_id in " +
            "(select distinct map_info_id from share_info where user_info_id=" +
            "(select share_info_id from user_info where username=#{username}));")
    List<String> getPlaceNameByUsername(String username);
}
