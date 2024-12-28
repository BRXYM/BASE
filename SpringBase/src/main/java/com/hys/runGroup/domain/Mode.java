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
 * 模组表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("hysmo")
public class Mode {

    @TableId(value = "hysMOid", type = IdType.AUTO)
    @JsonProperty("MOid")
    private int MOid;

    @TableField("hysMOidT")
    @JsonProperty("Tid")
    private int Tid;

    @TableField("hysMOidU")
    @JsonProperty("Uid")
    private int Uid;

    @TableField("hysMOtime")
    @JsonProperty("time")
    private String time;

    @TableField("hysMOtxt")
    @JsonProperty("txt")
    private String txt;

    @TableField("hysMOsum")
    @JsonProperty("sum")
    private int sum;
}
