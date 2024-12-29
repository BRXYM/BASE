<template>
  <div>
    <el-space
        fill
        wrap
        :fill-ratio="fillRatio"
        :direction="direction"
        style="width: 100%"
    >
      <el-card v-for="comment in comments" :key="comment.Cid" class="box-card">
        <template #header>
          <div class="card-header">
            <span>{{ getUserName(comment.Uid) }}</span>
            <el-button type="primary" text @click="openDialog(comment)">查看详情</el-button>
          </div>
        </template>
        <div>评论时间: {{ comment.time }}</div>
        <div class="description">内容: {{ comment.txt }}</div>
      </el-card>
    </el-space>

    <!-- 模态框 -->
    <el-dialog v-model="dialogVisible" title="评论详情" width="500" center>
      <span v-if="selectedComment">
        <p>评论人: {{ getUserName(selectedComment.Uid) }}</p>
        <p>评论时间: {{ selectedComment.time }}</p>
        <p class="description">内容: {{ selectedComment.txt }}</p>
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
import { useCommentStore, useUserStore } from '@/stores/store';
import type { Comment } from '@/types/type';
import { ElMessage } from 'element-plus';

const commentStore = useCommentStore();
const userStore = useUserStore();

const { comments } = storeToRefs(commentStore);
const { users, islogin } = storeToRefs(userStore);

const direction = ref<'horizontal' | 'vertical'>('horizontal');
const fillRatio = ref(20);

// 模态框相关状态
const dialogVisible = ref(false);
const selectedComment = ref<Comment | null>(null);

// 打开模态框的方法
const openDialog = (comment: Comment) => {
  if(islogin.value === 0){
    ElMessage.error("请先登录");
    return;
  }
  selectedComment.value = comment;
  dialogVisible.value = true;
};

onMounted(() => {
  commentStore.getAllComments();
  userStore.fetchUsers();
});

// 根据 Uid 获取用户名
const getUserName = (Uid: number | null): string => {
  if (Uid === undefined || Uid === null) return '未知用户';
  const user = users.value?.find(user => user.Uid === Uid);
  return user?.Uname || '未知用户';
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