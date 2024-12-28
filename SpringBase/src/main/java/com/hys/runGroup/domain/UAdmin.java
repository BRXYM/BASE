package com.hys.runGroup.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("hysa")
public class UAdmin {

    @TableId(value = "hysAid", type = IdType.AUTO)
    @JsonProperty("Aid")
    private int Aid;

    @TableField("hysAname")
    @JsonProperty("name")
    private String name;

    @TableField("hysAphone")
    @JsonProperty("phone")
    private String phone;

    @TableField("hysAmail")
    @JsonProperty("mail")
    private String mail;

    @TableField("hysApass")
    @JsonProperty("pass")
    private String pass;
}
