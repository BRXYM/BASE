package com.hys.runGroup.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private int MOid;
    @TableField("hysMOidT")
    private int Tid;
    @TableField("hysMOidU")
    private int Uid;
    @TableField("hysMOtime")
    private String time;
    @TableField("hysMOtxt")
    private String txt;
    @TableField("hysMOsum")
    private int sum;
}
