import axios from "@/lib/axios"

// 添加分类
export const addCategory = (
    categoryName: string,
) => {
    return axios.post(
        "/admin/category/add",
        { categoryName }
    )
}

// 删除分类
export const deleteCategory = (
    categoryId: string
) => {
    return axios.post(
        "/admin/category/delete",
        { categoryId }
    )
}

// 更新分类
export const updateCategory = (
    categoryId: string,
    categoryName: string
) => {
    return axios.post(
        "admin/category/update",
        { categoryId, categoryName }
    )
}

// 获取分类列表
export const getCategory = (
    categoryName: string,
    current?: number,
    Long?: number,
    startDate?: string,
    endDate?: string,
) => {
    return axios.post(
        "/admin/category/query",
        { categoryName, current, Long, startDate, endDate }
    )
}