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
 * 用户表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("hysu")
public class User {
    @TableId(value = "hysUid", type = IdType.AUTO)
    @JsonProperty("Uid")
    private int Uid;

    @TableField("hysUimg")
    @JsonProperty("Uimg")
    private String Uimg;

    @TableField("hysUname")
    @JsonProperty("Uname")
    private String Uname;

    @TableField("hysUphone")
    @JsonProperty("Uphone")
    private String Uphone;

    @TableField("hysUqq")
    @JsonProperty("Uqq")
    private String Uqq;

    @TableField("hysUmile")
    @JsonProperty("Umile")
    private String Umile;

    @TableField("hysUpass")
    @JsonProperty("Upass")
    private String Upass;
}
