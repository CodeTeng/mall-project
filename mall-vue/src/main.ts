import { createApp } from 'vue'


import App from './App.vue'
import piniaStore from './store';
import router from './router/permission'
import svgIcon from './components/SvgIcon/index.vue'
// svg-icon的引入
import 'virtual:svg-icons-register'
// 导入应用样式
import './styles/index.scss'
// 导入重置样式
import './styles/reset.scss';
// 导入el样式
import 'element-plus/dist/index.css'
import { ElMessage } from 'element-plus'

// 导入样式
import './assets/css/bootstrap-select.min.css'
import './assets/css/bootstrap.min.css'
import './assets/css/fore_main.css'


const app = createApp(App)
app.use(piniaStore)
app.use(router)
app.use(ElMessage)

// 全局导入svg
app.component('svg-icon', svgIcon)

app.mount('#app')
