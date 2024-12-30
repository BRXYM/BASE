<template>
  <div class="profile-container">
    <h1>个人中心</h1>
    <el-form :model="userForm" status-icon label-width="auto" ref="profileForm">
      <el-form-item label="用户姓名" prop="uname">
        <el-input v-model="userForm.Uname" />
      </el-form-item>
      <el-form-item label="QQ号" prop="uqq">
        <el-input v-model="userForm.Uqq" />
      </el-form-item>
      <el-form-item label="邮箱" prop="umile">
        <el-input v-model="userForm.Umile" />
      </el-form-item>
      <el-form-item label="手机号" prop="uphone">
        <el-input v-model="userForm.Uphone" />
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
          <img v-if="userForm.Uimg" :src="FILE_URL + '/' + userForm.Uimg" class="avatar" />
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

const userStore = useUserStore()
const { currentUser } = storeToRefs(userStore)
const { updateUser } = userStore

const userForm = ref({
  Uid: null,
  Uimg: null,
  Upass: null,
  Uqq: null,
  Umile: null,
  Uphone: null,
  Uname: null
})

onMounted(() => {
  if (currentUser.value) {
    userForm.value = { ...currentUser.value }
  }
})

function clearUser() {
  userForm.value.Uid = null
  userForm.value.Upass = null
  userForm.value.Uqq = null
  userForm.value.Umile = null
  userForm.value.Uphone = null
  userForm.value.Uname = null
  userForm.value.Uimg = null
}

const handleAvatarSuccess = (response, uploadFile) => {
  userForm.value.Uimg = uploadFile.name
}

const beforeAvatarUpload = (rawFile) => {
  if (!['image/jpeg', 'image/png', 'image/gif'].includes(rawFile.type)) {
    ElMessage.error('文件格式不正确')
    return false
  }
  return true
}

const updateUserProfile = async () => {
  try {
    await updateUser(userForm.value)
    ElMessage.success('用户信息更新成功')
  } catch (error) {
    ElMessage.error('用户信息更新失败')
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
