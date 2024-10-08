import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import { DatePicker } from "ant-design-vue";
import Router from './router/router'
import { createPinia } from 'pinia'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/reset.css'

const app = createApp(App)
const pinia = createPinia()
app.use(Antd)
app.use(Router)
app.use(pinia)
app.use(DatePicker);

app.mount('#app')
