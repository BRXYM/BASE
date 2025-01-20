// store.ts
import { defineStore } from "pinia";
import { executeDeleteSql, executeInsertSql, executeSelectSql, executeUpdateSql, getTableInfo } from "@/api/api";
import { ElMessage } from 'element-plus';

interface ColumnInfo {
    data_type: string;
    column_name: string;
    is_primary_key: boolean; // 添加 is_primary_key 字段
}

interface TableInfo {
    [table_comment: string]: Array<ColumnInfo>;
}

export const useAdmStore = defineStore("adm", {
    state: () => ({
        tableInfo: null as null | TableInfo,
        queryResult: null as null | object[],
        selectTable: null as null | string, // 添加 selectTable 状态
        isTableInfoLoaded: false, // 添加标志
    }),
    actions: {
        async fetchTableInfo() {
            try {
                const result = await getTableInfo();
                // 解析 list 字符串为对象
                this.tableInfo = JSON.parse(result.list as unknown as string);
                console.log("Fetched tableInfo:", this.tableInfo); // 添加日志

                // 设置默认选中的表名
                if (this.tableInfo) {
                    const tableKeys = Object.keys(this.tableInfo);
                    if (tableKeys.length > 0) {
                        const firstTableName = tableKeys[0].split(':')[1];
                        this.setSelectTable(firstTableName);
                    }
                }

                // 设置标志为加载完成
                this.isTableInfoLoaded = true;
            } catch (error) {
                console.error("获取表信息失败:", error);
                throw error;
            }
        },
        async executeDelete(sql: string) {
            try {
                const result = await executeDeleteSql(sql);
                ElMessage.success("删除成功");
                return result;
            } catch (error) {
                ElMessage.error("执行删除 SQL 失败");
                console.error("执行删除 SQL 失败:", error);
                throw error;
            }
        },
        async executeInsert(sql: string) {
            try {
                return await executeInsertSql(sql);
            } catch (error) {
                ElMessage.error("执行插入 SQL 失败");
                console.error("执行插入 SQL 失败:", error);
                throw error;
            }
        },
        async executeSelect(sql: string) {
            try {
                const result = await executeSelectSql(sql);
                if (Array.isArray(result.list)) {
                    this.queryResult = result.list;
                } else {
                    ElMessage.error("查询结果不是数组");
                    console.warn("查询结果不是数组:", result.list);
                }
                return result;
            } catch (error) {
                ElMessage.error("执行查询 SQL 失败");
                console.error("执行查询 SQL 失败:", error);
                throw error;
            }
        },
        async executeUpdate(sql: string) {
            try {
                const result = await executeUpdateSql(sql);
                ElMessage.success("更新成功");
                return result;
            } catch (error) {
                ElMessage.error("执行更新 SQL 失败");
                console.error("执行更新 SQL 失败:", error);
                throw error;
            }
        },
        // 示例方法，假设这里需要将 tableInfo 转换为字符串
        exampleMethod() {
            if (this.tableInfo) {
                const tableInfoString = JSON.stringify(this.tableInfo); // 使用类型断言将对象转换为字符串
                console.log("TableInfo as string:", tableInfoString);
                // 在这里使用 tableInfoString
            } else {
                console.warn("tableInfo is null or undefined");
            }
        },
        // 添加方法来更新 selectTable
        setSelectTable(tableName: string) {
            this.selectTable = tableName;
            console.log("Selected Table:", this.selectTable);
        },
        // 添加方法来获取表数据
        async fetchTableData(tableName: string) {
            try {
                const sql = `SELECT * FROM ${tableName}`;
                console.log(sql);
                const result = await executeSelectSql(sql);
                if (Array.isArray(result.list)) {
                    this.queryResult = result.list;
                } else {
                    ElMessage.error("查询结果不是数组");
                    console.warn("查询结果不是数组:", result.list);
                }
                return result;
            } catch (error) {
                console.error("获取表数据失败:", error);
                throw error;
            }
        },
        // 新增方法：执行插入操作
        async insertRecord(tableName: string, record: Record<string, any>) {
            try {
                // 构建插入 SQL 语句
                const columnNames = Object.keys(record).join(', ');
                const values = Object.values(record).map(value => `'${value}'`).join(', ');
                const sql = `INSERT INTO ${tableName} (${columnNames}) VALUES (${values})`;
                console.log('Insert SQL:', sql);

                // 执行插入操作
                const result = await executeInsertSql(sql);
                ElMessage.success("插入成功");
                return result;
            } catch (error) {
                ElMessage.error("执行插入 SQL 失败");
                console.error("执行插入 SQL 失败:", error);
                throw error;
            }
        }
    },
});
