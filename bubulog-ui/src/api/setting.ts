import axios from "@/lib/axios";
import { FormType } from "@/types/setting-blog.type";

// 更新博客设置
export const updateSettingBlog = (data: FormType) => {
  return axios.post("/admin/blog/settings/update", {
    ...data,
  });
};
