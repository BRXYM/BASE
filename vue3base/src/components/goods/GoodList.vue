<template>
  <div>
    <el-space
      fill
      wrap
      :fill-ratio="fillRatio"
      :direction="direction"
      style="width: 100%"
    >
      <el-card v-for="good in goods" :key="good.good_id" class="box-card">
        <template #header>
          <div class="card-header">
            <span>{{ good.good_name }}</span>
            <el-button type="primary" text @click="openDialog(good)">查看详情</el-button>
          </div>
        </template>
        <img :src="`${API_URL}/${good.good_filepath}`" class="product-image" />
        <div>数量: {{ good.good_num }}</div>
        <div>描述: {{ good.good_txt }}</div>
      </el-card>
    </el-space>

    <!-- 模态框 -->
    <el-dialog v-model="dialogVisible" title="商品详情" width="500" center>
      <span v-if="selectedGood">
        <p>名称: {{ selectedGood.good_name }}</p>
        <p>数量: {{ selectedGood.good_num }}</p>
        <p>描述: {{ selectedGood.good_txt }}</p>
        <img :src="`${API_URL}/${selectedGood.good_filepath}`" class="dialog-product-image" />
      </span>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">关闭</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { useGoodStore } from '@/stores/goodStore';
import type { Good } from '@/types';
import { API_URL } from '@/config';

const goodStore = useGoodStore();

const goods = computed(() => goodStore.goods);

onMounted(() => {
  goodStore.fetchAllGoods();
});

const direction = ref<'horizontal' | 'vertical'>('horizontal');
const fillRatio = ref(20);

// 模态框相关状态
const dialogVisible = ref(false);
const selectedGood = ref<Good | null>(null);

// 打开模态框的方法
const openDialog = (good: Good) => {
  selectedGood.value = good;
  dialogVisible.value = true;
};
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 250px; /* 调整卡片宽度 */
  transition: box-shadow 0.3s ease; /* 添加过渡效果 */
}

.box-card:hover {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.7); /* 鼠标悬浮时的灰色阴影 */
}

.product-image {
  width: 100%;
  height: 200px; /* 设置固定高度 */
  object-fit: cover; /* 按比例缩放并裁剪 */
}

/* 添加新的样式规则 */
.dialog-product-image {
  max-width: 100%; /* 确保图片宽度不超过容器 */
  max-height: 300px; /* 设置最大高度 */
  object-fit: contain; /* 保持图片比例 */
}
</style>