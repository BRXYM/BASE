<template>
  <div class="upload-cla-container">
    <h1>上传课程</h1>
    <el-form :model="currentClass" status-icon label-width="120px" ref="claForm">
      <el-form-item label="课程名称" prop="cla_name">
        <el-input v-model="currentClass.cla_name" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="add()">上传</el-button>
        <el-button @click="setCurrentClassNull()">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
  <!-- 添加课程表格 -->
  <el-table :data="classes as Cla[]">
    <el-table-column prop="cla_id" label="课程ID" />
    <el-table-column prop="cla_name" label="课程名称" />
    <el-table-column prop="teach_id" label="教师ID" />
    <el-table-column prop="cla_time" label="课程时间" />
    <el-table-column label="操作" width="200">
      <template #default="scope">
        <el-button size="small" @click="editClass(scope.row)">修改</el-button>
        <el-button size="small" type="danger" @click="deleteClass(scope.row.cla_id)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>

  <!-- 修改课程模态框 -->
  <el-dialog v-model="dialogVisible" title="修改课程">
    <el-form :model="currentClass" label-width="120px">
      <el-form-item label="课程名称" prop="cla_name">
        <el-input v-model="currentClass.cla_name" />
      </el-form-item>
    </el-form>
    <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="saveEdit">确定</el-button>
        </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox, ElDialog, ElForm, ElFormItem, ElInput, ElButton } from 'element-plus'
import { useClaStore } from '@/stores/store'
import type { Cla } from '@/type/type'
import { storeToRefs } from "pinia";
import { useTeachStore } from "@/stores/store";

const teacherStore = useTeachStore()
const { currentTeach } = storeToRefs(teacherStore)

const claStore = useClaStore()
const { addCla, setCurrentClassNull, getClasByTeacherId, deleteCla, updateCla } = claStore
const { currentClass, classes } = storeToRefs(claStore)

// 模态框显示控制
const dialogVisible = ref(false)

onMounted(() => {
  if (currentTeach.value && currentTeach.value.teach_id) {
    getClasByTeacherId(currentTeach.value.teach_id)
  } else {
    ElMessage.error('教师信息未找到')
  }
})

function add() {
  if (currentClass.value.cla_name === '') {
    ElMessage.error('请输入课程名称')
    return
  }
  currentClass.value.teach_id = currentTeach.value.teach_id
  addCla().then(() => {
    getClasByTeacherId(currentTeach.value.teach_id)
  })
}

function deleteClass(cla_id: number) {
  ElMessageBox.confirm('确认删除该课程？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    deleteCla(cla_id).then(() => {
      getClasByTeacherId(currentTeach.value.teach_id)
      ElMessage.success('删除成功')
    })
  }).catch(() => {
    ElMessage.info('已取消删除')
  })
}

function editClass(cla: Cla) {
  // 设置当前课程为要编辑的课程
  currentClass.value = { ...cla }
  dialogVisible.value = true
}

function saveEdit() {
  if (currentClass.value.cla_name === '') {
    ElMessage.error('请输入课程名称')
    return
  }
  updateCla(currentClass.value).then(() => {
    getClasByTeacherId(currentTeach.value.teach_id)
    dialogVisible.value = false
    ElMessage.success('修改成功')
  })
}
</script>

<style scoped>
.upload-cla-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #f9f9f9;
}
</style>
