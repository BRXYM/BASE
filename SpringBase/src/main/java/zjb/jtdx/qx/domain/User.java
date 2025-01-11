package zjb.jtdx.qx.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 用户表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
    @TableId(value = "user_id", type = IdType.AUTO)
    @JsonProperty("user_id")
    private Integer user_id;

    @TableField("user_img")
    @JsonProperty("user_img")
    private String user_img;

    @TableField("user_name")
    @JsonProperty("user_name")
    private String user_name;

    @TableField("user_phone")
    @JsonProperty("user_phone")
    private String user_phone;

    @TableField("user_pass")
    @JsonProperty("user_pass")
    private String user_pass;
}
