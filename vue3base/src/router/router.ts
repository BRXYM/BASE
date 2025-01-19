import { createRouter, createWebHistory } from 'vue-router'
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

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    // 主页
    {
      path: '/',
      name: 'home',
      components: {
        'app-header':Menu,
        'app-main':Welcome
      }
    },
    {
      path: '/shop',
      name: 'shop',
      components: {
        'app-header':Menu,
        'app-main':Shop
      }
    },
    {
      path: '/order',
      name: 'order',
      components: {
        'app-header':Menu,
        'app-main':Order
      }
    },
    {
      path: '/sela',
      name: 'sela',
      components: {
        'app-header':Menu,
        'app-main':Select
      }
    },
    {
      path: '/class',
      name: 'class',
      components: {
        'app-header':Menu,
        'app-main':Class
      }
    },
    {
      path: '/teacher',
      name: 'teacher',
      components: {
        'app-header':Menu,
        'app-main':Teacher
      }
    },
    {
      path: '/profile',
      name: 'profile',
      components: {
        'app-header':Menu,
        'app-main':Profile
      }
    },
  ]
})

export default router
