package zjb.jtdx.qx.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * 课程表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cla")
public class Cla {
    @TableId(value = "cla_id", type = IdType.AUTO)
    @JsonProperty("cla_id")
    private Integer claId;

    @TableField("cla_name")
    @JsonProperty("cla_name")
    private String claName;

    @TableField("teach_id")
    @JsonProperty("teach_id")
    private Integer teachId;

    @TableField("cla_time")
    @JsonProperty("cla_time")
    private Instant claTime;
}
