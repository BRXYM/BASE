package adm.control.all.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

public interface AdmMapper extends BaseMapper {

@Select("""
        WITH table_columns AS (
            SELECT
                t.TABLE_NAME,
                JSON_ARRAYAGG(
                    JSON_OBJECT(
                        'column_name', c.COLUMN_NAME,
                        'data_type', c.DATA_TYPE,
                        'is_primary_key', IF(kcu.COLUMN_NAME IS NOT NULL, 'true', 'false')
                    )
                ) AS columns_info
            FROM
                information_schema.tables t
            JOIN
                information_schema.columns c
            ON
                t.TABLE_NAME = c.TABLE_NAME
            LEFT JOIN
                information_schema.KEY_COLUMN_USAGE kcu
            ON
                t.TABLE_NAME = kcu.TABLE_NAME
                AND c.COLUMN_NAME = kcu.COLUMN_NAME
                AND kcu.CONSTRAINT_NAME = 'PRIMARY'
            WHERE
                t.TABLE_SCHEMA = "${localDB}"
                AND c.TABLE_SCHEMA = "${localDB}"
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
String getTableInfo(String localDB);


    @Options(statementType = StatementType.STATEMENT)
    @Insert("${sql}")
    int executeInsertSql(@Param("sql") String sql);

    @Options(statementType = StatementType.STATEMENT)
    @Delete("${sql}")
    int executeDeleteSql(@Param("sql") String sql);

    @Options(statementType = StatementType.STATEMENT)
    @Update("${sql}")
    int executeUpdateSql(@Param("sql") String sql);

    @Options(statementType = StatementType.STATEMENT)
    @Select("${sql}")
    List<Map<String, Object>> executeSelectSql(@Param("sql") String sql);
}
