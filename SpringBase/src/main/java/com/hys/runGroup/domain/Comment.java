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
 * 评论表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("hysc")
public class Comment {

    @TableId(value = "hysCid", type = IdType.AUTO)
    @JsonProperty("Cid")
    private int Cid;

    @TableField("hysCidU")
    @JsonProperty("Uid")
    private int Uid;

    @TableField("hysCidMO")
    @JsonProperty("MOid")
    private int MOid;

    @TableField("hysCtxt")
    @JsonProperty("txt")
    private String txt;

    @TableField("hysCtime")
    @JsonProperty("time")
    private String time;
}
