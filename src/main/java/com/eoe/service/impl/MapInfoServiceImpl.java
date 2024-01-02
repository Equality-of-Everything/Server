package com.eoe.service.impl;

import com.eoe.entity.*;
import com.eoe.exception.GetVideoLikesStatusException;
import com.eoe.exception.MapInfoUpdateFailException;
import com.eoe.mapper.MapInfoMapper;
import com.eoe.mapper.ShareInfoMapper;
import com.eoe.mapper.UserInfoMapper;
import com.eoe.mapper.UserLoginMapper;
import com.eoe.service.MapInfoService;
import com.eoe.service.UploadFileService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
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

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Override
    public boolean getLikeStatus(int videoId,int userId) {
        Integer likeStatus = mapInfoMapper.getLikeStatus(videoId, userId);
        if(likeStatus == null) throw new GetVideoLikesStatusException("获取点赞状态失败");
        return mapInfoMapper.getLikeStatus(videoId,userId) > 0;
    }

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
                return false;
            }else if (!existingLike.isLiked()) {
                // 用户还未点赞过，进行点赞
                existingLike.setLiked(true);
                existingLike.setTimestamp(new Timestamp(System.currentTimeMillis()));
                mapInfoMapper.saveLikes(existingLike);
                return true;
            }
        } else {
            // 用户还未点赞过，进行点赞
            Likes newLike = new Likes(UUID.randomUUID().toString().hashCode(),userId, videoId, true, new Timestamp(System.currentTimeMillis()));
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
    public boolean insertVideo(String city, MultipartFile file,String username,String longitude,String latitude) {
        List<MapInfo> mapInfos = mapInfoMapper.judgePlaceName(city);
        String videoUrl = uploadFileService.uploadFile(file);
        int shareInfoId = userInfoMapper.getShareInfoIdByUsername(username);
        Double longitude1 = Double.parseDouble(longitude);
        Double latitude1 = Double.parseDouble(latitude);
        BigDecimal latitude2 = new BigDecimal(latitude1);
        BigDecimal longitude2 = new BigDecimal(longitude1);
        int commentUUID = Objects.hash(UUID.randomUUID().toString());

        if (mapInfos.size() == 0) {
            int uuidID = Objects.hash(UUID.randomUUID().toString());
            System.out.println("uuidID:"+uuidID);
            int uuidShareID = Objects.hash(UUID.randomUUID().toString());
            System.out.println("uuidShareID:"+uuidShareID);
            MapInfo mapInfo = new MapInfo();
            mapInfo.setId(uuidID);
            mapInfo.setPlaceName(city);
            mapInfo.setShareInfoId(uuidShareID);
            mapInfo.setLatitude(latitude2);
            mapInfo.setLongitude(longitude2);
            int flagMapInfo = mapInfoMapper.insert(mapInfo);

            int flagShareInfo = shareInfoMapper.insert(new ShareInfo(uuidID,shareInfoId, uuidShareID, videoUrl,commentUUID));

            if(flagMapInfo == 1 && flagShareInfo == 1) return true;
            throw new MapInfoUpdateFailException("视频发布失败");
        }

        Integer shareInfoIdByPlaceName = mapInfoMapper.getShareInfoIdByPlaceName(city);
        int shareInfoIdOld = Objects.hash(UUID.randomUUID().toString());

        int flag = shareInfoMapper.insert(new ShareInfo(shareInfoIdOld,shareInfoId, shareInfoIdByPlaceName, videoUrl,commentUUID));
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
        comment.setCommentDate(new Timestamp(System.currentTimeMillis())+"");
        comment.setCommentTime(new Timestamp(System.currentTimeMillis())+"");
        comment.setId(UUID.randomUUID().toString().hashCode());

        // 1代表视频评论
        comment.setType("1");

        Integer commentId = mapInfoMapper.getCommentIdFromShareInfo(comment.getVideoId());
        comment.setShareInfoId(commentId);
        Integer userId = userLoginMapper.getUserIdByUsername(new UserLogin(comment.getUsername()));
        comment.setUserId(userId);
        String avatar = userInfoMapper.getAvatarByUsername(comment.getUsername());
        comment.setAvatar(avatar);
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

    @Override
    public boolean deleteVideoCommentByUsernameAndVideoIdAndCommentDate(Comment comment) {
        return mapInfoMapper.deleteVideoCommentByUsernameAndVideoIdAndCommentDate(comment) > 0;
    }

    @Override
    public List<String> getPlaceNameByUsername(String username) {
        List<String> res = mapInfoMapper.getPlaceNameByUsername(username);
        return res;
    }
}
