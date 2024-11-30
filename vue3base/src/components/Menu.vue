<template>
  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" :ellipsis="false"
           @select="handleSelect">

    <el-menu-item index="1">
      克苏鲁跑团
    </el-menu-item>
    <el-menu-item index="0">查看模组</el-menu-item>
    <el-menu-item index="4">上传模组</el-menu-item>
    <el-menu-item index="5">查看评论</el-menu-item>
    <el-menu-item index="6">留言信箱</el-menu-item>
    <el-menu-item index="7">我的收藏</el-menu-item>
    <el-menu-item index="8">历史留言</el-menu-item>
    <el-menu-item index="2" v-if="!isLogin" @click="drawer = true">登录</el-menu-item>
    <el-sub-menu index="3" v-if="isLogin">
      <template #title>{{ currentUser?.user_name }}</template>
      <el-menu-item index="3-1">个人中心</el-menu-item>
      <el-menu-item index="3-2" @click="logout">退出登录</el-menu-item>
    </el-sub-menu>
  </el-menu>
  <el-drawer v-model="drawer" title="用户登录" size="40%">
    <!-- 登录表单 -->
    <el-form style="max-width: 100%" :model="userForm" status-icon label-width="auto">
      <el-form-item label="用户ID" prop="userid">
        <el-input v-model="userForm.user_phone" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="userForm.user_psword" type="password" autocomplete="off"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleLogin">登录</el-button>
        <el-button @click="clearUser">清除</el-button>
      </el-form-item>
    </el-form>
    <div>
      <el-button @click="innerDrawer = true, clearUser">没有用户？点击这里</el-button>
      <el-drawer v-model="innerDrawer" title="注册" :append-to-body="true" size="37%">
        <!-- 注册表单 -->
        <el-form style="max-width: 100%" :model="userForm" status-icon label-width="auto">
          <el-form-item label="用户姓名" prop="user_name">
            <el-input v-model="userForm.user_name"/>
          </el-form-item>
          <el-form-item label="手机号" prop="user_phone">
            <el-input v-model="userForm.user_phone"/>
          </el-form-item>
          <el-form-item label="密码" prop="user_psword">
            <el-input v-model="userForm.user_psword" type="password" autocomplete="off"/>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="register">注册</el-button>
            <el-button @click="clearUser">清除</el-button>
          </el-form-item>
        </el-form>
      </el-drawer>
    </div>
  </el-drawer>
</template>

<script lang="ts" setup>
import { ref, watch, onBeforeMount } from 'vue'
import { useUserStore } from '@/stores/userStore';
import { storeToRefs } from 'pinia';
import { ElMessageBox } from 'element-plus'

const userStore = useUserStore()
const { isLogin, currentUser } = storeToRefs(userStore)
const { fetchAllUsers, login, logout } = userStore

const drawer = ref(false)
const innerDrawer = ref(false)


const userForm = ref({
  user_id: undefined as number | undefined,
  user_phone: '',
  user_psword: '',
  user_name: '',
  user_ads_id: 0
});

const clearUser = () => {
  userForm.value = {
    user_id: undefined as number | undefined,
    user_phone: '',
    user_psword: '',
    user_name: '',
    user_ads_id: 0
  }
}

const handleClose = (done: () => void) => {
  ElMessageBox.confirm('您还有未保存的数据，确定要离开吗？')
      .then(() => {
        done()
      })
      .catch(() => {
        // catch error
      })
}

const activeIndex = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}

const handleLogin = async () => {
  const success = await login(userForm.value);
  if (success) {
    drawer.value = false;
    clearUser();
  } else {
    ElMessageBox.alert('登录失败，请检查用户名和密码', '错误', {
      confirmButtonText: '确定',
      type: 'error'
    });
  }
};

const register = () => {
  // 实现注册逻辑
  innerDrawer.value = false;
  clearUser();
}
</script>

<style>
.el-menu--horizontal > .el-menu-item:nth-child(1) {
  margin-right: auto;
}
</style>
