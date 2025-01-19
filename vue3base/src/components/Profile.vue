<template>
  <div class="profile-container">
    <h1>个人中心</h1>
    <el-form :model="userForm" status-icon label-width="120px" ref="profileForm">
      <el-form-item label="用户姓名" prop="user_name">
        <el-input v-model="userForm.user_name" />
      </el-form-item>
      <el-form-item label="手机号" prop="user_phone">
        <el-input v-model="userForm.user_phone" />
      </el-form-item>
      <el-form-item label="密码" prop="user_pass">
        <el-input v-model="userForm.user_pass" type="password" />
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
          <img v-if="userForm.user_img" :src="FILE_URL + '/' + userForm.user_img" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateUserProfile">保存更改</el-button>
        <el-button @click="clearUser">清除</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted, watch } from 'vue'
import { storeToRefs } from 'pinia'
import { useUserStore } from '@/stores/store'
import { ElMessage } from 'element-plus'
import { FILE_URL } from '@/config'
import { Plus } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'
import type { User } from '@/type/type'

const userStore = useUserStore()
const { currentUser } = storeToRefs(userStore)
const { updateUser } = userStore
const { islogin } = storeToRefs(userStore)
const router = useRouter()

const userForm = ref<User>({
  user_id: null,
  user_img: null,
  user_name: null,
  user_phone: null,
  user_pass: null
})

onMounted(() => {
  if (currentUser.value) {
    userForm.value = { ...currentUser.value };
  }
})

function clearUser() {
  userForm.value.user_id = null;
  userForm.value.user_img = null;
  userForm.value.user_name = null;
  userForm.value.user_phone = null;
  userForm.value.user_pass = null;
}

const handleAvatarSuccess = (response:any, uploadFile:any) => {
  userForm.value.user_img = uploadFile.name
}

const beforeAvatarUpload = (rawFile:any) => {
  if (!['image/jpeg', 'image/png', 'image/gif'].includes(rawFile.type)) {
    ElMessage.error('文件格式不正确')
    return false
  }
  return true
}

const updateUserProfile = async () => {
  try {
    await updateUser(userForm.value);
    router.push({ name: 'home' });
    islogin.value = 0;
    ElMessage.success('用户信息更新成功，请重新登录');
  } catch (error) {
    ElMessage.error('用户信息更新失败');
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