import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import { DatePicker } from "ant-design-vue";

const app = createApp(App)


app.use(DatePicker);

app.mount('#app')
