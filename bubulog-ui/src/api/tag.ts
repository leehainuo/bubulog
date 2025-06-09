import axios from "@/lib/axios";

// 添加标签
export const addTag = (tagName: string) => {
  return axios.post("/admin/tag/add", { tagName });
};

// 删除标签
export const deleteTag = (tagId: string) => {
  return axios.post("/admin/tag/delete", { tagId });
};

// 更新标签
export const updateTag = (tagId: string, tagName: string) => {
  return axios.post("admin/tag/update", { tagId, tagName });
};

// 获取标签列表
export const getTag = (
  tagName: string,
  current?: number,
  size?: number,
  startDate?: string,
  endDate?: string
) => {
  return axios.post("/admin/tag/query", {
    tagName,
    current,
    size,
    startDate,
    endDate,
  });
};

// 获取所有标签
export const getAllTag = () => {
  return axios.post("/admin/tag/select/list")
}
