<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { useCounterStore } from '@/stores/counter';
import { storeToRefs } from 'pinia';
import GoodList from './goods/GoodList.vue';
import type { Good } from '@/types';
import { API_URL } from '@/config'; // 导入全局配置
import { useGoodStore } from '@/stores/goodStore';

const goodStore = useGoodStore();

const goods = computed(() => goodStore.goods);

const counter = useCounterStore();
const { count, doubleCount } = storeToRefs(counter);
const { increment } = counter;

onMounted(() => {
  goodStore.fetchAllGoods();
});
</script>

<template>
  <el-carousel :interval="4000" type="card" height="400px" class="custom-carousel">
    <el-carousel-item v-for="(good, index) in goods" :key="index">
      <div class="carousel-content">
        <img :src="`${API_URL}/${good.good_filepath}`" alt="product" class="carousel-image" />
        <div class="carousel-info">
          <h3>{{ good.good_name }}</h3>
          <p>{{ good.good_txt }}</p>
        </div>
      </div>
    </el-carousel-item>
  </el-carousel>
</template>

<style scoped>
.custom-carousel {
  background-color: #f0f0f0; /* 淡灰色背景 */
  border: 0px solid black; /* 黑色边框 */
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
  background-color: rgba(211, 211, 211, 0.8); /* 灰色透明背景 */
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

.carousel-info p {
  margin: 5px 0 0;
  font-size: 0.9em;
  color: #666;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>