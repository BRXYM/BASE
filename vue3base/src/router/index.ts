import { createRouter, createWebHistory } from 'vue-router'
import HelloWorld from '@/components/HelloWorld.vue'
import TableTest from '@/components/book/TableTest.vue'
import Menu from '@/components/Menu.vue'
import Welcome from '@/components/Welcome.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      components: {
        'app-header': Menu,
        'app-main': Welcome
      }
    },
    {
      path: '/hello',
      name: 'hello',
      component: HelloWorld
    },
    {
      path: '/table',
      name: 'table',
      component: TableTest
    }
  ]
})

export default router