import {createRouter, createWebHistory} from 'vue-router'
import HelloWorld from '@/components/HelloWorld.vue'
import TableTest from '@/components/book/TableTest.vue'
import Menu from '@/components/Menutop.vue'
import Profile from '@/components/Profile.vue'
import Shop from '@/components/shops/Shop.vue'
import Order from '@/components/orders/Order.vue'
import Class from '@/components/classes/Class.vue'
import Select from '@/components/selas/Select.vue'
import Teacher from "@/components/teachers/Teacher.vue";
import Welcome from "@/components/Welcome.vue";
import teacherMenuTop from "@/components/teacherAdmin/teacherMenuTop.vue";
import UploadGood from "@/components/teacherAdmin/UploadGood.vue";
import UploadCla from "@/components/teacherAdmin/UploadCla.vue";
import AdminProfile from "@/components/teacherAdmin/AdminProfile.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        // 主页
        {
            path: '/',
            name: 'home',
            components: {
                'app-header': Menu,
                'app-main': Welcome
            }
        },
        {
            path: '/home',
            name: 'home',
            components: {
                'app-header': Menu,
                'app-main': Welcome
            }
        },
        {
            path: '/shop',
            name: 'shop',
            components: {
                'app-header': Menu,
                'app-main': Shop
            }
        },
        {
            path: '/order',
            name: 'order',
            components: {
                'app-header': Menu,
                'app-main': Order
            }
        },
        {
            path: '/sela',
            name: 'sela',
            components: {
                'app-header': Menu,
                'app-main': Select
            }
        },
        {
            path: '/class',
            name: 'class',
            components: {
                'app-header': Menu,
                'app-main': Class
            }
        },
        {
            path: '/teacher',
            name: 'teacher',
            components: {
                'app-header': Menu,
                'app-main': Teacher
            }
        },
        {
            path: '/profile',
            name: 'profile',
            components: {
                'app-header': Menu,
                'app-main': Profile
            }
        },
        {
            path: '/adminProfile',
            name: 'adminProfile',
            components: {
                'app-header': Menu,
                'app-main': AdminProfile
            }
        },
        {
            path: '/admin',
            name: 'admin',
            components: {
                'app-header': teacherMenuTop,
                'app-main': UploadGood
            }
        },
        {
            path: '/adminUploadGood',
            name: 'adminUploadGood',
            components: {
                'app-header': teacherMenuTop,
                'app-main': UploadGood
            }
        },
        {
            path: '/adminUploadCla',
            name: 'adminUploadCla',
            components: {
                'app-header': teacherMenuTop,
                'app-main': UploadCla
            }
        }
    ]
})

export default router
