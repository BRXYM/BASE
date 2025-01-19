package adm.control.all.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

public interface AdmMapper extends BaseMapper {
    @Select("""
            WITH table_columns AS (
                SELECT
                    t.TABLE_NAME,
                    JSON_ARRAYAGG(
                        JSON_OBJECT(
                            'column_name', c.COLUMN_NAME,
                            'data_type', c.DATA_TYPE
                        )
                    ) AS columns_info
                FROM
                    information_schema.tables t
                JOIN
                    information_schema.columns c
                ON
                    t.TABLE_NAME = c.TABLE_NAME
                WHERE
                    t.TABLE_SCHEMA = 'classroom_db'
                    AND c.TABLE_SCHEMA = 'classroom_db'
                GROUP BY
                    t.TABLE_NAME
            )
            SELECT
                JSON_OBJECTAGG(
                    CONCAT('table_comment:', TABLE_NAME),
                    columns_info
                ) AS json_result
            FROM
                table_columns;
          """)
    String getTableInfo();
}
