"use client";
import React from "react";
import { Button, Form, FormProps, Input } from "antd";
import { UploadFile } from "../UploadFile";

const { TextArea } = Input;

interface FormType {
    logo?: string,
    name?: string,
    author?: string,
    introuduction?: string,
    avatar?: string,
    githubHomePage?: string,
    juejinHomePage?: string,
    wechatAccount?: string,
    qqAccount?: string
}

// 处理表单提交成功
const onFinish: FormProps<FormType>['onFinish'] = (values) => {
    console.log('Success:', values)
}
// 处理表单提交失败
const onFinishFailed: FormProps<FormType>['onFinishFailed'] = (err) => {
    console.log('Failed:', err);
};

export const SettingBlogForm = () => {
  return (
    <>
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
            <Form.Item
             label="博客名称"
             name="name" 
            >
              <Input />
            </Form.Item>
            <Form.Item
             label="博客作者"
             name="author" 
            >
              <Input />
            </Form.Item>
            <Form.Item
              label="作者头像"
              name="avatar"
              valuePropName="value"
            >
              <UploadFile />
            </Form.Item>
            <Form.Item
             label="博客介绍"
             name="introduction"
            >
              <TextArea rows={6} />
            </Form.Item>
          </div>
          <div>
            <Form.Item
             label="稀土掘金"
             name="juejinHomePage"
            >
              <Input />
            </Form.Item>
            <Form.Item
             label="Github"
             name="githubHomePage"
            >
              <Input />
            </Form.Item>
            <Form.Item
             label="QQ 号"
             name="qqAccount" 
            >
              <Input />
            </Form.Item>
            <Form.Item
             label="微信号"
             name="wechatAccount" 
            >
              <Input />
            </Form.Item>
          </div>
          <Form.Item style={{ marginLeft: 50 }}>
            <Button type="primary" htmlType="submit">保存</Button>
          </Form.Item>
        </div>
      </Form>
    </>
  );
};
