import request from '@/utils/request'
import type { User } from '@/stores/user'

// 登录
export const loginService = (user: User) => {
    return request.post('/user/login', user)
}

// 注册
export const registerService = (user: User) => {
    return request.post('/user/addUser', user)
}

// 修改用户信息
export const userUpdateService = (user: User) => {
    return request.post('/user/updateUser', user)
}