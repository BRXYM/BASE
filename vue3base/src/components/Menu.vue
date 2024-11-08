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
        <el-menu-item index="2" v-if="!islogin" @click="drawer = true">登录</el-menu-item>
        <el-sub-menu index="3" v-if="islogin">
            <template #title>{{ user.uname }}</template>
            <el-menu-item index="3-1">个人中心</el-menu-item>
            <el-menu-item index="3-2" @click="logout">退出登录</el-menu-item>
        </el-sub-menu>
    </el-menu>
    <el-drawer v-model="drawer" title="用户登录" size="40%">
        <!-- 登录表单 -->
        <el-form style="max-width: 100%" :model="userForm" status-icon label-width="auto">
            <el-form-item label="用户ID" prop="userid">
                <el-input v-model="userForm.uid" autocomplete="off" />
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="userForm.upass" type="password" autocomplete="off" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="login(userForm)">
                    登录
                </el-button>
                <el-button @click="clearUser()">清除</el-button>
            </el-form-item>
        </el-form>
        <div>
            <el-button @click="innerDrawer = true,clearUser()">没有用户？点击这里</el-button>
            <el-drawer v-model="innerDrawer" title="注册" :append-to-body="true" size="37%">
                <!-- 注册表单 -->
                <el-form style="max-width: 100%" :model="userForm" status-icon label-width="auto">
                    <el-form-item label="用户姓名" prop="uname">
                        <el-input v-model="userForm.uname" />
                    </el-form-item>
                    <el-form-item label="QQ号" prop="uqq">
                        <el-input v-model="userForm.uqq" />
                    </el-form-item>
                    <el-form-item label="邮箱" prop="umile">
                        <el-input v-model="userForm.umile" />
                    </el-form-item>
                    <el-form-item label="手机号" prop="uphone">
                        <el-input v-model="userForm.uphone" />
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input v-model="userForm.upass" type="password" autocomplete="off" />
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="register(userForm),innerDrawer = false">
                            注册
                        </el-button>
                        <el-button @click="clearUser()">清除</el-button>
                    </el-form-item>
                </el-form>
            </el-drawer>
        </div>
    </el-drawer>

</template>

<script lang="ts" setup>
import { ref, watch, onBeforeMount } from 'vue'
import { useViewStore } from '@/stores/view';
import { storeToRefs } from 'pinia';
import { ElMessageBox } from 'element-plus'
import { useUserStore } from '@/stores/user';


import type { User } from '@/stores/user'


const Huser = useUserStore()
const view = useViewStore()

const { islogin } = storeToRefs(view)
const { user } = storeToRefs(Huser)
const { login,register } = Huser


const { setUserNull } = Huser

const drawer = ref(false)
const innerDrawer = ref(false)

const userForm = ref<User>({
    uid: null,
    upass: "",
    uqq: "",
    umile: "",
    uphone: "",
    uname: ""
})


function clearUser() {
    userForm.value.uid = null
    userForm.value.upass = ""
    userForm.value.uqq = ""
    userForm.value.umile = ""
    userForm.value.uphone = ""
    userForm.value.uname = ""
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
    islogin.value = false
    // clearUser()

}

watch(user, (newValue, oldValue) => {
    if (newValue.uid != null) {
        islogin.value = true
        drawer.value = false
    }
})


</script>

<style>
.el-menu--horizontal>.el-menu-item:nth-child(1) {
    margin-right: auto;
}
</style>