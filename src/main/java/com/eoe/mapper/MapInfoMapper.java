package com.eoe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eoe.entity.MapInfo;
import com.eoe.entity.ShareInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/1 16:21
 * @Decription : 用于地图信息存储，并关联数据源（shareInfo表）
 */

@Mapper
public interface MapInfoMapper extends BaseMapper<MapInfo> {

}
