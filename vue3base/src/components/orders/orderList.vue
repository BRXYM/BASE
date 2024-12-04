#file: d:\GIT_STORE\BASE_LJF\vue3base\src\components\orders\OrderList.vue
<template>
  <div>
    <el-space
      fill
      wrap
      :fill-ratio="fillRatio"
      :direction="direction"
      style="width: 100%"
    >
      <el-card v-for="order in orders" :key="order.order_id" class="box-card">
        <template #header>
          <div class="card-header">
            <span>订单编号：{{ order.order_id }} </span>
            <el-button type="primary" text @click="openDialog(order)">查看详情</el-button>
          </div>
        </template>
        <div>订单商品 ID: {{ order.order_good_id }}</div>
        <!-- <div>订单用户 ID: {{ order.order_user_id }}</div> -->
        <div>订单时间: {{ order.order_time }}</div>
      </el-card>
    </el-space>

    <!-- 模态框 -->
    <el-dialog v-model="dialogVisible" title="订单详情" width="500" center>
      <span v-if="selectedOrder">
        <temorder :order="selectedOrder"></temorder>
        <!-- <p>订单 ID: {{ selectedOrder.order_id }}</p>
        <p>订单时间: {{ selectedOrder.order_time }}</p>
        <p>订单商品 ID: {{ selectedOrder.order_good_id }}</p>
        <p>订单用户 ID: {{ selectedOrder.order_user_id }}</p> -->
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
import {storeToRefs} from 'pinia';
import { useOrderStore } from '@/stores/orderStore';
import type { Order } from '@/types';
import { useUserStore } from '@/stores/userStore';
import temorder from '@/components/orders/order.vue';


const orderStore = useOrderStore();
const orders = computed(() => orderStore.orders);

const userStore = useUserStore();
const { currentUser } = storeToRefs(userStore);

const requestorder:Order =({
  order_user_id: currentUser.value?.user_id,
});

onMounted(() => {
  orderStore.fetchIdOrders(requestorder);
});

const direction = ref<'horizontal' | 'vertical'>('horizontal');
const fillRatio = ref(100);

// 模态框相关状态
const dialogVisible = ref(false);
const selectedOrder = ref<Order | null>(null);

// 打开模态框的方法
const openDialog = (order: Order) => {
  selectedOrder.value = order;
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
</style>