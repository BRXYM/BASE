package com.ljf.dts.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 商家表
 * @TableName ljf_sell
 */
@TableName(value ="ljf_sell")
@Data
public class LjfSell implements Serializable {
    /**
     * 商家ID
     */
    @TableId(value = "ljf_sell_id", type = IdType.AUTO)
    private Integer sell_id;

    /**
     * 商家手机号
     */
    @TableField(value = "ljf_sell_phone")
    private String sell_phone;

    /**
     * 商家密码
     */
    @TableField(value = "ljf_sell_psword")
    private String sell_psword;

    /**
     * 商家姓名
     */
    @TableField(value = "ljf_sell_name")
    private String sell_name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}