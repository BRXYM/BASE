package com.hys.runGroup.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private int Cid;
    @TableField("hysCidU")
    private int Uid;
    @TableField("hysCidMO")
    private int MOid;
    @TableField("hysCtxt")
    private String txt;
    @TableField("hysCtime")
    private String time;
}
