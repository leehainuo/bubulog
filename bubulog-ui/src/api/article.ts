import axios from "@/lib/axios";
import { FormType } from "@/types/article.type";

// 添加文章
export const addArticle = (req: FormType) => {
  return axios.post("/admin/article/add", {
    ...req,
  })
}

// 更新文章
export const updateArticle = () => {
  
}

// 获取文章详情
export const getArticleDetail = (articleId: string) => {
  return axios.post("/admin/article/detail", {
    articleId,
  });
};

// 获取文章列表
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
