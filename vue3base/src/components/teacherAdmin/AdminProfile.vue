<template>
  <div class="profile-container">
    <h1>个人中心</h1>
    <el-form :model="teachForm" status-icon label-width="120px" ref="profileForm">
      <el-form-item label="教练姓名" prop="teach_name">
        <el-input v-model="teachForm.teach_name" />
      </el-form-item>
      <el-form-item label="手机号" prop="teach_phone">
        <el-input v-model="teachForm.teach_phone" />
      </el-form-item>
      <el-form-item label="密码" prop="teach_pass">
        <el-input v-model="teachForm.teach_pass" type="password" />
      </el-form-item>
      <el-form-item label="头像">
        <el-upload
          class="avatar-uploader"
          :action="FILE_URL"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :limit="1"
        >
          <img v-if="teachForm.teach_img" :src="FILE_URL + '/' + teachForm.teach_img" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item label="简介" prop="teach_txt">
        <el-input v-model="teachForm.teach_txt" type="textarea" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateTeachProfile">保存更改</el-button>
        <el-button @click="clearTeach">清除</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted, watch } from 'vue'
import { storeToRefs } from 'pinia'
import { useTeachStore } from '@/stores/store'
import { ElMessage } from 'element-plus'
import { FILE_URL } from '@/config'
import { Plus } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'
import type { Teach } from '@/type/type'
import {useUserStore} from "@/stores/store";
const userStore = useUserStore()
const {islogin} = storeToRefs(userStore)

const teachStore = useTeachStore()
const { currentTeach } = storeToRefs(teachStore)
const { updateTeach } = teachStore
const router = useRouter()

const teachForm = ref<Teach>({
  teach_id: null,
  teach_img: null,
  teach_name: null,
  teach_phone: null,
  teach_pass: null,
  teach_txt: null
})

onMounted(() => {
  if (currentTeach.value) {
    teachForm.value = { ...currentTeach.value };
  }
})

function clearTeach() {
  teachForm.value.teach_id = null;
  teachForm.value.teach_img = null;
  teachForm.value.teach_name = null;
  teachForm.value.teach_phone = null;
  teachForm.value.teach_pass = null;
  teachForm.value.teach_txt = null;
}

const handleAvatarSuccess = (response: any, uploadFile: any) => {
  teachForm.value.teach_img = uploadFile.name
}

const beforeAvatarUpload = (rawFile: any) => {
  if (!['image/jpeg', 'image/png', 'image/gif'].includes(rawFile.type)) {
    ElMessage.error('文件格式不正确')
    return false
  }
  return true
}

const updateTeachProfile = async () => {
  try {
    await updateTeach(teachForm.value);
    router.push({ name: 'home' });
    islogin.value = 0;
    ElMessage.success('教练信息更新成功');
  } catch (error) {
    ElMessage.error('教练信息更新失败');
  }
}
</script>

<style scoped>
.profile-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #f9f9f9;
}

.avatar-uploader .avatar {
  width: 100px;
  height: 100px;
  display: block;
}

.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  text-align: center;
}
</style>
