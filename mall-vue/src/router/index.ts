//引入路由对象
import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

//路由数组的类型 RouteRecordRaw
// 定义一些路由
// 每个路由都需要映射到一个组件。
const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: '',
        component: () => import('../components/Layout/index.vue'),
        // 重定向
        redirect: '/mall',
        children: [
            // 首页
            {
                path: 'mall',
                name: 'Mall',
                component: () => import('../views/Mall/index.vue')
            },
            // 购物车
            {
                path: 'cart',
                name: 'Cart',
                component: () => import('../views/Cart/index.vue')
            },
            // 用户个人信息
            {
                path: 'userInfo',
                name: 'UserInfo',
                component: () => import('../views/UserInfo/index.vue')
            },
            // 用户订单信息
            {
                path: 'order',
                name: 'Order',
                component: () => import('../views/Order/index.vue')
            },
            // 商品信息
            {
                path: 'product',
                name: 'Product',
                component: () => import('../views/Product/index.vue')
            },
        ]
    },
    {
        path: '/register',
        name: 'Register',
        component: () => import('../views/Register/index.vue')
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('../views/Login/index.vue')
    },
]



const router = createRouter({
    history: createWebHistory(),
    routes
})

// 导出router
export default router