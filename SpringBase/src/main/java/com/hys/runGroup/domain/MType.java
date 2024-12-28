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
 * 分类表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("hyst")
public class MType {

    @TableId(value = "hysTid", type = IdType.AUTO)
    @JsonProperty("Tid")
    private int Tid;

    @TableField("hysTname")
    @JsonProperty("name")
    private String name;
}
