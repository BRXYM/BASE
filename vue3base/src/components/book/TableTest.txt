<template>
        <el-button type="primary" @click="add">添加</el-button>
        <el-button type="primary" @click="back">回到初始页面</el-button>
    <transition 
      enter-active-class="animate__animated animate__fadeInLeftBig"
      leave-active-class="animate__animated animate__fadeOutLeftBig">
      <div v-if="show">
        <el-table :data="bookList" style="width: 100%" :row-class-name="tableRowClassName">
          <el-table-column fixed prop="bookId" label="预定ID" min-width="60" />
          <el-table-column prop="bookUserId" label="用户ID" min-width="70" />
          <el-table-column prop="bookClassroomId" label="房间ID" min-width="70" />
          <el-table-column prop="bookWaiterId" label="审核员ID" min-width="90" />
          <el-table-column prop="audit" label="审核状态" min-width="80" />
          <el-table-column prop="bookTime" label="使用时间" min-width="165" />
          <!-- <el-switch v-model="updatebook.enable" class="ml-2" style="--el-switch-on-color: #13ce66;
        --el-switch-off-color: #ff4949" active-text="存在" inactive-text="删除" min-width="120"/> -->
          <el-table-column prop="enable" label="状态" min-width="120" />
          <el-table-column fixed="right" label="操作" min-width="140">
            <template #default="scope">
              <el-button size="small" @click="handleEdit(scope.row)">
                修改
              </el-button>
              <el-button size="small" type="danger" @click="handleDelete(scope.row)">
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>


        <!-- 修改 -->
        <el-dialog v-model="editVisible" :title=title width="500">
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
              <el-date-picker v-model="updatebook.bookTime" type="datetime" placeholder="选择使用时间" :editable="false"
                format="YYYY/MM/DD hh:mm:ss" value-format="YYYY-MM-DD hh:mm:ss" style="width: 100%" />
            </el-form-item>
            <el-form-item label="状态" :label-width="formLabelWidth">
              <el-switch v-model="updatebook.enable" class="ml-2" style="--el-switch-on-color: #13ce66;
        --el-switch-off-color: #ff4949" active-text="存在" inactive-text="删除" />
            </el-form-item>
          </el-form>
          <template #footer>
            <div class="dialog-footer">
              <el-button @click="editBookOk()">确认</el-button>
              <el-button type="primary" @click="editVisible = false">
                取消
              </el-button>
            </div>
          </template>
        </el-dialog>

        <!-- 添加 -->
        <el-dialog v-model="addVisible" title="添加Book" width="500">
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
              <el-date-picker v-model="updatebook.bookTime" type="datetime" placeholder="选择使用时间" :editable="false"
                format="YYYY/MM/DD hh:mm:ss" value-format="YYYY-MM-DD hh:mm:ss" style="width: 100%" />
            </el-form-item>
            <el-form-item label="状态" :label-width="formLabelWidth">
              <el-switch v-model="updatebook.enable" class="ml-2" style="--el-switch-on-color: #13ce66;
        --el-switch-off-color: #ff4949" active-text="存在" inactive-text="删除" />
            </el-form-item>
          </el-form>
          <template #footer>
            <div class="dialog-footer">
              <el-button @click="addBookOk()">确认</el-button>
              <el-button type="primary" @click="addVisible = false">
                取消
              </el-button>
            </div>
          </template>
        </el-dialog>
        <span v-loading.fullscreen.lock="fullscreenLoading"></span>
      </div>
    </transition>
</template>

<script lang="ts" setup>
// 功能组件
import { storeToRefs } from 'pinia';
import { onMounted,onBeforeUnmount,reactive, ref } from 'vue';
import 'animate.css';
import { RouterLink, RouterView,useRouter } from 'vue-router';

// 功能组件功能实例化
const router = useRouter()

// 数据组件pinia
import { useBookStore } from '@/stores/bookStore';
import type { Book } from '@/stores/bookStore';


// 初始化解构数据常量
const book = useBookStore()
const { bookList, fullscreenLoading } = storeToRefs(book)
const { getBookListById, addBook, initBookList, deleteBookList, updateBookList } = book

// 组件内状态变量 
const title = ref()
const editVisible = ref(false)
const addVisible = ref(false)
const formLabelWidth = '90px'
const updatebook = ref<Book>({
  bookId: 0,
  bookUserId: 0,
  bookClassroomId: 0,
  bookWaiterId: 0,
  audit: '',
  bookTime: '',
  enable: false
})
const show = ref(false)

// 组件内方法
function add() {
  updatebook.value = {
    bookId: 0,
    bookUserId: 0,
    bookClassroomId: 0,
    bookWaiterId: 0,
    audit: '',
    bookTime: '',
    enable: false
  }
  addVisible.value = true
}

function handleDelete(row: Book) {
  deleteBookList(row)
}

function handleEdit(row: Book) {
  title.value = String(row.bookId)
  updatebook.value = { ...row }
  editVisible.value = true
}

function addBookOk() {
  addBook(updatebook.value)
  addVisible.value = false
}

function editBookOk() {
  updateBookList(updatebook.value)
  editVisible.value = false
}

// 路由控制
function back(){
  show.value = false
    // 跳转到table页面
    setTimeout(() => {
      router.push({name:'home'});
    }, 500)
}


// 页面其他方法

const tableRowClassName = ({
  row
}: {
  row: Book
}) => {
  if (!row.enable) {
    return 'warning-row'
  } else if (row.enable) {
    return 'success-row'
  }
  return ''
}

// 页面数据初始化方法
onMounted(() => {
  initBookList()
  show.value = true
})

onBeforeUnmount(() => {
  show.value = false
})

</script>

<style>
.el-table .warning-row {
  --el-table-tr-bg-color: var(--el-color-warning-light-8);
}

.el-table .success-row {
  --el-table-tr-bg-color: var(--el-color-success-light-8);
}
</style>
