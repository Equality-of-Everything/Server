package com.eoe.service;

import com.eoe.entity.Comment;
import com.eoe.entity.MapInfo;
import com.eoe.entity.ShareInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/4 14:28
 * @Decription : 用于获取地图背后的数据源（视频或图文）
 */

public interface MapInfoService {

    /**
     * 获取点赞状态
     * @param userId
     * @param videoId
     * @return
     */
    boolean getLikeStatus(int videoId,int userId );

    /**
     * 点赞视频或图文
     * @param userId
     * @param videoId
     * @return
     */
    boolean likeVideo(int userId, int videoId);

    /**
     * 根据地名获取视频或图文
     * @param mapInfo
     * @return
     */
    List<ShareInfo> getSourceByPlaceName(MapInfo mapInfo);

    /**
     * 插入视频或图文
     * @param
     * @return
     */
    boolean insertVideo(String city, MultipartFile file,String username,String longitude,String latitude);

    /**
     * 查询点赞数量
     * @param videoId
     * @return
     */
    int likeVideoCount(int videoId);

    /**
     * 评论视频或图文
     * @param comment
     * @return
     */
    boolean commentVideo(Comment comment);

    /**
     * 查看评论
     * @param videoId
     * @return
     */
    List<Comment> getComment(int videoId);

    /**
     * 删除评论通过用户名时间，视频iD
     * @param comment
     * @return
     */
    boolean deleteVideoCommentByUsernameAndVideoIdAndCommentDate(Comment comment);

    // 通过用户名去获取他在哪发布的视频
    List<String> getPlaceNameByUsername(String username);
}
