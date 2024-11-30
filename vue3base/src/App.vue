<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <!-- <RouterView name="header"></RouterView> -->
        <Menu></Menu>
      </el-header>
      <el-main>
        <RouterView name="main"></RouterView>
      </el-main>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import Menu from './components/Menu.vue';

</script>
