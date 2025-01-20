<!-- MenuLeft.vue -->
<template>
  <el-row class="tac">
    <el-col :span="20">
      <el-menu
          active-text-color="#ffd04b"
          background-color="#545c64"
          class="el-menu-vertical-demo"
          :default-active="defaultActiveIndex"
          text-color="#fff"
          @open="handleOpen"
          @close="handleClose"
      >
        <el-menu-item v-for="(table, index) in tableList" :key="index" :index="String(index + 1)" @click="handleTableClick(table.tableName)">
          <el-icon><location /></el-icon>
          <span>{{ table.tableName }}</span>
        </el-menu-item>
      </el-menu>
    </el-col>
  </el-row>
</template>

<script lang="ts" setup>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
import { ref, onMounted, computed } from 'vue'
import { useAdmStore } from '@/stores/store' // 引入 useAdmStore
import { useRouter } from 'vue-router'
const router = useRouter()

const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}

const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}

// 明确 tableList 的类型为 { tableName: string }[]
const tableList = ref<{ tableName: string }[]>([])

const admStore = useAdmStore()

// 计算默认激活的索引
const defaultActiveIndex = computed(() => {
  const index = tableList.value.findIndex(table => table.tableName === admStore.selectTable);
  return index >= 0 ? String(index + 1) : '1';
});

onMounted(async () => {
  try {
    await admStore.fetchTableInfo() // 使用 store 中的方法获取表信息
    if (admStore.tableInfo) {
      // 将 store 中的表信息转换为适合渲染的数据结构
      tableList.value = Object.keys(admStore.tableInfo).map(tableName => ({
        tableName: tableName.split(':')[1] // 提取表名
      }));

      // 自动导航到第一个表的详细页面
      if (admStore.selectTable && tableList.value.length >0) {
        router.push({ name: 'table', params: { tableName: admStore.selectTable } });
      }
    }
  } catch (error) {
    console.error('获取表信息时出错:', error)
  }
})

const handleTableClick = (tableName: string) => {
  console.log('Selected Table Name:', tableName); // 添加日志
  admStore.setSelectTable(tableName); // 更新 selectTable 状态
  router.push({ name: 'table', params: { tableName } }) // 确保传递的参数正确
}
</script>
