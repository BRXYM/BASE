<template>
  <div>
    <el-radio-group v-model="typeradio" size="large">
      <el-radio-button v-for="type in mtypes" :key="type.Tid" :value="type.Tid?.toString()">{{ type.name }}</el-radio-button>
    </el-radio-group>
  </div>
  <br/>
  <div>
    <el-space
        fill
        wrap
        :fill-ratio="fillRatio"
        :direction="direction"
        style="width: 100%"
    >
      <el-card v-for="mode in modes" :key="mode.MOid" class="box-card">
        <template #header>
          <div class="card-header">
            <span>{{ mode.name }}</span>
            <el-button type="primary" text @click="openDialog(mode)">查看详情</el-button>
          </div>
        </template>
        <img :src="`${FILE_URL}/${mode.file}`" class="product-image" />
        <div>收藏量: {{ mode.sum }}</div>
        <div class="description">描述: {{ mode.txt }}</div>
      </el-card>
    </el-space>

    <!-- 模态框 -->
    <el-dialog v-model="dialogVisible" title="模组详情" width="800" center>
      <el-space :direction="direction" :fill-ratio="fillRatio" style="width: 100%">
        <div class="details-column">
          <span v-if="selectedMode">
            <p>名称: {{ selectedMode.name }}</p>
            <p>收藏量: {{ selectedMode.sum }}</p>
            <p class="description">描述: {{ selectedMode.txt }}</p>
            <p>上传用户: {{ getUserName(selectedMode.Uid) }}</p> <!-- 根据 Uid 获取用户名 -->
            <img :src="`${FILE_URL}/${selectedMode.file}`" class="dialog-product-image" /><br/>
            <el-button type="primary" @click="openFileInNewTab(selectedMode.download)">查看文件</el-button>
            <el-button type="success" @click="addStow(selectedMode)">收藏</el-button>
          </span>
        </div>
        <div class="comments-column">
          <h3>评论</h3>
          <el-card v-for="comment in comments" :key="comment.Cid" class="comment-card">
            <template #header>
              <div class="card-header">
                <span>{{ getUserName(comment.Uid) }}</span>
                <span>{{ comment.time }}</span>
              </div>
            </template>
            <div class="comment-content">{{ comment.txt }}</div>
          </el-card>
        </div>
      </el-space>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">关闭</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed, unref, watch } from 'vue';
import { storeToRefs } from 'pinia';
import { useModeStore, useMTypeStore, useUserStore, useStowStore, useCommentStore } from '@/stores/store';
import type { Mode, MType, Stow, Comment } from '@/types/type';
import { FILE_URL } from '@/config';
import { ElMessage } from 'element-plus';

const modeStore = useModeStore();
const userStore = useUserStore();
const mTypeStore = useMTypeStore();
const stowStore = useStowStore();
const commentStore = useCommentStore();

const { mtypes } = storeToRefs(mTypeStore);

const modes = computed(() => modeStore.modes);
const { users, islogin, currentUser } = storeToRefs(userStore);
const { comments } = storeToRefs(commentStore);

// 初始化 typeradio 为第一个类型的 Tid 或空字符串
const typeradio = ref<string | undefined>('');

onMounted(() => {
  userStore.fetchUsers();
  mTypeStore.getAllMTypes().then(() => {
    const types = unref(mtypes);
    if (types && types.length > 0) {
      typeradio.value = types[0]?.Tid?.toString(); // 确保 typeradio 设置为 Tid 的字符串形式
    }
  });
});

const direction = ref<'horizontal' | 'vertical'>('horizontal');
const fillRatio = ref(20);

// 模态框相关状态
const dialogVisible = ref(false);
const selectedMode = ref<Mode | null>(null);

// 打开模态框的方法
const openDialog = async (mode: Mode) => {
  if (islogin.value === 0) {
    ElMessage.error("请先登录");
    return;
  }
  selectedMode.value = mode;
  dialogVisible.value = true;
  // 获取评论
  await commentStore.getCommentsByMid(mode.MOid as number);
};

watch(typeradio, (newValue) => {
  if (newValue === undefined) return;

  const tid = parseInt(newValue, 10);
  if (!isNaN(tid)) {
    modeStore.getModesByTid(tid);
  }
});

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

// 添加收藏的方法
const addStow = (mode: Mode) => {
  if (islogin.value === 0) {
    ElMessage.error("请先登录");
    return;
  }

  if (currentUser.value && currentUser.value.Uid !== null) {
    const stow: Stow = {
      Sid: null,
      Uid: currentUser.value.Uid,
      MOid: mode.MOid,
      time: null,
    };

    stowStore.addStow(stow).then(() => {
      ElMessage.success("收藏成功");
      dialogVisible.value = false;
    }).catch(() => {
      ElMessage.error("收藏失败");
    });
  } else {
    ElMessage.error("用户未登录");
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

.details-column {
  width: 50%;
  padding-right: 20px;
}

.comments-column {
  width: 50%;
}

.comment-card {
  margin-bottom: 10px;
}

.comment-content {
  margin-top: 10px;
}
</style>
