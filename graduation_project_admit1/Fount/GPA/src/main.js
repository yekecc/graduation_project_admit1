import { createApp } from 'vue'
// import './style.css'
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import App from './App.vue'
import { DatePicker } from "ant-design-vue";
import Router from './router/router'
import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import Antd from "ant-design-vue"
import 'ant-design-vue/dist/reset.css'
import STable from '@surely-vue/table';
const app = createApp(App)
const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)

app.use(Antd)
app.use(Router)
app.use(ElementPlus)
app.use(pinia)
app.use(DatePicker);
app.use(STable);
app.mount('#app')
