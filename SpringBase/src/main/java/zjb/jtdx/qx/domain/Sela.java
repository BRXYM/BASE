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
 * 选课表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sela")
public class Sela {
    @TableId(value = "sela_id", type = IdType.AUTO)
    @JsonProperty("sela_id")
    private Integer selaId;

    @TableField("cla_id")
    @JsonProperty("cla_id")
    private Integer claId;

    @TableField("user_id")
    @JsonProperty("user_id")
    private Integer userId;
}
