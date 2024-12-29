<template>
  <div>
    <el-space
        fill
        wrap
        :fill-ratio="fillRatio"
        :direction="direction"
        style="width: 100%"
    >
      <el-card v-for="stow in stows" :key="stow.Sid" class="box-card">
        <template #header>
          <div class="card-header">
            <span>{{ getModeName(stow.MOid) }}</span>
            <el-button type="primary" text @click="openDialog(stow)">查看详情</el-button>
          </div>
        </template>
        <img :src="`${FILE_URL}/${getModeFile(stow.MOid)}`" class="product-image" />
        <div>收藏时间: {{ stow.time }}</div>
      </el-card>
    </el-space>

    <!-- 模态框 -->
    <el-dialog v-model="dialogVisible" title="模组详情" width="500" center>
      <span v-if="selectedStow">
        <p>名称: {{ getModeName(selectedStow.MOid) }}</p>
        <p>收藏时间: {{ selectedStow.time }}</p>
        <p class="description">{{ getModeDescription(selectedStow.MOid) }}</p>
        <p>上传用户: {{ getUserName(getModeUid(selectedStow.MOid)) }}</p> <!-- 根据 Uid 获取用户名 -->
        <img :src="`${FILE_URL}/${getModeFile(selectedStow.MOid)}`" class="dialog-product-image" /><br/>
        <el-button type="primary" @click="openFileInNewTab(getModeDownload(selectedStow.MOid))">查看文件</el-button>
        <el-button type="danger" @click="removeStow(selectedStow)">取消收藏</el-button>
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
import { ref, onMounted, computed, watch } from 'vue';
import { storeToRefs } from 'pinia';
import { useStowStore, useModeStore, useUserStore } from '@/stores/store';
import type { Stow, Mode } from '@/types/type';
import { FILE_URL } from '@/config';
import { ElMessage } from 'element-plus';

const stowStore = useStowStore();
const userStore = useUserStore();
const modeStore = useModeStore();

const { stows } = storeToRefs(stowStore);
const { modes } = storeToRefs(modeStore);
const { users, islogin, currentUser } = storeToRefs(userStore);

const direction = ref<'horizontal' | 'vertical'>('horizontal');
const fillRatio = ref(20);

// 模态框相关状态
const dialogVisible = ref(false);
const selectedStow = ref<Stow | null>(null);

// 打开模态框的方法
const openDialog = (stow: Stow) => {
  if (islogin.value === 0) {
    ElMessage.error("请先登录");
    return;
  }
  selectedStow.value = stow;
  dialogVisible.value = true;
};

onMounted(() => {
  if (currentUser.value && currentUser.value.Uid !== null) {
    stowStore.getStowsByUid(currentUser.value.Uid);
    modeStore.getAllModes();
    userStore.fetchUsers();
  } else {
    ElMessage.error("用户未登录");
  }
});

// 根据 MOid 获取模组名称
const getModeName = (MOid: number | null): string => {
  if (MOid === undefined || MOid === null) return '未知模组';
  const mode = modes.value?.find(mode => mode.MOid === MOid);
  return mode?.name || '未知模组';
};

// 根据 MOid 获取模组文件路径
const getModeFile = (MOid: number | null): string => {
  if (MOid === undefined || MOid === null) return '';
  const mode = modes.value?.find(mode => mode.MOid === MOid);
  return mode?.file || '';
};

// 根据 MOid 获取模组描述
const getModeDescription = (MOid: number | null): string => {
  if (MOid === undefined || MOid === null) return '';
  const mode = modes.value?.find(mode => mode.MOid === MOid);
  return mode?.txt || '';
};

// 根据 MOid 获取模组下载路径
const getModeDownload = (MOid: number | null): number | null => {
  if (MOid === undefined || MOid === null) return null;
  const mode = modes.value?.find(mode => mode.MOid === MOid);
  return mode?.download || null;
};

// 根据 MOid 获取模组上传用户 Uid
const getModeUid = (MOid: number | null): number | null => {
  if (MOid === undefined || MOid === null) return null;
  const mode = modes.value?.find(mode => mode.MOid === MOid);
  return mode?.Uid || null;
};

// 根据 Uid 获取用户名
const getUserName = (Uid: number | null): string => {
  if (Uid === undefined || Uid === null) return '未知用户';
  const user = users.value?.find(user => user.Uid === Uid);
  return user?.Uname || '未知用户';
};

// 在新标签页中打开文件 URL 的方法
const openFileInNewTab = (downloadPath: number | null) => {
  if (downloadPath === null) {
    ElMessage.error("文件路径无效");
    return;
  }
  const url = `${FILE_URL}/${downloadPath}`;
  window.open(url, '_blank');
};

// 取消收藏的方法
const removeStow = (stow: Stow) => {
  if (stow.Sid !== null) {
    stowStore.deleteStow(stow.Sid).then(() => {
      ElMessage.success("取消收藏成功");
      dialogVisible.value = false;
      // 从 stows 列表中移除被选中的收藏
      stows.value = stows.value?.filter(s => s.Sid !== stow.Sid) || [];
    }).catch(() => {
      ElMessage.error("取消收藏失败");
    });
  } else {
    ElMessage.error("收藏ID无效");
  }
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