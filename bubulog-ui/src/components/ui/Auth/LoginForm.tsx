/* eslint-disable @typescript-eslint/no-explicit-any */
"use client";
import React, { useRef } from "react";
import type { FormProps } from "antd";
import { Button, Form, Input, message } from "antd";
import { login } from "@/api/auth";
import { useRouter } from "next/navigation";
import { setToken } from "@/lib/cookie";
import type { InputRef } from "antd";

type FieldType = {
  username?: string;
  password?: string;
  remember?: string;
};

export const LoginForm = () => {
  const router = useRouter();
  const [messageApi, contextHolder] = message.useMessage();
  const usernameRef = useRef<InputRef>(null);
  const passwordRef = useRef<InputRef>(null);
  const formRef = useRef<any>(null);

  const onFinish: FormProps<FieldType>["onFinish"] = (values) => {
    login(values.username, values.password)
      .then((res) => {
        // 获取 Token
        const token = res.data.data.token;
        // 将 Token 存入cookie
        setToken(token)
        // 跳转到仪表盘并添加欢迎消息
        router.push("/dashboard?message=登录成功，欢迎回来");
      })
      .catch((err) => {
        console.log(err);
        messageApi.error("账号或密码错误");
      });
  };

  // 键盘事件处理
  const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>, field: "username" | "password") => {
    if (e.key === "ArrowDown" || (e.key === "ArrowUp" && field === "password")) {
      e.preventDefault();
      if (field === "username") {
        passwordRef.current?.focus();
      } else {
        usernameRef.current?.focus();
      }
    }
    if (e.key === "Enter") {
      formRef.current?.submit();
    }
  };

  return (
    <>
      {contextHolder}
      <Form
        ref={formRef}
        name="basic"
        wrapperCol={{ span: 28 }}
        style={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          justifyContent: "center",
        }}
        initialValues={{
          username: "test",
          remember: true
        }}
        onFinish={onFinish}
        autoComplete="off"
      >
        <Form.Item<FieldType>
          name="username"
          style={{ width: 280 }}
          rules={[{ required: true, message: "用户名不能为空！" }]}
        >
          <Input
            ref={usernameRef}
            placeholder="请输入用户名"
            onKeyDown={(e) => handleKeyDown(e, "username")}
          />
        </Form.Item>

        <Form.Item<FieldType>
          name="password"
          style={{ width: 280 }}
          rules={[{ required: true, message: "请输入密码！" }]}
        >
          <Input.Password
            ref={passwordRef}
            placeholder="请输入密码 游客: test"
            onKeyDown={(e) => handleKeyDown(e, "password")}
          />
        </Form.Item>

        <Form.Item label={null}>
          <Button type="primary" htmlType="submit" style={{ width: 280 }}>
            登录
          </Button>
        </Form.Item>
      </Form>
    </>
  );
};
