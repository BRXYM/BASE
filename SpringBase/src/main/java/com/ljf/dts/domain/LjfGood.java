package com.ljf.dts.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 商品列表
 * @TableName ljf_good
 */
@TableName(value ="ljf_good")
@Data
public class LjfGood implements Serializable {
    /**
     * 商品ID
     */
    @TableId(value = "ljf_good_id", type = IdType.AUTO)
    private Integer good_id;

    /**
     * 商家ID
     */
    @TableField(value = "ljf_good_sell_id")
    private Integer good_sell_id;

    /**
     * 商品名称
     */
    @TableField(value = "ljf_good_name")
    private String good_name;

    /**
     * 商品数量
     */
    @TableField(value = "ljf_good_num")
    private Integer good_num;

    /**
     * 商品描述
     */
    @TableField(value = "ljf_good_txt")
    private String good_txt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}