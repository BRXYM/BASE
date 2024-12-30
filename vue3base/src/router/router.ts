import { createRouter, createWebHistory } from 'vue-router'
import Menu from '@/components/Menu.vue';
import ModeList from '@/components/modes/ModeList.vue';
import Welcome from '@/components/Welcome.vue';
import ModeType from '@/components/modes/ModeType.vue';
import CommentPage from '@/components/comments/CommentPage.vue';
import MessagePage from '@/components/messages/MessagePage.vue';
import StowPage from '@/components/stows/StowPage.vue';
import ModeAdd from "@/components/modes/ModeAdd.vue";


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    // 主页
    {
      path: '/',
      name: 'home',
      components: {
        'app-header':Menu,
        'app-main-welcome':Welcome,
        'app-main-list':ModeList
      }
    },
    // 查看模组
    {
      path: '/mode',
      name: 'mode',
      components: {
        'app-header':Menu,
        // 'app-main-welcome':,
        'app-main-list':ModeType
      }
    },
    // 上传模组
    {
      path: '/uploadmode',
      name: 'uploadmode',
      components: {
        'app-header':Menu,
        // 'app-main-welcome':,
        'app-main-list':ModeAdd
      }
    },
    // 评论
    {
      path: '/comment',
      name: 'comment',
      components: {
        'app-header':Menu,
        // 'app-main-welcome':,
        'app-main-list':CommentPage
      }
    },
    // 留言
    {
      path: '/message',
      name: 'message',
      components: {
        'app-header':Menu,
        // 'app-main-welcome':,
        'app-main-list':MessagePage
      }
    },
    // 收藏
    {
      path: '/stow',
      name: 'stow',
      components: {
        'app-header':Menu,
        // 'app-main-welcome':,
        'app-main-list':StowPage
      }
    },
  ]
})



export default router
