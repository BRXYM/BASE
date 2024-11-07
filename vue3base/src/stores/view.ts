import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useViewStore = defineStore('view', () => {
  const islogin = ref(false)

  return { islogin }
})
