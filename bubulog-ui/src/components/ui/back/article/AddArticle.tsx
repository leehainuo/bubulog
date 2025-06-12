"use client";

import { ArrowLeftOutlined } from "@ant-design/icons";
import MDEditor from "@uiw/react-md-editor";
import { Button, Card, Form, FormProps, Input, message, Select } from "antd";
import Link from "next/link";
import React from "react";
import { UploadFile } from "../UploadFile";
import { addArticle } from "@/api/article";
import { FormType } from "@/types/article.type";
import { useRouter } from "next/navigation";

// 下拉列表类型
interface SelectOption {
  label: string;
  value: string | number;
}

interface AddArticleProps {
  categoryData?: SelectOption[];
  tagData?: SelectOption[];
  onSuccess?: () => void;
}

export const AddArticle = ({ categoryData, tagData, onSuccess }: AddArticleProps) => {
  const [messageApi, contextHolder] = message.useMessage();
  const router = useRouter();
  const onFinish: FormProps<FormType>["onFinish"] = async (values) => {
    console.log(values);
    const res = await addArticle(values);
    if (res.status != 200) {
      messageApi.error("设置失败");
    } else {
      onSuccess?.();
      router.push("/dashboard/article");
    }
  };

  return (
    <div data-color-mode="light">
      {contextHolder}
      <Link href="/dashboard/article">
        <ArrowLeftOutlined />
      </Link>
      <Card
        title="创建文章"
        style={{
          border: "none",
        }}
      >
        <Form layout="vertical" onFinish={onFinish}>
          <Form.Item
            name="articleTitle"
            label="文章标题"
            rules={[{ required: true, message: "请输入文章标题" }]}
          >
            <Input placeholder="请输入文章标题" />
          </Form.Item>

          <Form.Item
            name="articleCover"
            label="文章封面"
            rules={[{ required: true, message: "请输入文章封面URL" }]}
          >
            <UploadFile />
          </Form.Item>

          <Form.Item
            name="articleSummary"
            label="文章摘要"
            rules={[{ required: true, message: "请输入文章摘要" }]}
          >
            <Input.TextArea
              placeholder="请输入文章摘要"
              autoSize={{ minRows: 3, maxRows: 6 }}
            />
          </Form.Item>

          <Form.Item
            name="categoryId"
            label="文章分类"
            rules={[{ required: true, message: "请选择文章分类" }]}
          >
            <Select 
              placeholder="请选择文章分类"
              options={Array.isArray(categoryData) ? categoryData.map(item => ({
                label: item.label,
                value: item.value,
              })) : []}
            />
          </Form.Item>

          <Form.Item
            name="tags"
            label="文章标签"
            rules={[{ required: true, message: "请选择文章标签" }]}
          >
            <Select
              mode="multiple"
              placeholder="请选择文章标签"
              options={Array.isArray(tagData) ? tagData.map((item) => ({
                label: item.label,
                value: item.label,
              })) : []}
            />
          </Form.Item>

          <Form.Item
            name="articleContent"
            label="文章内容"
            rules={[{ required: true, message: "请输入文章内容" }]}
          >
            <MDEditor height={500} preview="edit" />
          </Form.Item>

          <Form.Item>
            <Button type="primary" htmlType="submit">
              保存
            </Button>
          </Form.Item>
        </Form>
      </Card>
    </div>
  );
};
