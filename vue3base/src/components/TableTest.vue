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
          Edit
        </el-button>
        <el-button
          size="small"
          type="danger"
          @click="handleDelete(scope.row)"
        >
          Delete
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>
  
  <script lang="ts" setup>
import { useBookStore } from '@/stores/bookStore';
import type { Book } from '@/stores/bookStore';
import { storeToRefs } from 'pinia';
import { bookListRegisterService } from '@/api/bookApi';
import { ElNotification } from 'element-plus'
import { onMounted } from 'vue';
const book = useBookStore()

const {bookList} = storeToRefs(book)
const { getBookListById,addBook,initBookList,deleteBookList } = book

const getBookList = async()=> {
    try{
      // {data:{statusCode,code,message,list}} 结构赋值，{statusCode,code,message,list}是后端定义的返回值
      let {data:{statusCode,code,message,list}} = await bookListRegisterService()
      if(code === "001"){
        initBookList(list)
        ElNotification({
          title:'获取预定列表',
          message: message,
          type: 'success'
        })
        getBookListById(message)
      } else {
        ElNotification({
          title:'获取预定列表',
          message: message,
          type: 'warning'
        })
      }
    }catch (error){
      console.log(error)
      ElNotification({
          title:'获取预定列表',
          message: "获取预定列表失败",
          type: 'error'
        })
    }
}

function handleDelete (row: Book){
  deleteBookList(row)
}

function handleEdit (row: Book){
  
}


onMounted(() => {
  getBookList()
})
  
  </script>
  