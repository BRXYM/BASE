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
 * 教练表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("teach")
public class Teach {
    @TableId(value = "teach_id", type = IdType.AUTO)
    @JsonProperty("teach_id")
    private Integer teachId;

    @TableField("teach_name")
    @JsonProperty("teach_name")
    private String teachName;

    @TableField("teach_img")
    @JsonProperty("teach_img")
    private String teachImg;

    @TableField("teach_phone")
    @JsonProperty("teach_phone")
    private String teachPhone;

    @TableField("teach_pass")
    @JsonProperty("teach_pass")
    private String teachPass;

    @TableField("teach_txt")
    @JsonProperty("teach_txt")
    private String teachTxt;
}
