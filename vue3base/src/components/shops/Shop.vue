<template>
  <div>
    <el-space
      fill
      wrap
      :fill-ratio="fillRatio"
      :direction="direction"
      style="width: 100%"
    >
      <el-card v-for="good in goodStore.goods" :key="good.good_id" class="box-card">
        <template #header>
          <div class="card-header">
            <span>{{ good.good_name }}</span>
            <el-button type="primary" text @click="openDialog(good)">查看详情</el-button>
          </div>
        </template>
        <img :src="`${FILE_URL}/${good.good_img}`" class="product-image" />
        <div>剩余数量: {{ good.good_num }}</div>
        <div class="description">{{ good.good_txt }}</div>
      </el-card>
    </el-space>

    <!-- 模态框 -->
    <el-dialog v-model="dialogVisible" title="商品详情" width="500" center>
      <span v-if="selectedGood">
        <p>名称: {{ selectedGood.good_name }}</p>
        <p>剩余数量: {{ selectedGood.good_num }}</p>
        <p class="description">{{ selectedGood.good_txt }}</p>
        <img :src="`${FILE_URL}/${selectedGood.good_img}`" class="dialog-product-image" />
        <br />
        <el-input-number v-model="num" :min="1" :max="selectedGood.good_num || 1" @change="handleChange" />
      </span>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">关闭</el-button>
          <el-button type="success" @click="buyGood(selectedGood)">购买</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed, watch } from 'vue';
import { storeToRefs } from 'pinia';
import { useGoodStore, useUserStore, useOrdStore } from '@/stores/store';
import type { Good, User, Ord } from '@/type/type';
import { FILE_URL } from '@/config';
import { ElMessage } from 'element-plus';

const goodStore = useGoodStore();
const userStore = useUserStore();
const ordStore = useOrdStore();

const { goods } = storeToRefs(goodStore);
const { users, islogin, currentUser } = storeToRefs(userStore);

const direction = ref<'horizontal' | 'vertical'>('horizontal');
const fillRatio = ref(20);

// 模态框相关状态
const dialogVisible = ref(false);
const selectedGood = ref<Good | null>(null);
const num = ref(1); // 初始化购买数量为1

// 打开模态框的方法
const openDialog = (good: Good) => {
  if (islogin.value === 0) {
    ElMessage.error("请先登录");
    return;
  }
  selectedGood.value = good;
  num.value = 1; // 重置购买数量为1
  dialogVisible.value = true;
};

// 购买商品的方法
const buyGood = (good: Good) => {
  if (islogin.value === 0) {
    ElMessage.error("请先登录");
    return;
  }

  // 检查商品库存
  if (good.good_num && good.good_num < num.value) {
    ElMessage.error("商品库存不足");
    return;
  }

  // 创建订单
  const newOrd: Ord = {
    ord_id: null,
    good_id: good.good_id,
    user_id: currentUser.value?.user_id,
    ord_time: new Date().toISOString(),
    ord_num: num.value, // 使用用户选择的数量
  };

  ordStore.addOrd(newOrd).then(() => {
    // 更新商品库存
    if (good.good_num !== undefined) {
      good.good_num -= num.value;
      goodStore.updateGood(good);
    }
    ElMessage.success("购买成功");
    dialogVisible.value = false; // 关闭模态框
    num.value = 1; // 重置购买数量为1
  }).catch((error) => {
    ElMessage.error("购买失败: " + error);
  });
};

// 处理数字输入框变化的方法
const handleChange = (value: number) => {
  console.log(value);
};

// 根据 user_id 获取用户名
const getUserName = (user_id: number | null): string => {
  if (user_id === undefined || user_id === null) return '未知用户';
  const user = users.value?.find(user => user.user_id === user_id);
  return user?.user_name || '未知用户';
};

// 在新标签页中打开文件 URL 的方法
const openFileInNewTab = (filePath: string | null) => {
  if (filePath === null) {
    ElMessage.error("文件路径无效");
    return;
  }
  const url = `${FILE_URL}/${filePath}`;
  window.open(url, '_blank');
};

onMounted(() => {
  goodStore.fetchGoods(); // 获取所有商品信息
  userStore.fetchUsers(); // 获取所有用户信息
});
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

.description {
  margin: 5px 0 0;
  font-size: 0.9em;
  color: #666;
  overflow: hidden; /* 隐藏溢出内容 */
  text-overflow: ellipsis; /* 显示省略号 */
  display: -webkit-box; /* 使用 WebKit 盒模型 */
  -webkit-line-clamp: 10; /* 设置显示的行数 */
  -webkit-box-orient: vertical; /* 设置文本方向为垂直 */
}
</style>
