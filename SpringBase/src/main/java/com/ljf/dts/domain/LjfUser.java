package com.ljf.dts.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 用户表
 * @TableName ljf_user
 */
@TableName(value ="ljf_user")
@Data
public class LjfUser implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "ljf_user_id", type = IdType.AUTO)
    private Integer user_id;

    /**
     * 用户手机号
     */
    @TableField(value = "ljf_user_phone")
    private String user_phone;

    /**
     * 用户密码
     */
    @TableField(value = "ljf_user_psword")
    private String user_psword;

    /**
     * 用户昵称
     */
    @TableField(value = "ljf_user_name")
    private String user_name;

    /**
     * 
     */
    @TableField(value = "ljf_user_ads_id")
    private Integer user_ads_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}