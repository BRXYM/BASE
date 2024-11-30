// src/stores/view.ts

import { defineStore } from 'pinia'

export const useViewStore = defineStore('view', () => {
  // 移除 islogin 状态，使用 userStore 中的 isLogin 计算属性
  return {}
})
