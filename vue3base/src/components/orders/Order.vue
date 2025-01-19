<template>
  <div>
    <h2>所有订单</h2>
    <el-space
      fill
      wrap
      :fill-ratio="fillRatio"
      :direction="direction"
      style="width: 100%"
    >
      <el-card v-for="ord in formattedOrds" :key="ord.ord_id" class="box-card">
        <template #header>
          <div class="card-header">
            <span>订单详情</span>
          </div>
        </template>
        <p>商品名称: {{ getGoodName(ord.good_id) }}</p>
        <p>商品图片: <img :src="`${FILE_URL}/${getGoodImage(ord.good_id)}`" alt="商品图片" class="product-image" /></p>
        <p>商品描述: {{ getGoodDescription(ord.good_id) }}</p>
        <p>购买时间: {{ ord.ord_time }}</p>
        <p>购买数量: {{ ord.ord_num }}</p>
        <p>商品价格: {{ getGoodPrice(ord.good_id) }} 元</p>
        <p>订单总价: {{ ord.ord_num * getGoodPrice(ord.good_id) }} 元</p>
      </el-card>
    </el-space>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { storeToRefs } from 'pinia';
import { useOrdStore, useUserStore, useGoodStore } from '@/stores/store';
import type { Ord, Good } from '@/type/type';
import { ElMessage } from 'element-plus';
import dayjs from 'dayjs';
import { FILE_URL } from "@/config";

const ordStore = useOrdStore();
const userStore = useUserStore();
const goodStore = useGoodStore();

const { ords } = storeToRefs(ordStore);
const { currentUser } = storeToRefs(userStore);
const { goods } = storeToRefs(goodStore);

const direction = ref<'horizontal' | 'vertical'>('horizontal');
const fillRatio = ref(20);

// 格式化订单时间
const formattedOrds = computed(() => {
  return ords.value?.map(ord => ({
    ...ord,
    ord_time: ord.ord_time ? dayjs(ord.ord_time).format('YYYY-MM-DD HH:mm:ss') : '未知时间'
  })) || [];
});

// 获取商品名称
const getGoodName = (goodId: number | undefined): string => {
  if (goodId === undefined) return '未知商品';
  const good = goods.value?.find(good => good.good_id === goodId);
  return good?.good_name || '未知商品';
};

// 获取商品图片
const getGoodImage = (goodId: number | undefined): string => {
  if (goodId === undefined) return '';
  const good = goods.value?.find(good => good.good_id === goodId);
  return good?.good_img || '';
};

// 获取商品描述
const getGoodDescription = (goodId: number | undefined): string => {
  if (goodId === undefined) return '未知描述';
  const good = goods.value?.find(good => good.good_id === goodId);
  return good?.good_txt || '未知描述';
};

// 获取商品价格
const getGoodPrice = (goodId: number | undefined): number => {
  if (goodId === undefined) return 0;
  const good = goods.value?.find(good => good.good_id === goodId);
  return good?.good_price || 0;
};

onMounted(() => {
  Promise.all([
    ordStore.fetchOrds().catch((error) => {
      ElMessage.error("获取订单失败: " + error);
    }),
    goodStore.fetchGoods().catch((error) => {
      ElMessage.error("获取商品失败: " + error);
    }),
    userStore.fetchUsers().catch((error) => {
      ElMessage.error("获取用户失败: " + error);
    })
  ]).then(() => {
    if (currentUser.value && currentUser.value.user_id) {
      ordStore.getOrdByUserId(currentUser.value.user_id).catch((error) => {
        ElMessage.error("获取订单失败: " + error);
      });
    } else {
      ElMessage.error("用户未登录或未找到用户ID");
    }
  });
});
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.box-card {
  width: 250px; /* 调整卡片宽度 */
  transition: box-shadow 0.3s ease; /* 添加过渡效果 */
}

.box-card:hover {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.7); /* 鼠标悬浮时的灰色阴影 */
}

.product-image {
  max-width: 100px; /* 设置图片最大宽度 */
  max-height: 100px; /* 设置图片最大高度 */
  object-fit: cover; /* 按比例缩放并裁剪 */
}

/* 添加一些样式以美化表格 */
h2 {
  margin-bottom: 20px;
}
</style>
