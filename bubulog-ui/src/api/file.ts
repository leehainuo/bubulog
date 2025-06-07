import axios from "@/lib/axios"

// 上传文件
export const upload = (file: File) => {
    const formData = new FormData();
    formData.append("file", file); // 这里的 "file" 必须和后端参数名一致
    return axios.post("/admin/file/upload", formData, {
        headers: {
            "Content-Type": "multipart/form-data"
        }
    });
} 