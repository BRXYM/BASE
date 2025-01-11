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
 * 订单表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ord")
public class Ord {
    @TableId(value = "ord_id", type = IdType.AUTO)
    @JsonProperty("ord_id")
    private Integer ordId;

    @TableField("good_id")
    @JsonProperty("good_id")
    private Integer goodId;

    @TableField("user_id")
    @JsonProperty("user_id")
    private Integer userId;

    @TableField("ord_time")
    @JsonProperty("ord_time")
    private Instant ordTime;

    @TableField("ord_num")
    @JsonProperty("ord_num")
    private Integer ordNum;
}
