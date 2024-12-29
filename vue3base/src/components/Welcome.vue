<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { useModeStore } from '@/stores/store';
import { storeToRefs } from 'pinia';
import type { Mode } from '@/types/type';
import { FILE_URL } from '@/config'; // 导入全局配置

const modeStore = useModeStore();
const { modes, currentMode } = storeToRefs(modeStore);

onMounted(() => {
  modeStore.getAllModes();
});
</script>

<template>
  <el-carousel :interval="4000" indicator-position="none" type="card" height="600px" class="custom-carousel">
    <el-carousel-item v-for="(mode, index) in modes" :key="index">
      <div class="carousel-content">
        <img :src="`${FILE_URL}/${mode.file}`" alt="product" class="carousel-image" />
        <div class="carousel-info">
          <h3>{{ mode.name }}</h3>
          <p class="carousel-description">{{ mode.txt }}</p>
        </div>
      </div>
    </el-carousel-item>
  </el-carousel>
</template>

<style scoped>
.custom-carousel {
  background-color: #f0f0f0bd; /* 淡灰色背景 */
  border: 0px solid black; /* 黑色边框 */
  margin-bottom: 10px;
}

.carousel-content {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100%;
}

.el-carousel__item img {
  width: 100%;
  height: 60%; /* 调整图片高度以适应内容 */
  object-fit: cover;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.7); /* 修正阴影效果 */
}

.carousel-info {
  padding: 10px;
  background-color: rgba(211, 211, 211, 0.5); /* 灰色透明背景 */
  height: 40%; /* 调整信息区域高度 */
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.carousel-info h3 {
  margin: 0;
  font-size: 1.2em;
  color: #333;
}

.carousel-description {
  margin: 5px 0 0;
  font-size: 0.9em;
  color: #666;
  overflow: hidden; /* 隐藏溢出内容 */
  text-overflow: ellipsis; /* 显示省略号 */
  display: -webkit-box; /* 使用 WebKit 盒模型 */
  -webkit-line-clamp: 10; /* 设置显示的行数 */
  -webkit-box-orient: vertical; /* 设置文本方向为垂直 */
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>