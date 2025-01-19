<template>
  <div>
    <h2>所有教师</h2>
    <el-space
      fill
      wrap
      :fill-ratio="fillRatio"
      :direction="direction"
      style="width: 100%"
    >
      <el-card v-for="teach in teaches" :key="teach.teach_id" class="box-card">
        <template #header>
          <div class="card-header">
            <span>{{ teach.teach_name }}</span>
          </div>
        </template>
        <p><strong>教师ID:</strong> {{ teach.teach_id }}</p>
        <p><strong>教师名称:</strong> {{ teach.teach_name }}</p>
        <p><strong>教师头像:</strong> <img :src="`${FILE_URL}/${teach.teach_img}`" alt="教师头像" class="teacher-image" /></p>
        <p><strong>联系方式:</strong> {{ teach.teach_phone }}</p>
        <p><strong>简介:</strong> {{ teach.teach_txt }}</p>
        <!-- 添加按钮以显示当前教师全部课程信息 -->
        <el-button type="info" @click="showAllClasses(teach.teach_id)">显示全部课程</el-button>
      </el-card>
    </el-space>

    <!-- 课程详情模态框 -->
    <el-dialog v-model="dialogVisible" title="教师课程详情" width="80%">
      <div v-if="loading">
        <el-skeleton :rows="5" animated />
      </div>
      <div v-else-if="teacherClasses.length > 0">
        <el-space
            fill
            wrap
            :fill-ratio="sonfillRatio"
            :direction="direction"
            style="width: 100%"
        >
        <el-card v-for="cla in paginatedClasses" :key="cla.cla_id" class="class-card">
          <template #header>
            <div class="card-header">
              <span>{{ cla.cla_name }}</span>
            </div>
          </template>
          <div class="class-details">
            <p><strong>课程ID:</strong> {{ cla.cla_id }}</p>
            <p><strong>教师:</strong> {{ teachName }}</p>
            <p><strong>联系方式:</strong> {{ teachPhone }}</p>
            <p><strong>课程时间:</strong> {{ cla.cla_time }}</p>
          </div>
        </el-card>
        </el-space>

        <el-pagination
          v-if="totalClasses > pageSize"
          background
          layout="prev, pager, next"
          :total="totalClasses"
          :page-size="pageSize"
          v-model:current-page="currentPage"
          @current-change="handlePageChange"
        />
      </div>
      <div v-else>
        <p>该教师没有课程。</p>
      </div>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">关闭</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { storeToRefs } from 'pinia';
import { useTeachStore, useClaStore } from '@/stores/store';
import type { Teach, Cla } from '@/type/type';
import { ElMessage } from 'element-plus';
import { FILE_URL } from "@/config";

const teachStore = useTeachStore();
const claStore = useClaStore();

const { teaches } = storeToRefs(teachStore);
const { classes } = storeToRefs(claStore);

const direction = ref<'horizontal' | 'vertical'>('horizontal');
const fillRatio = ref(100);
const sonfillRatio = ref(20);

const dialogVisible = ref(false);
const teacherClasses = ref<Cla[]>([]);
const teachName = ref<string>('');
const teachPhone = ref<string>('');
const loading = ref(false);

const pageSize = ref(8);
const currentPage = ref(1);
const totalClasses = ref(0);

const paginatedClasses = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value;
  const end = start + pageSize.value;
  return teacherClasses.value.slice(start, end);
});

// 处理显示全部课程按钮点击事件
const showAllClasses = async (teachId: number) => {
  claStore.setCurrentClassNull()
  const teach = teaches.value?.find(t => t.teach_id === teachId);
  if (teach) {
    teachName.value = teach.teach_name;
    teachPhone.value = teach.teach_phone;
  }

  loading.value = true;
  try {
    await claStore.getClasByTeacherId(teachId);
    teacherClasses.value = classes.value || [];
    totalClasses.value = teacherClasses.value.length;
    currentPage.value = 1; // 重置当前页码
    dialogVisible.value = true;
  } catch (error) {
    ElMessage.error("获取课程失败: " + error);
  } finally {
    loading.value = false;
  }
};

const handlePageChange = (page: number) => {
  currentPage.value = page;
};

onMounted(() => {
  teachStore.fetchTeaches().catch((error) => {
    ElMessage.error("获取教师失败: " + error);
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
  width: 100px; /* 设置头像宽度 */
  height: 100px; /* 设置头像高度 */
  border-radius: 50%; /* 设置头像为圆形 */
  object-fit: cover; /* 按比例缩放并裁剪 */
}

.class-card {
  margin-bottom: 16px;
  border: 1px solid #ebeef5;
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.class-details {
  padding: 10px;
  font-size: 14px;
}

.el-pagination {
  margin-top: 20px;
  text-align: center;
}
</style>

