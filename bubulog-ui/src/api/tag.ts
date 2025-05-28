import axios from "@/lib/axios";

// 添加分类
export const addTag = (tagName: string) => {
  return axios.post("/admin/tag/add", { tagName });
};

// 删除分类
export const deleteTag = (tagId: string) => {
  return axios.post("/admin/tag/delete", { tagId });
};

// 更新分类
export const updateTag = (tagId: string, tagName: string) => {
  return axios.post("admin/tag/update", { tagId, tagName });
};

// 获取分类列表
export const getTag = (
  tagName: string,
  current?: number,
  Long?: number,
  startDate?: string,
  endDate?: string
) => {
  return axios.post("/admin/tag/query", {
    tagName,
    current,
    Long,
    startDate,
    endDate,
  });
};
