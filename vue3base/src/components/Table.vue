<template>
  <div class="table-container">
    <el-button type="primary" size="large" @click="handleAdd" class="add-button">
      新增
    </el-button>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column
        v-for="column in columns"
        :key="column.column_name"
        :prop="column.column_name"
        :label="column.column_name"
        :width="column.width"
      />
      <el-table-column fixed="right" label="操作" min-width="120">
        <template #default="scope">
          <el-button link type="primary" size="small" @click="handleEdit(scope.row)">
            修改
          </el-button>
          <el-button link type="primary" size="small" @click="handleDelete(scope.row)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 修改对话框 -->
    <el-dialog v-model="dialogVisible" title="编辑记录">
      <el-form :model="editRow" label-width="100px">
        <el-form-item v-for="column in columns" :key="column.column_name" :label="column.column_name">
          <el-input v-model="editRow[column.column_name]"
            :disabled="primaryKeyColumns.includes(column.column_name)"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleSaveEdit">保存</el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 新增对话框 -->
    <el-dialog v-model="addDialogVisible" title="新增记录">
      <el-form :model="newRow" label-width="100px">
        <el-form-item v-for="column in columns" :key="column.column_name" :label="column.column_name">
          <el-input v-model="newRow[column.column_name]"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="addDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleAddRecord">保存</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted, watch, onUnmounted, nextTick, computed } from 'vue'
import { useRoute } from 'vue-router'
import { useAdmStore } from '@/stores/store' // 引入 useAdmStore
import { ElMessageBox, ElMessage, ElDialog, ElForm, ElFormItem, ElInput } from 'element-plus'

const route = useRoute()
const admStore = useAdmStore()

const tableName = ref(route.params.tableName as string)
const tableData = ref<any[]>([])
const columns = ref<any[]>([])
const primaryKeyColumns = ref<string[]>([])
const dialogVisible = ref(false)
const editRow = ref<any>({})
const addDialogVisible = ref(false) // 新增对话框的显示状态
const newRow = ref<any>({}) // 新增记录的数据

const fetchTableData = async () => {
  try {
    console.log('Fetching data for table:', tableName.value); // 添加日志
    await admStore.fetchTableData(tableName.value) // 使用 store 中的方法获取表数据
    console.log('Fetched data:', admStore.queryResult); // 添加日志
    tableData.value = admStore.queryResult as any[]
    // 根据返回的数据动态生成列
    if (tableData.value.length > 0) {
      const totalWidth = document.querySelector('.el-table')?.clientWidth || 0;
      const numColumns = Object.keys(tableData.value[0]).length;
      const columnWidth = Math.floor((totalWidth - 120) / numColumns); // 120 是操作列的最小宽度
      columns.value = Object.keys(tableData.value[0]).map(key => ({
        column_name: key,
        width: columnWidth
      }))
    }

    // 提取主键列信息
    if (admStore.tableInfo) {
      const tableComment = Object.keys(admStore.tableInfo).find(key => key.split(':')[1] === tableName.value);
      if (tableComment) {
        const columnInfos = admStore.tableInfo[tableComment];
        primaryKeyColumns.value = Array.from(new Set(columnInfos.filter(column => column.is_primary_key).map(column => column.column_name)));
        console.log('Primary Key Columns:', primaryKeyColumns.value); // 添加日志
      }
    }
  } catch (error) {
    console.error('获取表数据时出错:', error)
  }
}

watch(() => route.params.tableName, (newTableName) => {
  tableName.value = newTableName as string
  fetchTableData()
})

const handleDelete = (row: any) => {
  console.log('Delete row:', row);
  // 弹出确认对话框
  ElMessageBox.confirm('此操作将永久删除该记录, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    // 确保 primaryKeyColumns 已经被正确设置
    if (primaryKeyColumns.value.length === 0) {
      ElMessage.error('无法确定主键列，无法删除记录');
      return;
    }

    // 构建删除 SQL 语句
    const primaryKeyConditions = primaryKeyColumns.value.map(key => `${key} = '${row[key]}'`).join(' AND ');
    const sql = `DELETE FROM ${tableName.value} WHERE ${primaryKeyConditions}`;
    console.log(sql);
    admStore.executeDelete(sql).then(() => {
      // 重新获取数据
      fetchTableData();
    });
  }).catch(() => {
    ElMessage({
      type: 'info',
      message: '已取消删除'
    });
  });
}

const originalRow = ref<any>({}); // 新增原始行数据的引用

const handleEdit = (row: any) => {
  editRow.value = { ...row }; // 复制当前行数据到编辑行
  originalRow.value = { ...row }; // 保存原始行数据
  dialogVisible.value = true;
}

const handleSaveEdit = async () => {
  try {
    // 构建更新 SQL 语句
    const setClauses = columns.value
      .filter(column => !primaryKeyColumns.value.includes(column.column_name)) // 过滤掉主键列
      .map(column => `${column.column_name} = '${editRow.value[column.column_name]}'`)
      .join(', ');
    const primaryKeyConditions = primaryKeyColumns.value.map(key => `${key} = '${originalRow.value[key]}'`).join(' AND '); // 使用原始行数据
    const sql = `UPDATE ${tableName.value} SET ${setClauses} WHERE ${primaryKeyConditions}`;
    console.log(sql);
    await admStore.executeUpdate(sql);
    ElMessage.success('更新成功');
    dialogVisible.value = false;
    fetchTableData(); // 重新获取数据
  } catch (error) {
    console.error('更新记录时出错:', error);
    ElMessage.error('更新失败');
  }
}

// 新增记录的处理方法
const handleAdd = () => {
  newRow.value = {}; // 清空新增记录的数据
  addDialogVisible.value = true; // 显示新增对话框
}

const handleAddRecord = async () => {
  try {
    // 构建插入 SQL 语句
    const columnNames = columns.value.map(column => column.column_name).join(', ');
    const values = columns.value.map(column => `'${newRow.value[column.column_name]}'`).join(', ');
    const sql = `INSERT INTO ${tableName.value} (${columnNames}) VALUES (${values})`;
    console.log(sql);
    await admStore.executeInsert(sql);
    ElMessage.success('新增成功');
    addDialogVisible.value = false;
    fetchTableData(); // 重新获取数据
  } catch (error) {
    console.error('新增记录时出错:', error);
    ElMessage.error('新增失败');
  }
}
</script>

<style scoped>
.table-container {
  display: flex;
  justify-content: space-between;
}

.add-button {
  margin-right: 10px;
}
</style>
