package zjb.jtdx.qx.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
@Data
@Table(name = "user", schema = "zjb_db")
public class User {
    @TableId(value = "user_id",type = IdType.AUTO)
    public Integer id;

    @TableField(value = "user_name")
    public String userName;

    @TableField(value = "user_phone")
    public String userPhone;

    @TableField(value = "user_pass")
    public String userPass;


}