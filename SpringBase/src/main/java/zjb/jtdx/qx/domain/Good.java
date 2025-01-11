package zjb.jtdx.qx.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 商品表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("good")
public class Good {
    @TableId(value = "good_id", type = IdType.AUTO)
    @JsonProperty("good_id")
    private Integer goodId;

    @TableField("good_name")
    @JsonProperty("good_name")
    private String goodName;

    @TableField("good_price")
    @JsonProperty("good_price")
    private BigDecimal goodPrice;

    @TableField("good_num")
    @JsonProperty("good_num")
    private Integer goodNum;
}
