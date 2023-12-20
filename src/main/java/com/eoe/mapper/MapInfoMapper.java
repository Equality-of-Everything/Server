package com.eoe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eoe.entity.Likes;
import com.eoe.entity.MapInfo;
import com.eoe.entity.ShareInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/1 16:21
 * @Decription : 用于地图信息存储，并关联数据源（shareInfo表）
 */

@Mapper
public interface MapInfoMapper extends BaseMapper<MapInfo> {

    /**
     * 根据用户和视频id查询是否点赞
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
    @Update("UPDATE Likes SET is_liked = #{isLiked}, timestamp = #{timestamp} WHERE id = #{id}")
    void saveLikes(Likes existingLike);

    /**
     * 插入点赞
     * @param newLike
     */
    @Insert("INSERT INTO likes(user_id,video_id,is_liked,timestamp) VALUES(#{userId},#{videoId},#{isLiked},#{timestamp})")
    void insertLikes(Likes newLike);
}
