import { defineStore,storeToRefs  } from 'pinia'
import { ref } from 'vue'
import { loginService, registerService, userUpdateService } from '@/api/userApi'
import { ElNotification } from 'element-plus'


export interface User {
    uid: number | null;
    upass: string;
    uqq: string;
    umile: string;
    uphone: string;
    uname: string;
}

export const useUserStore = defineStore('user', () => {
    const user = ref({
        uid: null,
        upass: '',
        uqq: '',
        umile: '',
        uphone: '',
        uname: ''
    })
    function setUserNull() {
        user.value = {
            uid: null,
            upass: '',
            uqq: '',
            umile: '',
            uphone: '',
            uname: ''
        }
    }

// 注册
    async function register(registeruser: User) {
        try {
            let { data: { statusCode, code, list, message } } = await registerService(registeruser)
            showMessage('注册', code, message)
            if (code === "001") {
                user.value = list
            }
        } catch (error) {
            console.log(error)
            netError()
        }
    }


// 登录
    async function login(loginuser: User) {
        try {
            let { data: { statusCode, code, list, message } } = await loginService(loginuser)
            showMessage('登录', code, message)
            if (code === "001") {
                user.value = list
            }
        } catch (error) {
            console.log(error)
            netError()
        }
    }

    function showMessage(title: string, code: string, message: string) {
        if (code === "001") {
            ElNotification({
                title: title,
                message: message,
                type: 'success'
            })
        } else {
            ElNotification({
                title: title,
                message: message,
                type: 'warning'
            })
        }
    }

    function netError() {
        ElNotification({
            title: '网络错误',
            message: '网络错误，请检查网络连接',
            type: 'error'
        })
    }

    return { user,login,setUserNull,register }

})