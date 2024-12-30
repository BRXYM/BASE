<template>
  <div>
    <!-- 新增模组卡片 -->
    <el-card class="add-mode-card">
      <template #header>
        <div class="card-header">
          <span>新增模组</span>
        </div>
      </template>
      <el-form :model="newMode" label-width="100px">
        <el-form-item label="名称">
          <el-input v-model="newMode.name"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="newMode.txt" type="textarea"></el-input>
        </el-form-item>
        <el-form-item label="模组类型">
          <el-select v-model="newMode.Tid" placeholder="请选择模组类型">
            <el-option
              v-for="mtype in mtypes"
              :key="mtype.Tid"
              :label="mtype.name"
              :value="mtype.Tid"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="文件上传">
          <el-upload
            ref="upload"
            class="upload-demo"
            :action="FILE_URL"
            :limit="1"
            :on-exceed="handleExceed"
            :on-success="handleFileSuccess"
            :before-upload="beforeFileUpload"
          >
            <template #trigger>
              <el-button type="primary">选择文件</el-button>
            </template>
            <el-button class="ml-3" type="success" @click="submitUpload">
              更新文件
            </el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="图片上传">
          <el-upload
            class="avatar-uploader"
            :action="FILE_URL"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img v-if="imageUrl" :src="imageUrl" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addNewMode">添加模组</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-space
        fill
        wrap
        :fill-ratio="20"
        :direction="'horizontal'"
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
        <div class="description"> {{ mode.txt }}</div>
      </el-card>
    </el-space>

    <!-- 模态框 -->
    <el-dialog v-model="dialogVisible" title="模组详情" width="500" center>
      <span v-if="selectedMode">
        <p>名称: {{ selectedMode.name }}</p>
        <p>收藏量: {{ selectedMode.sum }}</p>
        <p class="description"> {{ selectedMode.txt }}</p>
        <p>上传用户: {{currentUser.Uname}}</p> <!-- 根据 Uid 获取用户名 -->
        <img :src="`${FILE_URL}/${selectedMode.file}`" class="dialog-product-image" />
        <br/>
        <el-button type="primary" @click="openFileInNewTab(selectedMode.download)">查看文件</el-button>
        <el-button type="success" @click="addStow(selectedMode)">收藏</el-button>
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
import { useModeStore, useUserStore, useMTypeStore } from '@/stores/store';
import type { Mode, User, MType } from '@/types/type';
import { FILE_URL } from '@/config';
import { ElMessage } from 'element-plus';
import { genFileId } from 'element-plus';
import { Plus } from '@element-plus/icons-vue';
import type { UploadInstance, UploadProps, UploadRawFile, UploadResponse } from 'element-plus';

const modeStore = useModeStore();
const userStore = useUserStore();
const mTypeStore = useMTypeStore();

const { modes } = storeToRefs(modeStore);
const { currentUser } = storeToRefs(userStore);
const { mtypes } = storeToRefs(mTypeStore);

// 模态框相关状态
const dialogVisible = ref(false);
const selectedMode = ref<Mode | null>(null);

// 新增模组相关状态
const newMode = ref<Mode>({
  MOid: null,
  name: '',
  txt: '',
  file: '',
  download: null,
  Uid: null,
  Tid: null,
  sum: 0,
});

const upload = ref<UploadInstance>();
const imageUrl = ref('');
const downloadUrl = ref('');

onMounted(() => {
  if (currentUser.value && currentUser.value.Uid !== null) {
    modeStore.getModesByUid(currentUser.value.Uid);
  } else {
    ElMessage.error("用户未登录");
  }
  mTypeStore.getAllMTypes();
});

// 打开模态框的方法
const openDialog = (mode: Mode) => {
  selectedMode.value = mode;
  dialogVisible.value = true;
};

// 根据 Uid 获取用户名
const getUserName = (Uid: number | null): string => {
  if (Uid === undefined || Uid === null) return '未知用户';
  const user = userStore.users?.value?.find(user => user.Uid === Uid);
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
  // 这里假设已经实现了收藏功能，可以根据需要进行调整
};

// 文件上传处理函数
const handleExceed: UploadProps['onExceed'] = (files) => {
  upload.value!.clearFiles();
  const file = files[0] as UploadRawFile;
  file.uid = genFileId();
  upload.value!.handleStart(file);
};

const submitUpload = () => {
  upload.value!.submit();
};

// 图片路径获取函数
const handleAvatarSuccess: UploadProps['onSuccess'] = (
  response: UploadResponse,
  uploadFile: UploadRawFile
) => {
  imageUrl.value = URL.createObjectURL(uploadFile.raw!);
  newMode.value.file = uploadFile.name;
};


// 文件上传成功处理函数
const handleFileSuccess: UploadProps['onSuccess'] = (
  response: UploadResponse,
  uploadFile: UploadRawFile
) => {
  downloadUrl.value = URL.createObjectURL(uploadFile.raw!)
  newMode.value.download = uploadFile.name;
};

// 添加模组的方法
const addNewMode = () => {
  if (currentUser.value && currentUser.value.Uid !== null) {
    newMode.value.Uid = currentUser.value.Uid;
    modeStore.addMode(newMode.value).then(() => {
      ElMessage.success("模组添加成功");
      newMode.value = {
        MOid: null,
        name: '',
        txt: '',
        file: '',
        download: null,
        Uid: null,
        Tid: null,
        sum: 0,
      };
      imageUrl.value = '';
    }).catch(() => {
      ElMessage.error("模组添加失败");
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

/* 新增模组卡片样式 */
.add-mode-card {
  margin-bottom: 20px;
}

.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}

.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>
