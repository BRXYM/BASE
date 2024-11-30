package com.ljf.dts.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 地址表
 * @TableName ljf_ads
 */
@TableName(value ="ljf_ads")
@Data
public class LjfAds implements Serializable {
    /**
     * 地址ID
     */
    @TableId(value = "ljf_ads_id", type = IdType.AUTO)
    private Integer ads_id;

    /**
     * 地址
     */
    @TableField(value = "ljf_ads_txt")
    private String ads_txt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}