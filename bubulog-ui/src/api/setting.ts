import axios from "@/lib/axios";
import { FormType } from "@/types/setting-blog.type";

// 更新博客设置
export const updateSettingBlog = (req: FormType) => {
  return axios.post("/admin/blog/settings/update", {
    ...req,
  });
};
