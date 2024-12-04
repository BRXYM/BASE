package com.ljf.dts.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 留言表
 * @TableName ljf_mess
 */
@TableName(value ="ljf_mess")
@Data
public class LjfMess implements Serializable {
    /**
     * 留言ID
     */
    @TableId(value = "ljf_mess_id", type = IdType.AUTO)
    private Integer mess_id;

    /**
     * 留言时间
     */
    @TableField(value = "ljf_mess_time")
    private String mess_time;

    /**
     * 留言内容
     */
    @TableField(value = "ljf_mess_txt")
    private String mess_txt;

    /**
     * 订单ID
     */
    @TableField(value = "ljf_mess_order_id")
    private Integer order_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}