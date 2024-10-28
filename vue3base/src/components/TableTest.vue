<template>
  <el-table :data="bookList" style="width: 100%">
    <el-table-column fixed prop="bookId" label="预定ID" min-width="60" />
    <el-table-column prop="bookUserId" label="用户ID" min-width="70" />
    <el-table-column prop="bookClassroomId" label="房间ID" min-width="70" />
    <el-table-column prop="bookWaiterId" label="审核员ID" min-width="90" />
    <el-table-column prop="audit" label="审核状态" min-width="80" />
    <el-table-column prop="bookStartTime" label="开始使用时间" min-width="165" />
    <el-table-column prop="bookEndTime" label="结束使用时间" min-width="165" />
    <el-table-column prop="enable" label="状态" min-width="120" />
    <el-table-column fixed="right" label="操作" min-width="140">
      <template #default="scope">
        <el-button size="small" @click="handleEdit(scope.row)">
          修改
        </el-button>
        <el-button
          size="small"
          type="danger"
          @click="handleDelete(scope.row)"
        >
          删除
        </el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-dialog v-model="editVisible" title="修改预定信息" width="500">
    <el-form :model="updatebook">
      <el-form-item label="用户ID" :label-width="formLabelWidth">
        <el-input v-model="updatebook.bookUserId" autocomplete="off" />
      </el-form-item>
      <el-form-item label="房间ID" :label-width="formLabelWidth">
        <el-input v-model="updatebook.bookClassroomId" autocomplete="off" />
      </el-form-item>
      <el-form-item label="审核员ID" :label-width="formLabelWidth">
        <el-input v-model="updatebook.bookWaiterId" autocomplete="off" />
      </el-form-item>
      <el-form-item label="审核状态" :label-width="formLabelWidth">
        <el-select v-model="updatebook.audit" placeholder="请选择审核状态">
          <el-option label="通过" value="通过" />
          <el-option label="未通过" value="未通过" />
        </el-select>
      </el-form-item>
      <el-form-item label="使用时间" :label-width="formLabelWidth">
        <el-date-picker
          v-model="updatebook.bookTime"
          type="datetimerange"
          range-separator="到"
          start-placeholder="开始时间"
          end-placeholder="结束时间"
          :clearable="false"
          :editable="false"
        />
      </el-form-item>
      <el-form-item label="状态" :label-width="formLabelWidth">
        <el-select v-model="updatebook.enable" placeholder="请选择状态">
          <el-option label="存在" value="存在" />
          <el-option label="删除" value="删除" />
        </el-select>
      </el-form-item>
      
      
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="updatebookOk()">确认</el-button>
        <el-button type="primary" @click="editVisible = false">
          取消
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>
  
  <script lang="ts" setup>
import { useBookStore } from '@/stores/bookStore';
import type { Book } from '@/stores/bookStore';
import { storeToRefs } from 'pinia';

import { onMounted,reactive,ref } from 'vue';
const book = useBookStore()
const {bookList} = storeToRefs(book)
const { getBookListById,addBook,initBookList,deleteBookList,updateBookList } = book



const editVisible = ref(false)
const formLabelWidth = '90px'
const updatebook = ref<Book>({
  bookId:0,
  bookUserId:0,
  bookClassroomId:0,
  bookWaiterId:'',
  audit:'',
  bookTime:['',''],
  enable:''
})




function handleDelete (row: Book){
  deleteBookList(row)
}

function handleEdit (row: Book){
  updatebook.value = {...row}
  editVisible.value = true
}

function updatebookOk(){
  updateBookList(updatebook.value)
  editVisible.value = false
}


onMounted(() => {
  initBookList()
})
  
  </script>
  