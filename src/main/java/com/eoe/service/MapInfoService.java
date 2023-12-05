package com.eoe.service;

import com.eoe.entity.MapInfo;
import com.eoe.entity.ShareInfo;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/4 14:28
 * @Decription : 用于获取地图背后的数据源（视频或图文）
 */

public interface MapInfoService {

    /**
     * 根据地名获取视频或图文
     * @param mapInfo
     * @return
     */
    ShareInfo getSourceByPlaceName(MapInfo mapInfo);

    /**
     * 插入视频或图文
     * @param shareInfo
     * @return
     */
    boolean insertVideo(ShareInfo shareInfo);
}