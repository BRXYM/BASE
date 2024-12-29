import { createApp } from 'vue'
import { createPinia } from 'pinia'
import axios from 'axios'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import App from './App.vue'
import router from './router/router'

const app = createApp(App)
const pinia = createPinia()

app.use(ElementPlus)
app.use(router)
app.use(pinia)
app.config.globalProperties.$axios = axios

app.mount('#app')
