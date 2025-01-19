<template>
  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" :ellipsis="false"
    @select="handleSelect">

    <el-menu-item index="home">
      青训
    </el-menu-item>
    <el-menu-item index="shop">商店</el-menu-item>
    <el-menu-item index="order">商品订单</el-menu-item>
    <el-menu-item index="sela">全部选课</el-menu-item>
    <el-menu-item index="class">全部课程</el-menu-item>
    <el-menu-item index="teacher">全部教练</el-menu-item>
    <el-menu-item index="login" v-if="islogin === 0" @click="islogin = 2">登录</el-menu-item>
    <el-sub-menu index="nologin" v-if="islogin === 1">
      <template #title>
        <el-avatar :size="30" :src="FILE_URL + '/' + currentUser?.user_img" fit="cover" />
        <span class="avatar-name-spacing"></span>
        {{ currentUser?.user_name || '未登录' }}
      </template>
      <el-menu-item index="profile">个人中心</el-menu-item>
      <el-menu-item index="3-2" @click="logout">退出登录</el-menu-item>
    </el-sub-menu>
  </el-menu>
  <el-drawer
    v-model="drawer"
    title="用户登录"
    size="40%"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    :show-close="false"
    direction="ttb"
  >
    <!-- 登录表单 -->
    <el-form style="max-width: 100%" :model="userForm" status-icon label-width="auto">
      <el-form-item label="手机号" prop="userphone">
        <el-input v-model="userForm.user_phone" autocomplete="off" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="userForm.user_pass" type="password" autocomplete="off" />
      </el-form-item>
      <el-form-item>
        
        <el-button type="primary" @click="login(userForm)">
          登录
        </el-button>
        <el-button @click="clearUser()">清除</el-button>
        <el-button @click="islogin = 0">关闭登录页面</el-button>
        <br>
        <el-button @click="islogin = 3, clearUser()" style="margin-top: 10px;">没有用户？点击这里</el-button>
        
      </el-form-item>
    </el-form>
    <div>
      

      <el-drawer
        v-model="innerDrawer"
        title="注册"
        :append-to-body="true"
        size="40%"
        :close-on-click-modal="false"
        :close-on-press-escape="false"
        :show-close="false"
        direction="ttb"
      >
        <el-form style="max-width: 100%" :model="userForm" status-icon label-width="auto" ref="registerForm">
          <el-form-item label="用户姓名" prop="uname">
            <el-input v-model="userForm.user_name" />
          </el-form-item>
          <el-form-item label="手机号" prop="uphone">
            <el-input v-model="userForm.user_phone" />
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="userForm.user_pass" type="password" autocomplete="off" />
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
            <el-button type="primary" @click="regist(userForm)">
              注册
            </el-button>
            <el-button @click="clearUser()">清除</el-button>
            <el-button @click="islogin = 2">返回登录</el-button>
          </el-form-item>
        </el-form>
      </el-drawer>
    </div>
  </el-drawer>
</template>

<script lang="ts" setup>
import { ref, watch, onBeforeMount } from 'vue'
import { useRouter } from 'vue-router'
import { storeToRefs } from 'pinia'
import { ElMessageBox, type UploadFile } from 'element-plus'
import { useUserStore } from '@/stores/store'
import { ElMessage } from 'element-plus'
import { FILE_URL, UPLOAD_URL } from '@/config'
import type { User } from '@/type/type'
import { Plus, Delete, ZoomIn } from '@element-plus/icons-vue'
import type { UploadProps } from 'element-plus'

const userStore = useUserStore()
const { islogin, currentUser } = storeToRefs(userStore)
const { login, regist, setUserNull } = userStore

const drawer = ref(false)
const innerDrawer = ref(false)

const userForm = ref<User>({
  user_id: null,
  user_img: null,
  user_name: null,
  user_phone: null,
  user_pass: null
})

const router = useRouter()

function clearUser() {
  userForm.value.user_id = null
  userForm.value.user_img = null
  userForm.value.user_name = null
  userForm.value.user_phone = null
  userForm.value.user_pass = null
}

const activeIndex = ref('1')

function logout() {
  setUserNull()
}

watch(() => userStore.currentUser, (newValue) => {
  if (newValue?.user_id != null) {
    islogin.value = 1
    drawer.value = false
  }
})

watch(() => islogin.value, (newValue) => {
  switch (newValue) {
    case 0:
      drawer.value = false
      innerDrawer.value = false
      break
    case 1:
      drawer.value = false
      innerDrawer.value = false
      break
    case 2:
      innerDrawer.value = false
      drawer.value = true
      break
    case 3:
      drawer.value = false
      innerDrawer.value = true
      userForm.value.user_img = null
      break
  }
})

watch(() => activeIndex.value, (newValue) => {
  if (islogin.value === 0) {
    router.push({ name: 'home' })
    ElMessage.error("请先登录")
  } else if (islogin.value === 1) {
    router.push({name:newValue})
  }
})

function handleSelect(index: string) {
  if (index !== '0') {
    activeIndex.value = index
  }
}

const handleAvatarSuccess: UploadProps['onSuccess'] = (
  response,
  uploadFile
) => {
  userForm.value.user_img = uploadFile.name
}

const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile) => {
  if (!['image/jpeg', 'image/png', 'image/gif'].includes(rawFile.type)) {
    ElMessage.error('文件格式不正确')
    return false
  }
  return true
}
</script>

<style>
.el-menu--horizontal>.el-menu-item:nth-child(1) {
  margin-right: auto;
}

.avatar-name-spacing {
  margin-left: 8px;
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

.el-drawer__body {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
.el-form {
  max-width: 100%;
  width: 300px; 
}
</style>