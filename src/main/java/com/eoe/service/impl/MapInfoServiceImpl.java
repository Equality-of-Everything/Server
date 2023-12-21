package com.eoe.service.impl;

import com.eoe.entity.Comment;
import com.eoe.entity.Likes;
import com.eoe.entity.MapInfo;
import com.eoe.entity.ShareInfo;
import com.eoe.exception.MapInfoUpdateFailException;
import com.eoe.mapper.MapInfoMapper;
import com.eoe.mapper.ShareInfoMapper;
import com.eoe.mapper.UserInfoMapper;
import com.eoe.service.MapInfoService;
import com.eoe.service.UploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.Map;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/4 14:29
 * @Decription : Service层用于返回Map所需要的数据源
 */

@Service
@Slf4j
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

    @Autowired
    private UploadFileService uploadFileService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<ShareInfo> getSourceByPlaceName(MapInfo mapInfo) {

        List<ShareInfo> res = mapInfoMapper.getSourceByPlaceName(mapInfo.getPlaceName());

        return res;
    }

    @Transactional
    @Override
    public boolean insertVideo(String city, MultipartFile file,String username) {
        List<MapInfo> mapInfos = mapInfoMapper.judgePlaceName(city);
        String videoUrl = uploadFileService.uploadFile(file);
        int shareInfoId = userInfoMapper.getShareInfoIdByUsername(username);

        if (mapInfos.size() == 0) {
            int uuidID = Objects.hash(UUID.randomUUID().toString());
            System.out.println("uuidID:"+uuidID);
            int uuidShareID = Objects.hash(UUID.randomUUID().toString());
            System.out.println("uuidShareID:"+uuidShareID);
            MapInfo mapInfo = new MapInfo();
            mapInfo.setId(uuidID);
            mapInfo.setPlaceName(city);
            mapInfo.setShareInfoId(uuidShareID);
            int flagMapInfo = mapInfoMapper.insert(mapInfo);

            int flagShareInfo = shareInfoMapper.insert(new ShareInfo(uuidID,shareInfoId, uuidShareID, videoUrl));

            if(flagMapInfo == 1 && flagShareInfo == 1) return true;
            throw new MapInfoUpdateFailException("视频发布失败");
        }

        Integer shareInfoIdByPlaceName = mapInfoMapper.getShareInfoIdByPlaceName(city);
        int shareInfoIdOld = Objects.hash(UUID.randomUUID().toString());

        int flag = shareInfoMapper.insert(new ShareInfo(shareInfoIdOld,shareInfoId, shareInfoIdByPlaceName, videoUrl));
        if(flag == 1) return true;
        throw new MapInfoUpdateFailException("视频发布失败");
    }

    /**
     * 查询点赞数量
     * @param videoId
     * @return
     */
    @Override
    public int likeVideoCount(int videoId) {
        int res = mapInfoMapper.likeVideoCount(videoId);
        if(res < 0) throw new RuntimeException("查询点赞数量失败");
        return res;
    }

    /**
     * 评论视频
     * @param comment
     * @return
     */
    @Override
    public boolean commentVideo(Comment comment) {
        comment.setCommentDate(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
        boolean res = mapInfoMapper.commentVideo(comment);
        if(res) return true;
        return false;
    }

    /**
     * 查看评论
     * @param videoId
     * @return
     */
    @Override
    public List<Comment> getComment(int videoId) {
        List<Comment> res = mapInfoMapper.getComment(videoId);
        return res;
    }
}
