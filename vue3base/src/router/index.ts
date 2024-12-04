import { createRouter, createWebHistory } from 'vue-router'
import HelloWorld from '@/components/HelloWorld.vue'
import TableTest from '@/components/book/TableTest.vue'
import Menu from '@/components/Menu.vue'
import Welcome from '@/components/Welcome.vue'
import GoodList from '@/components/goods/GoodList.vue'
import orderList from '@/components/orders/orderList.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      components: {
        'app-header': Menu,
        'app-main-welcome': Welcome,
        'app-main-list': GoodList
      }
    },
    {
      path: '/goods',
      name: 'goods',
      components: {
        'app-header': Menu,
        'app-main-list': GoodList
      }
    },
    {
      path: '/order',
      name: 'order',
      components: {
        'app-header': Menu,
        'app-main-list': orderList
      }
    },
    {
      path: '/table',
      name: 'table',
      component: TableTest
    }
  ]
})

export default router