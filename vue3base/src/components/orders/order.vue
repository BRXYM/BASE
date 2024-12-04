<template>
  <el-card style="max-width: 100%">
    <template #header>{{ order.order_id }} - {{ order.order_time }}</template>
    <div>订单商品 ID: {{ order.order_good_id }}</div>
    <div>订单用户 ID: {{ order.order_user_id }}</div>
    <div>商品 ID: {{thisgood?.good_id }}</div>
    <!-- <div>商品名称 ID: {{ thisgood?.good_sell_id }}</div> -->
    <div>商品名称: {{ thisgood?.good_name }}</div>
    <img :src="`${API_URL}/${thisgood?.good_filepath}`" class="dialog-product-image" />
    {{ thismess }}
  </el-card>
</template>

<script setup lang="ts">
import { defineProps, onMounted,ref } from 'vue';
import type { Order,Good,Mess } from '@/types';
import { useGoodStore } from '@/stores/goodStore';
import { API_URL } from '@/config';
import { fetchMessesByOrderId } from '@/api';

const { fetchIdGoods } = useGoodStore();

// 定义 props
const props = defineProps<{
  order: Order;
}>();

const thisgood = ref<Good>();
    const thismess = ref<Mess[] | null>(null);
onMounted(async()=>{
    thisgood.value = await fetchIdGoods({'good_id': props.order.order_good_id})
    const {list}:any = (await fetchMessesByOrderId(props.order.order_id)).data;
    thismess.value = list;
})
</script>

<style scoped>
/* 添加新的样式规则 */
.dialog-product-image {
  max-width: 100%; /* 确保图片宽度不超过容器 */
  max-height: 300px; /* 设置最大高度 */
  object-fit: contain; /* 保持图片比例 */
}
</style>