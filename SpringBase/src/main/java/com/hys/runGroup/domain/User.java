package com.hys.runGroup.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("hysu")
public class User {
    @TableId(value = "hysUid", type = IdType.AUTO)
    private int Uid;
    @TableField("hysUname")
    private String Uname;
    @TableField("hysUphone")
    private String Uphone;
    @TableField("hysUqq")
    private String Uqq;
    @TableField("hysUmile")
    private String Umile;
    @TableField("hysUpass")
    private String Upass;
}
