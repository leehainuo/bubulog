import Cookies from "js-cookie";

const TOKEN_KEY = 'Authorization'

// 获取 Token 值
export const getToken = () => {
    return Cookies.get(TOKEN_KEY)
}

// 设置 Token 到 Cookie 中
export const setToken = (
    token: string
) => {
    return Cookies.set(TOKEN_KEY, token)
}

// 删除 Token
export const removeToken = () => {
    return Cookies.remove(TOKEN_KEY)
} 