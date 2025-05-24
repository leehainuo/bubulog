"use client";

import { LoginForm } from "@/components/ui/auth/LoginForm";
import { useSearchParams, useRouter } from "next/navigation";
import { useEffect } from "react";
import { message } from "antd";

const LoginPage = () => {
  const searchParams = useSearchParams();
  const router = useRouter();
  const [messageApi, contextHolder] = message.useMessage();

  useEffect(() => {
    const message = searchParams.get("message");
    if (message) {
      // 清除 URL 参数
      router.replace("/auth/login");
      messageApi.info(message);
    }
  }, [searchParams, messageApi, router]);

  return (
    <>
      {contextHolder}
      <section className="w-full h-screen flex flex-col lg:flex-row">
        {/* Left */}
        <div className="bg-[#001529] h-[30%] lg:flex-1 lg:h-full flex flex-col justify-center items-center font-bold text-white gap-y-4">
          <h1 className="text-4xl">布布博客</h1>
          <span className="">Next.js + SpringBoot 3 + MyBatis-Plus</span>
        </div>
        {/* Right */}
        <div className="flex-1 flex flex-col justify-center items-center">
          <h1 className="text-3xl font-bold">欢迎回来</h1>
          <div className="flex items-center gap-x-2 my-2">
            <div className="w-14 border-y" />
            <p className="text-[#e5e7eb] text-sm">账号密码登录</p>
            <div className="w-14 border-y" />
          </div>
          <LoginForm />
        </div>
      </section>
    </>
  );
};

export default LoginPage;
