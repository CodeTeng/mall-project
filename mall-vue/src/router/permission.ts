import router from './index'
import {clearToken, getToken, isLogin} from '../utils/auth'

import NProgress from "nprogress"; // 导入 nprogress模块
import "nprogress/nprogress.css"; // 导入样式，否则看不到效果
NProgress.configure({ showSpinner: false }); // 显示右上角螺旋加载提示

// 白名单
const whileList = ['/login', '/register', "/mall", "/resetPwd"]

router.beforeEach((to: any, from: any, next: any) => {
    NProgress.start();
    //  白名单 有值 或者登陆过存储了token信息可以跳转 否则就去登录页面
    const token = getToken();
    if(!isLogin()){
        clearToken()
    }
    if (whileList.includes(to.path) || isLogin()) {
        next()
    } else {
        ElMessage.warning("用户未登录商城，请先登录")

        next({
            path: '/mall'
        })
    }

})

router.afterEach((to: any) => {
    NProgress.done();
});

export default router;
