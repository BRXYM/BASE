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
        <el-button type="primary" @click="handleAddGood">上传</el-button>
        <el-button @click="setCurrentGoodNull">重置</el-button>
      </el-form-item>
    </el-form>


  </div>
  <!-- 商品表格 -->
  <el-table :data="goods as Good[]" style="width: 100%">
    <el-table-column prop="good_id" label="商品ID" />
    <el-table-column prop="good_name" label="商品名称" />
    <el-table-column prop="good_price" label="商品价格" />
    <el-table-column prop="good_num" label="商品数量" />
    <el-table-column prop="good_img" label="商品图片">
      <template #default="scope">
        <img :src="FILE_URL + '/' + scope.row.good_img" class="table-avatar" />
      </template>
    </el-table-column>
    <el-table-column prop="good_txt" label="商品描述" />
    <el-table-column label="操作" width="200">
      <template #default="scope">
        <el-button size="small" @click="editGood(scope.row)">修改</el-button>
        <el-button size="small" type="danger" @click="handleDeleteGood(scope.row.good_id)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>

  <!-- 修改商品模态框 -->
  <el-dialog v-model="dialogVisible" title="修改商品">
    <el-form :model="currentGood" label-width="120px">
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
    </el-form>
    <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="saveEdit">确定</el-button>
        </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox, ElDialog, ElForm, ElFormItem, ElInput, ElButton, ElTable, ElTableColumn, ElUpload, ElIcon } from 'element-plus'
import { useGoodStore } from '@/stores/store'
import { FILE_URL } from '@/config'
import { Plus } from '@element-plus/icons-vue'
import { storeToRefs } from "pinia";
import type { Good } from "@/type/type";

const goodStore = useGoodStore()
const { addGood, setCurrentGoodNull, fetchGoods, deleteGood, updateGood } = goodStore
const { currentGood, goods } = storeToRefs(goodStore)

// 模态框显示控制
const dialogVisible = ref(false)

onMounted(() => {
  // 获取商品列表
  fetchGoods()
})

function handleAddGood() {
  if (!currentGood.value.good_name || !currentGood.value.good_price || !currentGood.value.good_num || !currentGood.value.good_img || !currentGood.value.good_txt) {
    ElMessage.error('请填写完整信息')
    return
  }
  addGood().then(() => {
    fetchGoods()
  })
}

function handleDeleteGood(good_id: number) {
  ElMessageBox.confirm('确认删除该商品？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    deleteGood(good_id).then(() => {
      fetchGoods()
      ElMessage.success('删除成功')
    })
  }).catch(() => {
    ElMessage.info('已取消删除')
  })
}

function editGood(good: Good) {
  // 设置当前商品为要编辑的商品
  currentGood.value = { ...good }
  dialogVisible.value = true
}

function saveEdit() {
  if (!currentGood.value.good_name || !currentGood.value.good_price || !currentGood.value.good_num || !currentGood.value.good_img || !currentGood.value.good_txt) {
    ElMessage.error('请填写完整信息')
    return
  }
  updateGood(currentGood.value).then(() => {
    fetchGoods()
    dialogVisible.value = false
    ElMessage.success('修改成功')
  })
}

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

.table-avatar {
  width: 50px;
  height: 50px;
  display: block;
}
</style>
