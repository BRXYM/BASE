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
 * 留言表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("hysme")
public class Message {

    @TableId(value = "hysMEid", type = IdType.AUTO)
    @JsonProperty("MEid")
    private int MEid;

    @TableField("hysMEtxt")
    @JsonProperty("txt")
    private String txt;

    @TableField("hysMEtime")
    @JsonProperty("time")
    private String time;

    @TableField("hysMEtoid")
    @JsonProperty("Utoid")
    private int Utoid;

    @TableField("hysMEidU")
    @JsonProperty("Uid")
    private int Uid;
}
