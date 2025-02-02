<template>
  <div class="upload-good-container">
    <h1>上传商品</h1>
    <el-form :model="currentGood" status-icon label-width="120px" ref="goodForm">
      <el-form-item label="商品名称" prop="good_name">
        <el-input v-model="currentGood.good_name" />
      </el-form-item>
      <el-form-item label="商品价格" prop="good_price">
        <el-input v-model="currentGood.good_price" type="number" />
      </el-form-item>
      <el-form-item label="商品数量" prop="good_num">
        <el-input v-model="currentGood.good_num" type="number" />
      </el-form-item>
      <el-form-item label="商品图片">
        <el-upload
            class="avatar-uploader"
            :action="FILE_URL"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            :limit="1"
        >
          <img v-if="currentGood.good_img" :src="FILE_URL + '/' + currentGood.good_img" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item label="商品描述" prop="good_txt">
        <el-input v-model="currentGood.good_txt" type="textarea" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addGood">上传</el-button>
        <el-button @click="setCurrentGoodNull">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import { ElMessage } from 'element-plus'
import { useGoodStore } from '@/stores/store'
import { FILE_URL } from '@/config'
import { Plus } from '@element-plus/icons-vue'
import {storeToRefs} from "pinia";

const goodStore = useGoodStore()
const { addGood ,setCurrentGoodNull} = goodStore
const {currentGood} = storeToRefs(goodStore)

const handleAvatarSuccess = (response: any, uploadFile: any) => {
  currentGood.value.good_img = uploadFile.name
}

const beforeAvatarUpload = (rawFile: any) => {
  if (!['image/jpeg', 'image/png', 'image/gif'].includes(rawFile.type)) {
    ElMessage.error('文件格式不正确')
    return false
  }
  return true
}
</script>

<style scoped>
.upload-good-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #f9f9f9;
}

.avatar-uploader .avatar {
  width: 100px;
  height: 100px;
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
  width: 100px;
  height: 100px;
  text-align: center;
}
</style>
