package zjb.jtdx.qx.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Data
@Table(name = "cla", schema = "zjb_db")
public class Cla {
    @Id
    @Column(value = "cla_id")
    public Integer id;

    @Column(value = "cla_name")
    public String claName;

    @Column(value = "teach_id")
    public Integer teachId;

    @Column(value = "cla_time")
    public Instant claTime;

}