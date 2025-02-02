<template>
  <el-menu  class="el-menu-demo" mode="horizontal" :ellipsis="false" router>
    <el-menu-item index="admin">教师管理</el-menu-item>

      <el-menu-item index="adminUploadGood">上传商品</el-menu-item>
      <el-menu-item index="adminUploadCla">上传课程</el-menu-item>
    <el-sub-menu index="nologin" v-if="islogin === 5">
      <template #title>
        <el-avatar :size="30" :src="FILE_URL + '/' + currentTeach?.user_img" fit="cover" />
        <span class="avatar-name-spacing"></span>
        {{ currentTeach?.teach_name || '未登录' }}
      </template>
      <el-menu-item index="adminProfile">个人中心</el-menu-item>
      <el-menu-item index="home" @click="logout,islogin = 0">退出登录</el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<script lang="ts" setup>
import { useRouter } from 'vue-router'
import { storeToRefs } from 'pinia'
import {useTeachStore} from "@/stores/store";
import {useUserStore} from "@/stores/store";
import {FILE_URL} from "@/config";
const userStore = useUserStore()
const {islogin} = storeToRefs(userStore)

const teachStore = useTeachStore()
const {currentTeach} = storeToRefs(teachStore)
const {logout} = teachStore




const router = useRouter()








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