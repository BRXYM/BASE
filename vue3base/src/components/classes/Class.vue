<template>
  <div>
    <h2>所有课程</h2>
    <el-space
        fill
        wrap
        :fill-ratio="fillRatio"
        :direction="direction"
        style="width: 100%"
    >
      <el-card v-for="cla in paginatedClasses" :key="cla.cla_id" class="box-card">
        <template #header>
          <div class="card-header">
            <span>{{ cla.cla_name }}</span>
          </div>
        </template>
        <p>教师:<img :src="`${FILE_URL}/${getTeachImage(cla.teach_id)}`" alt="教师头像" class="teacher-image"/>
          {{ getTeachName(cla.teach_id) }}</p>
        <p>联系方式: {{ getTeachPhone(cla.teach_id) }}</p>
        <p>课程时间: {{ cla.cla_time }}</p>
        <el-button type="primary" @click="handleViewDetails(cla.cla_id)">详情</el-button>
      </el-card>
    </el-space>

    <!-- 分页组件 -->
    <el-pagination
      v-model:current-page="currentPage"
      :page-size="pageSize"
      :total="claStore.classes.length"
      layout="prev, pager, next"
      @current-change="handlePageChange"
      style="margin-top: 20px; text-align: center;"
    />

    <!-- 课程详情模态框 -->
    <el-dialog v-model="dialogVisible" title="课程详情" width="50%">
      <div v-if="selectedClass">
        <p><strong>课程名称:</strong> {{ selectedClass.cla_name }}</p>
        <p><strong>教师:</strong> <img :src="`${FILE_URL}/${getTeachImage(selectedClass.teach_id)}`" alt="教师头像"
                                       class="teacher-image"/>{{ getTeachName(selectedClass.teach_id) }}</p>
        <p><strong>联系方式:</strong> {{ getTeachPhone(selectedClass.teach_id) }}</p>
        <p><strong>课程时间:</strong> {{ selectedClass.cla_time }}</p>
      </div>
      <template #footer>
        <span class="dialog-footer">
          <el-button type="success" @click="handleEnroll(selectedClass.cla_id)">选课</el-button>
          <el-button @click="dialogVisible = false">关闭</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { storeToRefs } from 'pinia';
import { useClaStore, useTeachStore, useUserStore, useSelaStore } from '@/stores/store';
import type { Cla, Teach, User } from '@/type/type';
import { ElMessage } from 'element-plus';
import { useRouter } from 'vue-router';
import { FILE_URL } from "@/config";
import dayjs from 'dayjs';

const claStore = useClaStore();
const teachStore = useTeachStore();
const userStore = useUserStore();
const selaStore = useSelaStore();
const router = useRouter();

const { classes } = storeToRefs(claStore);
const { teaches } = storeToRefs(teachStore);
const { currentUser } = storeToRefs(userStore);

const direction = ref<'horizontal' | 'vertical'>('horizontal');
const fillRatio = ref(20);

const dialogVisible = ref(false);
const selectedClass = ref<Cla | null>(null);

const currentPage = ref(1);
const pageSize = 12; // 每页显示的课程数量

// 计算分页后的课程
const paginatedClasses = computed(() => {
  const start = (currentPage.value - 1) * pageSize;
  const end = start + pageSize;
  return classes.value ? classes.value.slice(start, end) : [];
});

// 获取教师名称
const getTeachName = (teachId: number | undefined): string => {
  if (teachId === undefined) return '未知教师';
  const teach = teaches.value?.find(teach => teach.teach_id === teachId);
  return teach?.teach_name || '未知教师';
};

// 获取教师头像
const getTeachImage = (teachId: number | undefined): string => {
  if (teachId === undefined) return '';
  const teach = teaches.value?.find(teach => teach.teach_id === teachId);
  return teach?.teach_img || '';
};

// 获取教师手机号
const getTeachPhone = (teachId: number | undefined): string => {
  if (teachId === undefined) return '未知手机号';
  const teach = teaches.value?.find(teach => teach.teach_id === teachId);
  return teach?.teach_phone || '未知手机号';
};

// 处理查看详情按钮点击事件
const handleViewDetails = (claId: number) => {
  const cla = classes.value?.find(c => c.cla_id === claId);
  if (cla) {
    selectedClass.value = cla;
    dialogVisible.value = true;
  }
};

// 选课方法
const handleEnroll = (claId: number) => {
  if (!currentUser.value) {
    ElMessage.error("请先登录");
    return;
  }

  const sela = {
    user_id: currentUser.value.user_id,
    cla_id: claId,
    enroll_time: dayjs().format('YYYY-MM-DD HH:mm:ss'),
  };

  selaStore.addSela(sela).then(() => {
    ElMessage.success("选课成功");
  }).catch((error) => {
    ElMessage.error("选课失败: " + error);
  });
};

// 处理分页变化
const handlePageChange = (newPage: number) => {
  currentPage.value = newPage;
};

onMounted(() => {
  Promise.all([
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
    if (currentUser.value && currentUser.value.user_id) {
      // 可以在这里添加其他逻辑，比如获取用户的选课记录
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

.teacher-image {
  width: 50px; /* 设置头像宽度 */
  height: 50px; /* 设置头像高度 */
  border-radius: 50%; /* 设置头像为圆形 */
  object-fit: cover; /* 按比例缩放并裁剪 */
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
