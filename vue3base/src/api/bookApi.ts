import request from '@/utils/request'

// 获取book列表
export const bookListRegisterService = () => {
    return request.get('/book/getAllBooks')
}