<template>
    <el-form
      style="max-width: 100%"
      :model="userForm"
      status-icon
      label-width="auto"
    >
      <el-form-item label="用户ID" prop="userid">
        <el-input v-model="userForm.userid" autocomplete="off" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          v-model="userForm.password"
          type="password"
          autocomplete="off"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="login()">
          登录
        </el-button>
        <el-button @click="clear()">清除</el-button>
      </el-form-item>
    </el-form>
  </template>
  
  <script lang="ts" setup>
  import {  ref } from 'vue'
  

  const userForm = ref({
    userid: '',
    password: ''
  })

  function clear(){
    userForm.value = {
    userid: '',
    password: ''
  }
  }

  function login() {
    console.log(userForm)
  }

  </script>
  