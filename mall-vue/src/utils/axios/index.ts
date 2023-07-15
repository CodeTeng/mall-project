import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from 'axios';
import { getToken, clearToken } from '../auth';


// 请求超时时间
axios.defaults.timeout = 5000;

// 表示跨域请求时是否需要使用凭证
axios.defaults.withCredentials = false;

// 请求头的默认token
// axios.defaults.headers.common['Authorization'] = getToken()

const axiosInstance: AxiosInstance = axios.create({
    baseURL: import.meta.env.VITE_BASIC_URL + '',
})
// axiosInstance

// axios 请求拦截器

axiosInstance.interceptors.request.use(
    // @ts-ignore
    (config: AxiosRequestConfig) => {
        const token = getToken()
        if (token != null && token != "") {
            config.headers!.Authorization = getToken()
        }
        return config;
    },
    (error: any) => {
        return Promise.reject(error);
    }
)

// axios 响应拦截器
axiosInstance.interceptors.response.use(
    (response: AxiosResponse) => {
        if (response.data.code === 40100) {
            // 清除信息
            clearToken()
        }
        return response;
    },
    (error: any) => {
        console.log('网络请求异常,原因：' + error)
        return Promise.reject(error)
    }
)

export { axiosInstance };