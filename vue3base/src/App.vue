<template>
    <div class="common-layout">
      <el-container>
        <el-header>
            <RouterView name="app-header"></RouterView>
        </el-header>
        <el-container>
          <!-- <el-aside min-width="200px">
            <RouterView name="app-aside"></RouterView>
          </el-aside> -->
          <el-main>
            <RouterView name="app-main"></RouterView>
            <RouterView name="app-main1"></RouterView>
          </el-main>
        </el-container>
      </el-container>
    </div>
  </template>
<script setup lang="ts">
</script>