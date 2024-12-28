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
 * 收藏表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("hyss")
public class Stow {

    @TableId(value = "hysSid", type = IdType.AUTO)
    @JsonProperty("Sid")
    private int Sid;

    @TableField("hysSidU")
    @JsonProperty("Uid")
    private int Uid;

    @TableField("hysSidMO")
    @JsonProperty("MOid")
    private int MOid;

    @TableField("hysStime")
    @JsonProperty("time")
    private String time;
}
