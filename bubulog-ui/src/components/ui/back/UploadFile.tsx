/* eslint-disable @next/next/no-img-element */
import React, { useState, useEffect } from "react";
import { LoadingOutlined, PlusOutlined } from "@ant-design/icons";
import { Flex, message, Upload } from "antd";
import type { GetProp, UploadProps } from "antd";
import { upload } from "@/api/file";

// 定义文件类型
type FileType = Parameters<GetProp<UploadProps, "beforeUpload">>[0];

// 文件上传前处理函数
const beforeUpload = (file: FileType) => {
  // 验证文件类型 (JPG/PNG)
  const isJpgOrPng = file.type === "image/jpeg" || file.type === "image/png";
  if (!isJpgOrPng) {message.error("你只能上传 JPG/PNG 类型的文件!")};
  // 验证文件大小 (<2MB)
  const isLt2M = file.size / 1024 / 1024 < 2;
  if (!isLt2M) {message.error("图片大小超过 2MB!")};
  // 只有同时满足类型和大小才允许上传
  return isJpgOrPng && isLt2M;
};

interface UploadFileProps {
  value?: string;
  onChange?: (url: string) => void;
}

export const UploadFile: React.FC<UploadFileProps> = ({ value, onChange }) => {
  // 控制上传过程中的加载状态
  const [loading, setLoading] = useState(false);
  // 存储上传成功的图片 URL
  const [imageUrl, setImageUrl] = useState<string | undefined>(value);

  useEffect(() => {
    if (value) setImageUrl(value);
  }, [value]);

  // 自定义上传逻辑，调用后端接口
  const customRequest: UploadProps["customRequest"] = async (options) => {
    const { file, onSuccess, onError } = options;
    setLoading(true);
    try {
      const res = await upload(file as File);
      // 获取响应数据中的url
      const url = res.data.data.url;
      setImageUrl(url);
      if (onChange) onChange(url);
      if (onSuccess) onSuccess(res, file);
    } catch (err) {
      if (onError) onError(err as Error);
    } finally {
      setLoading(false);
    }
  };

  // 根据 loading 状态显示不同图标
  const uploadButton = (
    <button style={{ border: 0, background: "none" }} type="button">
      {loading ? <LoadingOutlined /> : <PlusOutlined />}
      <div style={{ marginTop: 8 }}>上传</div>
    </button>
  );

  return (
    <Flex gap="middle" wrap>
      <Upload
        name="file"
        listType="picture-card"
        className="avatar-uploader"
        showUploadList={false}
        customRequest={customRequest}
        beforeUpload={beforeUpload}
      >
        {imageUrl ? (
          <img src={imageUrl} alt="avatar"
           style={{ 
            width: "100%",
            height: "100%",
            objectFit: "cover"
           }}
          />
        ) : (
          uploadButton
        )}
      </Upload>
    </Flex>
  );
}
