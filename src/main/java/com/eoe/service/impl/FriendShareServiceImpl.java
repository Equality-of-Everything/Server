package com.eoe.service.impl;

import cn.hutool.db.PageResult;
import com.eoe.entity.FriendShare;
import com.eoe.entity.ImageLibrary;
import com.eoe.mapper.FriendShareMapper;
import com.eoe.service.FriendShareService;
import com.eoe.service.UploadFileService;
import com.eoe.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Service
public class FriendShareServiceImpl implements FriendShareService {

    @Autowired
    private FriendShareMapper friendShareMapper;

    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 上传朋友圈
     * @param friendShare
     * @param files
     * @return
     */
    @Override
    public boolean uploadFriendShare(FriendShare friendShare,String username, MultipartFile[] files) {

        int id = friendShareMapper.getShareId(username);
        friendShare.setUserInfoId(id);
        int uuidShareID = Objects.hash(UUID.randomUUID().toString());
        friendShare.setImage_library_id(uuidShareID);
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
        int imageLibraryId = friendShareMapper.selectById(id).getImage_library_id();
      //  friendShareMapper.deleteImageLibraryById(imageLibraryId);
        return false;

    }

    @Override
    public PageResult getFriendShareList() {
        return null;
    }
}
