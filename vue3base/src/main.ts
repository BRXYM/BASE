import { createApp } from 'vue'
import { createPinia } from 'pinia'
import axios from 'axios'
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import 'element-plus/dist/index.css'

import App from './App.vue'
import router from './router/router'

const app = createApp(App)

app.use(ElementPlus, {
    locale: zhCn,
})
app.use(createPinia())
app.use(router)
app.config.globalProperties.$axios = axios

app.mount('#app')
