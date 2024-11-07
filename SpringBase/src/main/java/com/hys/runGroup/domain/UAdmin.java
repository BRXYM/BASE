package com.hys.runGroup.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private int Aid;
    @TableField("hysAname")
    private String name;
    @TableField("hysAphone")
    private String phone;
    @TableField("hysAmail")
    private String mail;
    @TableField("hysApass")
    private String pass;
}
