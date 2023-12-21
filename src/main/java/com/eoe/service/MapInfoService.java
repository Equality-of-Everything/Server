package com.eoe.service;

import com.eoe.entity.MapInfo;
import com.eoe.entity.ShareInfo;

import java.util.List;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/4 14:28
 * @Decription : 用于获取地图背后的数据源（视频或图文）
 */

public interface MapInfoService {

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
     * @param shareInfo
     * @return
     */
    boolean insertVideo(ShareInfo shareInfo);

    /**
     * 查询点赞数量
     * @param videoId
     * @return
     */
    int likeVideoCount(int videoId);
}
