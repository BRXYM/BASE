<template>

<h2>APP</h2>
<el-button @click="table()">表格</el-button>
<!-- <RouterLink to="/table">表格</RouterLink> -->

<RouterView></RouterView>
</template>

<script setup lang="ts">
import { RouterLink, RouterView,useRouter } from 'vue-router';
const router = useRouter()

function table(){
    // 跳转到table页面
    router.push({name:'table'});
}

</script>
