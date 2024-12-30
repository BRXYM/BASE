<template>
  <div>
    <el-space
        fill
        wrap
        :fill-ratio="fillRatio"
        :direction="direction"
        style="width: 100%"
    >
      <el-card v-for="message in messages" :key="message.MEid" class="box-card">
        <template #header>
          <div class="card-header">
            <span>{{ getUserName(message.Uid) }}</span>
            <el-button type="primary" text @click="openDialog(message)">查看详情</el-button>
          </div>
        </template>
        <div>留言时间: {{ message.time }}</div>
        <div class="description">内容: {{ message.txt }}</div>
      </el-card>
    </el-space>

    <!-- 模态框 -->
    <el-dialog v-model="dialogVisible" title="留言详情" width="500" center>
      <span v-if="selectedMessage">
        <p>留言人: {{ getUserName(selectedMessage.Uid) }}</p>
        <p>留言时间: {{ selectedMessage.time }}</p>
        <p class="description">内容: {{ selectedMessage.txt }}</p>
        <el-form :model="replyForm" label-width="80px">
          <el-form-item label="回复">
            <el-input v-model="replyForm.txt" type="textarea"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="sendReply">发送回复</el-button>
          </el-form-item>
        </el-form>
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
import { useMessageStore, useUserStore } from '@/stores/store';
import type { Message } from '@/types/type';
import { ElMessage } from 'element-plus';

const messageStore = useMessageStore();
const userStore = useUserStore();

const { messages } = storeToRefs(messageStore);
const { users, islogin, currentUser } = storeToRefs(userStore);

const direction = ref<'horizontal' | 'vertical'>('horizontal');
const fillRatio = ref(20);

// 模态框相关状态
const dialogVisible = ref(false);
const selectedMessage = ref<Message | null>(null);

// 回复表单
const replyForm = ref({
  txt: '',
  Uid: null as number | null,
  MEid: null as number | null,
});

// 打开模态框的方法
const openDialog = (message: Message) => {
  if (islogin.value === 0) {
    ElMessage.error("请先登录");
    return;
  }
  selectedMessage.value = message;
  replyForm.value.Uid = currentUser.value?.Uid || null;
  replyForm.value.Utoid = message.Uid;
  // replyForm.value.MEid = message.MEid;
  dialogVisible.value = true;
};

// 发送回复的方法
const sendReply = async () => {
  console.log(replyForm.value)
  if (!replyForm.value.txt) {
    ElMessage.error("回复内容不能为空");
    return;
  }

  try {
    await messageStore.addMessage({
      txt: replyForm.value.txt,
      Uid: currentUser.value.Uid,
      Utoid: replyForm.value.Utoid
      // MEid: replyForm.value.MEid,
    });
    ElMessage.success("回复成功");
    replyForm.value.txt = '';
    dialogVisible.value = false;
  } catch (error) {
    ElMessage.error("回复失败");
  }
};

onMounted(() => {
  messageStore.getMessagesByToUid(currentUser.value.Uid);
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