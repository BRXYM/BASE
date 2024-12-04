<template>
  <div class="common-layout" id="app">
    <el-container>
      <el-header>
        <RouterView name="app-header"></RouterView>
      </el-header>
      <el-main>
        <RouterView name="app-main-welcome"></RouterView>
        <RouterView name="app-main-list"></RouterView>
      </el-main>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import { RouterView } from 'vue-router';
</script>