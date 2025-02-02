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
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { useClaStore } from '@/stores/store'
import type { Cla } from '@/type/type'
import {storeToRefs} from "pinia";
import {useTeachStore} from "@/stores/store";
const teacherStore = useTeachStore()
const {currentTeach} =storeToRefs(teacherStore)

const claStore = useClaStore()
const { addCla,setCurrentClassNull } = claStore
const {currentClass} =storeToRefs(claStore)

function add() {
  if (currentClass.value.cla_name === '') {
    ElMessage.error('请输入课程名称')
    return
  }
  currentClass.value.teach_id = currentTeach.value.teach_id
  addCla()
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
