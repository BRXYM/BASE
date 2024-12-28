<template>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" :ellipsis="false"
        @select="handleSelect">

        <el-menu-item index="1">
            克苏鲁跑团
        </el-menu-item>
        <el-menu-item index="0">查看模组</el-menu-item>
        <el-menu-item index="4">上传模组</el-menu-item>
        <el-menu-item index="5">查看评论</el-menu-item>
        <el-menu-item index="6">留言信箱</el-menu-item>
        <el-menu-item index="7">我的收藏</el-menu-item>
        <el-menu-item index="8">历史留言</el-menu-item>
        <el-menu-item index="2" v-if="islogin === 0" @click="islogin = 2">登录</el-menu-item>
        <el-sub-menu index="3" v-if="islogin === 1">
            <template #title>{{ currentUser?.Uname || '未登录'}}</template>
            <el-menu-item index="3-1">个人中心</el-menu-item>
            <el-menu-item index="3-2" @click="logout">退出登录</el-menu-item>
        </el-sub-menu>
    </el-menu>
    <el-drawer 
    v-model="drawer" 
    title="用户登录" 
    size="40%" 
    :close-on-click-modal="false" 
    :close-on-press-escape="false" 
    :show-close="false"
    >
        <!-- 登录表单 -->
        <el-form style="max-width: 100%" :model="userForm" status-icon label-width="auto">
            <el-form-item label="手机号" prop="userphone">
                <el-input v-model="userForm.Uphone" autocomplete="off" />
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="userForm.Upass" type="password" autocomplete="off" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="login(userForm)">
                    登录
                </el-button>
                <el-button @click="clearUser()">清除</el-button>
                <el-button @click="islogin = 0">关闭登录页面</el-button>
            </el-form-item>
        </el-form>
        <div>
            <el-button @click="islogin = 3,clearUser()">没有用户？点击这里</el-button>
            
            <el-drawer 
            v-model="innerDrawer" 
            title="注册" 
            :append-to-body="true" 
            size="40%"
            :close-on-click-modal="false" 
            :close-on-press-escape="false" 
            :show-close="false"             
            >
            <el-form style="max-width: 100%" :model="userForm" status-icon label-width="auto" ref="registerForm">
                    <el-form-item label="用户姓名" prop="uname">
                        <el-input v-model="userForm.Uname" />
                    </el-form-item>
                    <el-form-item label="QQ号" prop="uqq">
                        <el-input v-model="userForm.Uqq" />
                    </el-form-item>
                    <el-form-item label="邮箱" prop="umile">
                        <el-input v-model="userForm.Umile" />
                    </el-form-item>
                    <el-form-item label="手机号" prop="uphone">
                        <el-input v-model="userForm.Uphone" />
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input v-model="userForm.Upass" type="password" autocomplete="off" />
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="regist(userForm)">
                            注册
                        </el-button>
                        <el-button @click="clearUser()">清除</el-button>
                        <el-button @click="islogin = 2">返回登录</el-button>
                    </el-form-item>
                </el-form>
            </el-drawer>
        </div>
    </el-drawer>

</template>

<script lang="ts" setup>
import { ref, watch, onBeforeMount } from 'vue'
import { storeToRefs } from 'pinia';
import { ElMessageBox } from 'element-plus'
import { useUserStore } from '@/stores/store';

import type { User } from '@/types/index'
import { ca, tr } from 'element-plus/es/locales.mjs';

const Huser = useUserStore()

// 正确解构 user 和 islogin
const { islogin, currentUser } = storeToRefs(Huser) // 移除了 user 的解构
const { login,regist } = Huser
const drawer = ref(false)
const innerDrawer = ref(false)

const userForm = ref<User>({
    Uid: null,
    Upass: null,
    Uqq: null,
    Umile: null,
    Uphone: null,
    Uname: null
})

function clearUser() {
    userForm.value.Uid = null;
    userForm.value.Upass = null;
    userForm.value.Uqq = null;
    userForm.value.Umile = null;
    userForm.value.Uphone = null;
    userForm.value.Uname = null;
}

const handleClose = (done: () => void) => {
    ElMessageBox.confirm('You still have unsaved data, proceed?')
        .then(() => {
            done()
        })
        .catch(() => {
            // catch error
        })
}

const activeIndex = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
}

function logout() {
    Huser.setUserNull();
}

// 使用正确的 user 引用
watch(() => Huser.currentUser, (newValue, oldValue) => { // 直接监听 store 中的 user 属性
    if (newValue?.Uid != null) {
        islogin.value = 1
        drawer.value = false
    }
})

watch(() => islogin.value,(newValue , oldValue)=>{
   switch(newValue){
        case 0:
            drawer.value = false
            innerDrawer.value = false
            break;
        case 1:
            drawer.value = false
            innerDrawer.value = false
            break;
        case 2:
            innerDrawer.value = false
            drawer.value = true
            break;
        case 3:
            drawer.value = false
            innerDrawer.value = true
            break;
    }
})



</script>

<style>
.el-menu--horizontal>.el-menu-item:nth-child(1) {
    margin-right: auto;
}
</style>