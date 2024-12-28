package com.hys.runGroup.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 留言表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("hysme")
public class Message {

    @TableId(value = "hysMEid", type = IdType.AUTO)
    private int MEid;

    @TableField("hysMEtxt")
    private String txt;

    @TableField("hysMEtime")
    private String time;

    @TableField("hysMEtoid")
    private int Utoid;

    @TableField("hysMEidU")
    private int Uid;

}
