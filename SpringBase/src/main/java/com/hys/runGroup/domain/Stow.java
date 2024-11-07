package com.hys.runGroup.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private int Sid;
    @TableField("hysSidU")
    private int Uid;
    @TableField("hysSidMO")
    private int MOid;
    @TableField("hysStime")
    private String time;
}
