<template>
  <div>
    <h2>我的选课</h2>
    <el-space
      fill
      wrap
      :fill-ratio="fillRatio"
      :direction="direction"
      style="width: 100%"
    >
      <el-card v-for="sela in userSelas" :key="sela.sela_id" class="box-card">
        <template #header>
          <div class="card-header">
            <span>选课详情</span>
          </div>
        </template>
        <p><strong>课程名称:</strong> {{ getClassName(sela.cla_id) }}</p>
        <p><strong>教师:</strong> {{ getTeachName(getClaTeachId(sela.cla_id)) }}</p>
        <p><strong>联系方式:</strong> {{ getTeachPhone(getClaTeachId(sela.cla_id)) }}</p>
        <p><strong>课程时间:</strong> {{ getClaTime(sela.cla_id) }}</p>
        <p><strong>选课时间:</strong> {{ formatTime(sela.sela_time) }}</p>
      </el-card>
    </el-space>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { storeToRefs } from 'pinia';
import { useSelaStore, useClaStore, useTeachStore, useUserStore } from '@/stores/store';
import type { Sela, Cla, Teach, User } from '@/type/type';
import { ElMessage } from 'element-plus';
import dayjs from 'dayjs';

const selaStore = useSelaStore();
const claStore = useClaStore();
const teachStore = useTeachStore();
const userStore = useUserStore();

const { selas } = storeToRefs(selaStore);
const { classes } = storeToRefs(claStore);
const { teaches } = storeToRefs(teachStore);
const { currentUser } = storeToRefs(userStore);

const direction = ref<'horizontal' | 'vertical'>('horizontal');
const fillRatio = ref(100);

// 计算当前用户的选课记录
const userSelas = computed(() => {
  if (currentUser.value && currentUser.value.user_id) {
    return selas.value?.filter(sela => sela.user_id === currentUser.value.user_id) || [];
  }
  return [];
});

// 获取课程名称
const getClassName = (claId: number | undefined): string => {
  if (claId === undefined) return '未知课程';
  const cla = classes.value?.find(cla => cla.cla_id === claId);
  return cla?.cla_name || '未知课程';
};

// 获取课程教师ID
const getClaTeachId = (claId: number | undefined): number | undefined => {
  if (claId === undefined) return undefined;
  const cla = classes.value?.find(cla => cla.cla_id === claId);
  return cla?.teach_id;
};

// 获取教师名称
const getTeachName = (teachId: number | undefined): string => {
  if (teachId === undefined) return '未知教师';
  const teach = teaches.value?.find(teach => teach.teach_id === teachId);
  return teach?.teach_name || '未知教师';
};

// 获取教师手机号
const getTeachPhone = (teachId: number | undefined): string => {
  if (teachId === undefined) return '未知手机号';
  const teach = teaches.value?.find(teach => teach.teach_id === teachId);
  return teach?.teach_phone || '未知手机号';
};

// 获取课程时间
const getClaTime = (claId: number | undefined): string => {
  if (claId === undefined) return '未知时间';
  const cla = classes.value?.find(cla => cla.cla_id === claId);
  return formatTime(cla?.cla_time);
};

// 格式化时间
const formatTime = (time: string | undefined): string => {
  if (time === undefined) return '未知时间';
  return dayjs(time).format('YYYY-MM-DD HH:mm:ss');
};

onMounted(() => {
  Promise.all([
    selaStore.fetchSelas().catch((error) => {
      ElMessage.error("获取选课记录失败: " + error);
    }),
    claStore.fetchClasses().catch((error) => {
      ElMessage.error("获取课程失败: " + error);
    }),
    teachStore.fetchTeaches().catch((error) => {
      ElMessage.error("获取教师失败: " + error);
    }),
    userStore.fetchUsers().catch((error) => {
      ElMessage.error("获取用户失败: " + error);
    })
  ]).then(() => {
    if (!currentUser.value || !currentUser.value.user_id) {
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

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.product-image {
  width: 100%;
  height: 200px; /* 设置固定高度 */
  object-fit: cover; /* 按比例缩放并裁剪 */
}

/* 添加新的样式规则 */
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
