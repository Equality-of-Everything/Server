package com.eoe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eoe.entity.ShareInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/1 16:21
 * @Decription : 用于地图信息存储，并关联数据源（shareInfo表）
 */

@Mapper
public interface ShareInfoMapper extends BaseMapper<ShareInfo> {


    @Insert("insert into share_info(IP,type,comment_id,tag_id,like_user_id,map_info_id,user_info_id,video_url,image_library_id,text,like_count,upload_time,vr_image_url) values(#{ip},#{type},#{commentId},#{tagId},#{likeUserId},#{mapInfoId},#{userInfoId},#{videoUrl},#{imageLibraryId},#{text},#{likeCount},#{uploadTime},#{vrImageUrl})")
    int insert(ShareInfo shareInfo);
}
