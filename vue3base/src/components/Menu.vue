<template>
  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" :ellipsis="false"
    @select="handleSelect">

    <el-menu-item index="home">
      克苏鲁跑团
    </el-menu-item>
    <el-menu-item index="mode">模组分类</el-menu-item>
    <el-menu-item index="uploadmode">上传模组</el-menu-item>
    <!--        <el-menu-item index="comment">查看评论</el-menu-item>-->
    <el-menu-item index="message">留言信箱</el-menu-item>
    <el-menu-item index="stow">我的收藏</el-menu-item>
    <el-menu-item index="login" v-if="islogin === 0" @click="islogin = 2">登录</el-menu-item>
    <el-sub-menu index="nologin" v-if="islogin === 1">
      <template #title>
        <el-avatar :size="30" :src="FILE_URL + '/' + currentUser?.Uimg" fit="cover" />
        <span class="avatar-name-spacing"></span>
        {{ currentUser?.Uname || '未登录' }}
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
  >
    <!-- 登录表单 -->
    <el-form style="max-width: 100%" :model="userForm" status-icon label-width="auto">
      <el-form-item label="手机号" prop="userphone">
        <el-input v-model="userForm.Uphone" autocomplete="off" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="userForm.Upass" type="password" autocomplete="off" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="login(userForm)">
          登录
        </el-button>
        <el-button @click="clearUser()">清除</el-button>
        <el-button @click="islogin = 0">关闭登录页面</el-button>
      </el-form-item>
    </el-form>
    <div>
      <el-button @click="islogin = 3, clearUser()">没有用户？点击这里</el-button>

      <el-drawer
        v-model="innerDrawer"
        title="注册"
        :append-to-body="true"
        size="40%"
        :close-on-click-modal="false"
        :close-on-press-escape="false"
        :show-close="false"
      >
        <el-form style="max-width: 100%" :model="userForm" status-icon label-width="auto" ref="registerForm">
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
          <el-form-item label="密码" prop="password">
            <el-input v-model="userForm.Upass" type="password" autocomplete="off" />
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
import type { User } from '@/types/type'
import { Plus, Delete, ZoomIn } from '@element-plus/icons-vue'

const Huser = useUserStore()
const { islogin, currentUser } = storeToRefs(Huser)
const { login, regist } = Huser
const drawer = ref(false)
const innerDrawer = ref(false)

const userForm = ref<User>({
  Uid: null,
  Uimg: null,
  Upass: null,
  Uqq: null,
  Umile: null,
  Uphone: null,
  Uname: null
})

const router = useRouter()

function clearUser() {
  userForm.value.Uid = null
  userForm.value.Upass = null
  userForm.value.Uqq = null
  userForm.value.Umile = null
  userForm.value.Uphone = null
  userForm.value.Uname = null
  userForm.value.Uimg = null
}

const activeIndex = ref('1')

function logout() {
  Huser.setUserNull()
}

watch(() => Huser.currentUser, (newValue) => {
  if (newValue?.Uid != null) {
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
      userForm.value.Uimg = null
      break
  }
})

watch(() => activeIndex.value, (newValue) => {
  if (islogin.value === 0) {
    router.push({ name: 'home' })
    ElMessage.error("请先登录")
  } else {
    switch (newValue) {
      case 'mode':
        router.push({ name: 'mode' })
        break
      case 'home':
        router.push({ name: 'home' })
        break
      case 'uploadmode':
        router.push({ name: 'uploadmode' })
        break
      case 'comment':
        router.push({ name: 'comment' })
        break
      case 'message':
        router.push({ name: 'message' })
        break
      case 'stow':
        router.push({ name: 'stow' })
        break
      case 'profile':
        router.push({ name: 'profile' })
        break
    }
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
  userForm.value.Uimg = uploadFile.name
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
</style>
