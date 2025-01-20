<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <RouterView name="app_header"></RouterView>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <RouterView name="app_aside"></RouterView>
        </el-aside>
        <el-main>
          <RouterView name="app_main"></RouterView>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
