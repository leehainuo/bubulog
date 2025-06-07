"use client";
import React from "react";
import { Button, Form, FormProps, Input, message } from "antd";
import { UploadFile } from "../UploadFile";
import { updateSettingBlog } from "@/api/setting";
import { FormType } from "@/types/setting-blog.type";

const { TextArea } = Input;

export const SettingBlogForm = () => {
  const [messageApi, contextHolder] = message.useMessage();

  // 处理表单提交成功
  const onFinish: FormProps<FormType>["onFinish"] = async (values) => {
    console.log("Success:", values);
    const res = await updateSettingBlog(values);
    if (res.status === 200) {
      messageApi.success("设置成功");
    } else {
      messageApi.error("设置失败");
    }
  };
  // 处理表单提交失败
  const onFinishFailed: FormProps<FormType>["onFinishFailed"] = (err) => {
    console.log("Failed:", err);
    messageApi.error("未知错误");
  };

  return (
    <>
      {contextHolder}
      <Form
        labelCol={{ span: 6 }}
        wrapperCol={{ span: 14 }}
        layout="horizontal"
        disabled={false}
        style={{ maxWidth: 2000 }}
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
      >
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-4">
          <div>
            <Form.Item label="博客名称" name="name">
              <Input />
            </Form.Item>
            <Form.Item label="博客作者" name="author">
              <Input />
            </Form.Item>
            <Form.Item label="作者头像" name="avatar" valuePropName="value">
              <UploadFile />
            </Form.Item>
            <Form.Item label="博客介绍" name="introduction">
              <TextArea rows={6} />
            </Form.Item>
          </div>
          <div>
            <Form.Item label="稀土掘金" name="juejinHomePage">
              <Input />
            </Form.Item>
            <Form.Item label="Github" name="githubHomePage">
              <Input />
            </Form.Item>
            <Form.Item label="QQ 号" name="qqAccount">
              <Input />
            </Form.Item>
            <Form.Item label="微信号" name="wechatAccount">
              <Input />
            </Form.Item>
          </div>
          <Form.Item style={{ marginLeft: 50 }}>
            <Button type="primary" htmlType="submit">
              保存
            </Button>
          </Form.Item>
        </div>
      </Form>
    </>
  );
};
