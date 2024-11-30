package com.ljf.dts.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 管理员表
 * @TableName ljf_admin
 */
@TableName(value ="ljf_admin")
@Data
public class LjfAdmin implements Serializable {
    /**
     * 管理员ID
     */
    @TableId(value = "ljf_adm_id", type = IdType.AUTO)
    private Integer adm_id;

    /**
     * 管理员姓名
     */
    @TableField(value = "ljf_adm_name")
    private String adm_name;

    /**
     * 管理员手机号
     */
    @TableField(value = "ljf_adm_phone")
    private String adm_phone;

    /**
     * 管理员密码
     */
    @TableField(value = "ljf_adm_psword")
    private String adm_psword;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}