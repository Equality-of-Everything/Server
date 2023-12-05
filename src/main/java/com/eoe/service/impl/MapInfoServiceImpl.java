package com.eoe.service.impl;

import com.eoe.entity.MapInfo;
import com.eoe.entity.ShareInfo;
import com.eoe.mapper.MapInfoMapper;
import com.eoe.mapper.ShareInfoMapper;
import com.eoe.service.MapInfoService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public ShareInfo getSourceByPlaceName(MapInfo mapInfo) {

        ShareInfo res = mapInfoMapper.getSourceByPlaceName(mapInfo.getPlaceName());

        return res!=null ? res : null;
    }

    @Override
    public boolean insertVideo(ShareInfo shareInfo) {
        shareInfo.setUploadTime(new java.util.Date());
        int res = shareInfoMapper.insert(shareInfo);
        return res!=0;

    }
}
