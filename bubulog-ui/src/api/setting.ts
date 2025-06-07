import axios from "@/lib/axios"

// 更新博客设置
export const updateSettingBlog = (
    logo: string,
    name: string,
    author: string,
    introuduction: string,
    avatar: string,
    githubHomePage: string,
    juejinHomePage: string,
    wechatAccount: string,
    qqAccount: string
) => {
    return axios.post("/admin/blog/settings/update", {
        logo, name, author,
        introuduction, avatar,
        githubHomePage, juejinHomePage,
        wechatAccount, qqAccount
    });
}