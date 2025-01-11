package zjb.jtdx.qx.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
@Data
@Table(name = "teach", schema = "zjb_db")
public class Teach {
    @Id
    @Column(value = "teach_id")
    public Integer id;

    @Column(value = "teach_name")
    public String teachName;

    @Column(value = "teach_phone")
    public String teachPhone;

    @Column(value = "teach_pass")
    public String teachPass;


}