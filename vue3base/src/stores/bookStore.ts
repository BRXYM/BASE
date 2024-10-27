import { defineStore } from 'pinia'
import { ref } from 'vue'

export interface Book {
  bookId:number,
  bookUserId:number,
  bookClassroomId:number,
  bookWaiterId:number,
  audit:string,
  bookStartTime:string,
  bookEndTime:string,
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

  function initBookList(list: Book[]): void {
    const updatedList = list.map((book) => ({
      ...book,
      enable: String(book.enable) === '1' ? '存在' : '删除'
    }))
    setBookList(updatedList)
  }
  function getBookListById(id:Number): Book | undefined {
    return bookList.value.find((item) => item.bookId === id)
  }
  function deleteBookList(book:Book){
    const index = bookList.value.findIndex((item) => item.bookId === book.bookId)
    bookList.value.splice(index,1)
  }

  return { bookList,getBookListById,addBook,initBookList,deleteBookList }
})
