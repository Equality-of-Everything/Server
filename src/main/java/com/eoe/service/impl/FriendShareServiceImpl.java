package com.eoe.service.impl;

import cn.hutool.db.PageResult;
import com.eoe.entity.FriendCircleItem;
import com.eoe.entity.FriendShare;
import com.eoe.entity.ImageLibrary;
import com.eoe.mapper.FriendShareMapper;
import com.eoe.mapper.UserInfoMapper;
import com.eoe.service.FriendShareService;
import com.eoe.service.UploadFileService;
import com.eoe.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class FriendShareServiceImpl implements FriendShareService {

    @Autowired
    private FriendShareMapper friendShareMapper;

    @Autowired
    private UploadFileService uploadFileService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 上传朋友圈
     * @param friendShare
     * @param files
     * @return
     */
    @Override
    @Transactional
    public boolean uploadFriendShare(FriendShare friendShare,String username, MultipartFile[] files) {
        System.out.println(files.length);
        int id = friendShareMapper.getShareId(username);
        friendShare.setUserInfoId(id);
        friendShare.setCommentTime(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
        int uuidShareID = Objects.hash(UUID.randomUUID().toString());
        friendShare.setImageLibraryId(uuidShareID);
        friendShareMapper.insert(friendShare);
        for (MultipartFile file : files) {
            String url = uploadFileService.uploadFile(file);
            ImageLibrary imageLibrary = new ImageLibrary();
            imageLibrary.setFriendShareId(uuidShareID);
            imageLibrary.setImageUrl(url);
            imageLibrary.setUploadTime(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
            friendShareMapper.insertvideo(imageLibrary);
        }
        return true;
    }

    @Override
    public boolean deleteFriendShare(int id) {
        int imageLibraryId = friendShareMapper.selectById(id).getImageLibraryId();
      //  friendShareMapper.deleteImageLibraryById(imageLibraryId);
        return false;

    }

    @Override
    public PageResult getFriendShareList() {
        return null;
    }

    // 通过分页获取朋友圈的分享
    @Override
    public List<FriendCircleItem> getFriendShareList(int page, int size) {
        size = 5;
        List<FriendShare> res = friendShareMapper.getFriendShareList(page, size);
        List<FriendCircleItem> friendCircleItems = new ArrayList<>();
        for (FriendShare friendShare : res) {
            FriendCircleItem friendCircleItem = new FriendCircleItem();
            String username = userInfoMapper.getUsernameByFriendShareId(friendShare.getUserInfoId());
            friendCircleItem.setUserName(username);
            friendCircleItem.setTextContent(friendShare.getTextContent());
            friendCircleItem.setAvatarUrl(userInfoMapper.getAvatarByUsername(username));
            friendCircleItem.setPublishTime(Timestamp.valueOf(friendShare.getCommentTime())+"");
            friendCircleItem.setMediaUrls(friendShareMapper.getImageUrlByFriendShareId(friendShare.getImageLibraryId()));
            friendCircleItems.add(friendCircleItem);
        }
        return friendCircleItems;
    }
}
