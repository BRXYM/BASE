// router.ts
import { createRouter, createWebHistory } from 'vue-router';
import MenuTop from '@/components/MenuTop.vue';
import HelloWorld from "@/components/HelloWorld.vue";
import MenuLeft from "@/components/MenuLeft.vue";
import Table from "@/components/Table.vue"; // 引入 Table 组件

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        // 主页
        {
            path: '/',
            name: 'home',
            components: {
                'app_header': MenuTop,
                'app_main': HelloWorld
            }
        },
        {
            path: '/manager',
            name: 'manager',
            components: {
                'app_header': MenuTop,
                'app_aside': MenuLeft,
                'app_main': Table // 使用 Table 组件
            }
        },
        // 动态路由，根据表名显示不同的表数据
        {
            path: '/table/:tableName', // 确保路径中包含 :tableName 参数
            name: 'table',
            components: {
                'app_header': MenuTop,
                'app_aside': MenuLeft,
                'app_main': Table // 使用 Table 组件
            }
        },
    ]
})

export default router;
