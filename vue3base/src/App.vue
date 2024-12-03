<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <RouterView name="app-header"></RouterView>
      </el-header>
      <el-main>
        <RouterView name="app-main"></RouterView>
      </el-main>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import { RouterView } from 'vue-router';
</script>