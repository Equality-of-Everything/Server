package com.eoe.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/1 15:50
 * @Decription :
 */

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@TableName("map_info")
public class MapInfo {
    // ID - 地图信息的唯一标识符
    @TableField("ID")
    private int id;

    // 地名 - 地点的名称
    @TableField("place_name")
    private String placeName;

    // 纬度 - 指定南北位置的地理坐标
    @TableField("latitude")
    private BigDecimal latitude;

    // 经度 - 指定东西位置的地理坐标
    @TableField("longitude")
    private BigDecimal longitude;

    // Share Info Id - 与图文与视频分享信息表关联的ID
    @TableField("share_info_id")
    private int shareInfoId;
}
