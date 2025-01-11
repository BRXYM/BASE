package zjb.jtdx.qx.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
@Data
@Table(name = "sela", schema = "zjb_db")
public class Sela {
    @Id
    @Column(value = "sela_id")
    public Integer id;

    @Column(value = "cla_id")
    public Integer claId;

    @Column(value = "user_id")
    public Integer userId;



}