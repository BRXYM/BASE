<template>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" :ellipsis="false"
        @select="handleSelect">

        <el-menu-item index="0">
            克苏鲁跑团
        </el-menu-item>
        <el-menu-item index="1">查看模组</el-menu-item>
        <el-menu-item index="4">上传模组</el-menu-item>
        <el-menu-item index="5">查看评论</el-menu-item>
        <el-menu-item index="6">留言信箱</el-menu-item>
        <el-menu-item index="7">我的收藏</el-menu-item>
        <el-menu-item index="8">历史留言</el-menu-item>
        <el-menu-item index="2" v-if="!islogin" @click="drawer = true">登录</el-menu-item>
        <el-sub-menu index="3" v-if="islogin">
            <template #title>{{ islogin }}</template>
            <el-menu-item index="3-1">个人中心</el-menu-item>
            <el-menu-item index="3-2">退出登录</el-menu-item>
        </el-sub-menu>
    </el-menu>
    <el-drawer v-model="drawer" title="用户登录" size="50%">
        <Login> </Login>
        <div>
            <el-button @click="innerDrawer = true">没有用户？点击这里</el-button>
            <el-drawer v-model="innerDrawer" title="注册" :append-to-body="true">
                <p>注册</p>
            </el-drawer>
        </div>
    </el-drawer>

</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { useViewStore } from '@/stores/view';
import { storeToRefs } from 'pinia';
import { ElMessageBox } from 'element-plus'

import Login from './user/Login.vue';


const view = useViewStore()

const { islogin } = storeToRefs(view)
const drawer = ref(false)
const innerDrawer = ref(false)

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
</script>

<style>
.el-menu--horizontal>.el-menu-item:nth-child(1) {
    margin-right: auto;
}
</style>