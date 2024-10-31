import request from '@/utils/request'
import type { Book } from '@/stores/bookStore'

// 获取book列表
export const bookListRegisterService = () => {
    return request.get('/book/getAllBooks')
}

// 删除book
export const deleteBookService = (book: Book) => {
    return request.post('/book/deleteBook', book)
}

// 添加book
export const addBookService = (book: Book) => {
    return request.post('/book/addBook', book)
}

// 修改book
export const updateBookService = (book: Book) => {
    return request.post('/book/updateBook', book)
}