<template>
  <el-card style="max-width: 100%">
    <!-- {{ good }}
    {{ `${API_URL}/${good.good_filepath}` }} -->
    <template #header>{{ good.good_name }}</template>
    <img :src="`${API_URL}/${good.good_filepath}`" style="width: 100%" />
    <div>数量: {{ good.good_num }}</div>
    <div>描述: {{ good.good_txt }}</div>
  </el-card>
</template>

<script setup lang="ts">
import { defineProps } from 'vue';
import type { Good } from '@/types/type';
import { API_URL } from '@/config'; // 导入全局配置

// 定义 props
const props = defineProps<{
  good: Good;
}>();
</script>