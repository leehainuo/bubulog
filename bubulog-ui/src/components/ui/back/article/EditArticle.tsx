"use client";

import { ArrowLeftOutlined } from "@ant-design/icons";
import MDEditor from "@uiw/react-md-editor";
import { Button, Card, Form, FormProps, Input, message, Select } from "antd";
import Link from "next/link";
import React from "react";
import { UploadFile } from "../UploadFile";
import { useRouter } from "next/navigation";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { updateArticle } from "@/api/article";

// 下拉列表类型
interface SelectType {
  label: string;
  value: string | number;
}
// 文章数据类型
interface Article {
    articleId: string,
    articleTitle: string,
    articleCover: string,
    articleSummary: string,
    articleContent: string,
    categoryId: string,
    tags: string[]
}

// 定义表单提交的数据类型
type EditFormType = Partial<Article>;
  
interface EditArticleProps {
   articleData?: Article;
   categoryData?: SelectType[];
   tagData?: SelectType[];
}

export const EditArticle = ({ articleData, categoryData, tagData }: EditArticleProps) => {
  const [messageApi, contextHolder] = message.useMessage();
  const router = useRouter();
  const [form] = Form.useForm();
  const queryClient = useQueryClient();

  const updateMutation = useMutation<Article, Error, { articleId: string; data: EditFormType }>({ // 明确指定类型参数
    mutationFn: async ({ articleId, data }) => { // 明确标记为 async
      return await updateArticle({ articleId, data }); // 显式 await 并返回结果
    },
    onSuccess: (data) => { // data 参数现在是 Article 类型
      messageApi.success("更新成功");
      queryClient.invalidateQueries({ queryKey: ["article", data.articleId] }); // 使用返回的 data.articleId
      router.push("/dashboard/article");
    },
    onError: (error) => {
      messageApi.error(`更新失败: ${error.message}`);
    },
  });

  // 在组件加载或 articleData 变化时设置表单初始值
  React.useEffect(() => {
    if (articleData) {
      form.setFieldsValue({
        ...articleData,
        // 如果tagIds需要特殊处理（例如从string[]转换为Select[]的value），在这里进行转换
        tags: articleData.tags, // 假设tagIds直接对应Select的value
      });
    }
  }, [articleData, form]);

  const onFinish: FormProps<EditFormType>["onFinish"] = async (values) => {
    console.log(values);
    if (articleData?.articleId) {
      updateMutation.mutate({
        articleId: articleData.articleId,
        data: values,
      });
    } else {
      messageApi.error("文章ID缺失，无法更新。");
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
        loading={updateMutation.isPending}
      >
        <Form 
          form={form}
          layout="vertical" 
          onFinish={onFinish}
        >
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
              options={(categoryData ?? []).map((item) => ({
                label: item.label,
                value: item.value,
              }))}
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
              options={(tagData ?? []).map((item) => ({
                label: item.label,
                value: item.label,
              }))}
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
            <Button type="primary" htmlType="submit" loading={updateMutation.isPending}>
              保存
            </Button>
          </Form.Item>
        </Form>
      </Card>
    </div>
  );
};
