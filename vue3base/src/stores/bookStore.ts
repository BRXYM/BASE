import { defineStore } from 'pinia'
import { ref } from 'vue'
import { bookListRegisterService,deleteBookService,addBookService,updateBookService } from '@/api/bookApi';
import { ElNotification } from 'element-plus'
export interface Book{
  bookId:number,
  bookUserId:number,
  bookClassroomId:number,
  bookWaiterId:number,
  audit:string,
  bookTime: string,
  enable:boolean
}

const fullscreenLoading = ref(false)


export const useBookStore = defineStore('book', () => {
  const bookList = ref<Book[]>([])

  function setBookList(newList: Book[]): void {
    bookList.value = {...newList}
  }
  async function addBook(book: Book) {
    if( await addBooklist(book) === "001"){
      initBookList()
    // bookList.value.push(book)
    }
  }

  
  function getBookListById(id:Number): Book | undefined {
    return bookList.value.find((item) => item.bookId === id)
  }
  async function deleteBookList(book:Book){
    const index = bookList.value.findIndex((item) => item.bookId === book.bookId)
    if(await deleteBook(book) === '001'){
      initBookList()
      // bookList.value.splice(index,1)
    }
  }


  async function initBookList(): Promise<void> {
    const list = await getBookList()
    bookList.value = list || []
  }

async function updateBookList(book:Book){
  const index = bookList.value.findIndex((item) => item.bookId === book.bookId)
    if(await updateBook(book) === '001'){
      initBookList()
      // bookList.value[index] = book
    }
  }
  // 修改数据
  async function updateBook(book:Book):Promise<string | undefined> {
    try{
      let {data:{statusCode,code,message,list}} = await updateBookService(book)
      showMessage('修改预定',code,message)
      return code
    } catch (error) {
      console.log(error)
      netError()
      return undefined
    }
  }
  // 请求后端接口获取数据
  async function addBooklist(book:Book):Promise<string | undefined> {
    try{
      let {data:{statusCode,code,message,list}} = await addBookService(book)
      showMessage('添加预定',code,message)
      return code
    } catch (error) {
      console.log(error)
      netError()
      return undefined
    }
  }
  async function getBookList():Promise<Book[] | undefined> {
    try{
      // {data:{statusCode,code,message,list}} 结构赋值，{statusCode,code,message,list}是后端定义的返回值
      let {data:{statusCode,code,message,list}} = await bookListRegisterService()
      showMessage('获取预定',code,message)
      return list
    }catch (error){
      console.log(error)
      netError()
      return undefined
    }
  }

async function deleteBook(book:Book):Promise<string | undefined> {
  try {
    let {data:{statusCode,code,list,message}} = await deleteBookService(book)
    showMessage('删除预定',code,message)
    return code
  } catch (error){
    console.log(error)
    netError()
    return undefined
  }
}

  function showMessage(title:string,code:string,message:string){
    if (code === "001"){
      ElNotification({
        title:title,
        message: message,
        type: 'success'
      })
    }else {
      ElNotification({
        title:title,
        message: message,
        type: 'warning'
      })
    }
  }
  function netError(){
    ElNotification({
      title:'网络错误',
      message: '请检查网络',
      type: 'error'
    })
  }
  return { bookList,fullscreenLoading,getBookListById,addBook,initBookList,deleteBookList,updateBookList, }
})
