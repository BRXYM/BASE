package com.ljf.dts.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 订单表
 * @TableName ljf_order
 */
@TableName(value ="ljf_order")
@Data
public class LjfOrder implements Serializable {
    /**
     * 订单ID
     */
    @TableId(value = "ljf_order_id", type = IdType.AUTO)
    private Integer order_id;

    /**
     * 商品ID
     */
    @TableField(value = "ljf_order_good_id")
    private Integer order_good_id;

    /**
     * 用户ID
     */
    @TableField(value = "ljf_order_user_id")
    private Integer order_user_id;

    /**
     * 订单时间
     */
    @TableField(value = "ljf_order_time")
    private Date order_time;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}