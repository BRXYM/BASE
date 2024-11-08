<template>
    <el-form
      style="max-width: 100%"
      :model="userForm"
      status-icon
      label-width="auto"
    >
      <el-form-item label="用户ID" prop="userid">
        <el-input v-model="userForm.uid" autocomplete="off" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          v-model="userForm.upass"
          type="password"
          autocomplete="off"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="login(userForm)">
          登录
        </el-button>
        <el-button @click="clear()">清除</el-button>
      </el-form-item>
    </el-form>
  </template>
  
  <script lang="ts" setup>
  import {  ref,onBeforeMount } from 'vue'
  import { useUserStore } from '@/stores/user'; 
  import { storeToRefs } from 'pinia'


  import type { User } from '@/stores/user'
  const user = useUserStore()

  const { login } = (user)
  

  const userForm = ref<User>({
    uid: null,
    upass: "",
    uqq: "",
    umile: "",
    uphone: "",
    uname: ""
  })

  function clear(){
    userForm.value.uid = null
    userForm.value.upass = ""
    userForm.value.uqq = ""
    userForm.value.umile = ""
    userForm.value.uphone = ""
    userForm.value.uname = ""
  }

  onBeforeMount(() => {
    clear()
  })

  </script>
  