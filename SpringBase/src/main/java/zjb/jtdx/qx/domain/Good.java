package zjb.jtdx.qx.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
@Data
@Table(name = "good", schema = "zjb_db")
public class Good {
    @Id
    @Column(value = "good_id")
    public Integer id;

    @Column(value = "good_name")
    public String goodName;

    @Column(value = "good_price")
    public BigDecimal goodPrice;

    @Column(value = "good_num")
    public Integer goodNum;



}