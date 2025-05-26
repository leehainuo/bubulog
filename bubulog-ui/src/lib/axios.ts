import axios from "axios";
import { getToken, removeToken } from "./cookie";

// 创建 Axios 实例
const instance = axios.create({
    baseURL: "/api", // 你的 API 基础 URL
    timeout: 7000, // 请求超时时间
})

// 添加请求拦截器
instance.interceptors.request.use((config) => {
    // 在发送请求之前做些什么
    const token = getToken()
    console.log('统一添加请求头中的 Token:' + token)
    
    // 当 Token 不为空
    if (token) {
        // 添加请求头 key 为 Authorization
        config.headers['Authorization'] = token
    }

    return config;
}, (error) => {
    // 对请求错误做些什么
    return Promise.reject(error)
});

// 添加响应拦截器
instance.interceptors.response.use((response) => {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
    return response
}, (error) => {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    if (error.response.status === 401) {
        // 删除 Token
        removeToken()
        // 跳转到登录页面
        const url = '/auth/login?message=' + encodeURIComponent('登录状态已过期，请重新登录。')
        window.location.href = url
    }
    return Promise.reject(error)
})

// 暴露出去
export default instance;
