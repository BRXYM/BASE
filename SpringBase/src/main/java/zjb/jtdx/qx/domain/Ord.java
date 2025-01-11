package zjb.jtdx.qx.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
@Data
@Table(name = "ord", schema = "zjb_db")
public class Ord {
    @Id
    @Column(value = "ord_id")
    public Integer id;

    @Column(value = "good_id")
    public Integer goodId;

    @Column(value = "user_id")
    public Integer userId;

    @Column(value = "ord_time")
    public Instant ordTime;

    @Column(value = "ord_num")
    public Integer ordNum;


}