package com.eoe.service.impl;

import com.eoe.entity.Likes;
import com.eoe.entity.MapInfo;
import com.eoe.entity.ShareInfo;
import com.eoe.mapper.MapInfoMapper;
import com.eoe.mapper.ShareInfoMapper;
import com.eoe.service.MapInfoService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/4 14:29
 * @Decription : Service层用于返回Map所需要的数据源
 */

@Service
public class MapInfoServiceImpl implements MapInfoService {

    @Autowired
    private MapInfoMapper mapInfoMapper;

    @Autowired
    private ShareInfoMapper shareInfoMapper;

    /**
     * 点赞视频
     * @param userId
     * @param videoId
     * @return
     */
    @Override
    public boolean likeVideo(int userId, int videoId) {
        // 检查用户是否已经对该视频进行过点赞操作
        Likes existingLike = mapInfoMapper.findLikesByUserIdAndVideoId(userId, videoId);

        if (existingLike != null) {
            if (existingLike.isLiked()) {
                // 用户已经点赞过，取消点赞
                existingLike.setLiked(false);
                mapInfoMapper.saveLikes(existingLike);
                return true;
            }else if (!existingLike.isLiked()) {
                // 用户还未点赞过，进行点赞
                existingLike.setLiked(true);
                existingLike.setTimestamp(new Timestamp(System.currentTimeMillis()));
                mapInfoMapper.saveLikes(existingLike);
                return true;
            }
        } else {
            // 用户还未点赞过，进行点赞
            Likes newLike = new Likes(userId, videoId, true, new Timestamp(System.currentTimeMillis()));
            mapInfoMapper.insertLikes(newLike);
        }
        return true;
    }

    @Override
    public List<ShareInfo> getSourceByPlaceName(MapInfo mapInfo) {

        List<ShareInfo> res = mapInfoMapper.getSourceByPlaceName(mapInfo.getPlaceName());

        return res;
    }

    @Override
    public boolean insertVideo(ShareInfo shareInfo) {
        shareInfo.setUploadTime(new java.util.Date());
        int res = shareInfoMapper.insert(shareInfo);
        return res!=0;

    }
}
