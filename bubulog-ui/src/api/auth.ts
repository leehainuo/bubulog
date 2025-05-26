import axios from "@/lib/axios"

// 登录接口
export const login = (
    username: string | undefined,
    password: string | undefined
) => {
    return axios.post("/auth/login", {username, password})
}