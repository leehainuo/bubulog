import axios from "@/lib/axios";
import { FormType } from "@/types/article.type";

// 添加文章
export const addArticle = (data: FormType) => {
  return axios.post("/admin/article/add", {
    ...data,
  })
}

// 更新文章
export const updateArticle = (
  articleId: string,
  data: FormType
) => {
  return axios.post("/admin/article/update", {
    articleId,
    ...data
  })
}

// 获取文章详情
export const getArticleDetail = (articleId: string) => {
  return axios.post("/admin/article/detail", {
    articleId,
  });
};

// 查询文章
export const getArticle = (
  articleTitle: string,
  current?: number,
  size?: number,
  startDate?: string,
  endDate?: string
) => {
  return axios.post("/admin/article/query", {
    articleTitle,
    current,
    size,
    startDate,
    endDate,
  });
};

// 获取首页文章分页数据
export const getArticleList = (
  current: number,
  size: number,
) => {
  return axios.post("/article/list", {
    current,
    size
  })
}
