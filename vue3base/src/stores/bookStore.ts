import { defineStore } from 'pinia'
import { ref } from 'vue'
import { bookListRegisterService } from '@/api/bookApi';
import { ElNotification } from 'element-plus'


// 前端使用数据结构
export interface Book {
  bookId:number,
  bookUserId:number,
  bookClassroomId:number,
  bookWaiterId:string,
  audit:string,
  bookTime:[
    bookStartTime: string,
    bookEndTime: string
  ],
  enable:string
}

// 后端返回数据结构
interface bookGet{
  bookId:number,
  bookUserId:number,
  bookClassroomId:number,
  bookWaiterId:string,
  audit:string,
  bookStartTime: string,
  bookEndTime: string
  enable:string
}


export const useBookStore = defineStore('book', () => {
  const bookList = ref<Book[]>([])

  function setBookList(newList: Book[]): void {
    bookList.value = newList
  }
  function addBook(book: Book): void {
    bookList.value.push(book)
  }

  
  function getBookListById(id:Number): Book | undefined {
    return bookList.value.find((item) => item.bookId === id)
  }
  function deleteBookList(book:Book){
    const index = bookList.value.findIndex((item) => item.bookId === book.bookId)
    bookList.value.splice(index,1)
  }


  async function initBookList(): Promise<void> {
    const list = await getBookList()
    bookList.value = []
    if (list){
      const updatedList = list.map((bookGet) => ({
        ...bookGet,
        bookWaiterId: String(bookGet.bookWaiterId) === "0" ? '未审核' : String(bookGet.bookWaiterId),
        enable: String(bookGet.enable) === '1' ? '存在' : '删除',
        bookTime: [bookGet.bookStartTime,bookGet.bookEndTime] as [string,string],
      }))
      setBookList(updatedList)
    }
  }
  // 修改数据
  function updateBookList(book:Book):void{
    const index = bookList.value.findIndex((item) => item.bookId === book.bookId)
    bookList.value[index] = book
  }

  

  // 请求后端接口获取数据，数据类型给bookGet
  async function getBookList():Promise<bookGet[] | undefined> {
    try{
      // {data:{statusCode,code,message,list}} 结构赋值，{statusCode,code,message,list}是后端定义的返回值
      let {data:{statusCode,code,message,list}} = await bookListRegisterService()
      if(code === "001"){
        ElNotification({
          title:'获取预定列表',
          message: message,
          type: 'success'
        })
        return list
      } else {
        ElNotification({
          title:'获取预定列表',
          message: message,
          type: 'warning'
        })
        return list
      }
    }catch (error){
      console.log(error)
      ElNotification({
          title:'获取预定列表',
          message: "获取预定列表失败",
          type: 'error'
        })
        return undefined
    }
  }

  function updateBooK(){
    
  }

  return { bookList,getBookListById,addBook,initBookList,deleteBookList,updateBookList }
})
