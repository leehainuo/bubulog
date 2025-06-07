"use client";

import Image from "next/image";
import { LoginForm } from "@/components/ui/auth/LoginForm";
import { useSearchParams, useRouter } from "next/navigation";
import { useEffect, useRef, Suspense } from "react";
import { message } from "antd";
import { motion } from "motion/react";
import Link from "next/link";

const LoginContent = () => {
  const searchParams = useSearchParams();
  const router = useRouter();
  const [messageApi, contextHolder] = message.useMessage();
  const messageShown = useRef(false);

  useEffect(() => {
    const message = searchParams.get("message");
    if (message && !messageShown.current) {
      // 清除 URL 参数
      router.replace("/auth/login");
      messageApi.info(message);
    }
    messageShown.current = true;
  }, [searchParams, messageApi, router]);

  return (
    <>
      {contextHolder}
      <section className="w-full h-screen flex flex-col lg:flex-row">
        {/* Left */}
        <div className="bg-[#001529] h-[35%] lg:flex-1 lg:h-full flex flex-col justify-center items-center font-bold text-white gap-y-4">
          <h1 className="text-4xl">布布博客</h1>
          <div className="relative size-32 lg:size-72">
            <Image
              src={"/Banner.webp"}
              alt="Banner"
              height={288}
              width={288}
              fill={false}
            />
          </div>
          <span>Next.js + SpringBoot 3 + MyBatis-Plus</span>
          <span className="hidden lg:block">SEO友好的轻量开源博客</span>
        </div>
        {/* Right */}
        <motion.div
          className="flex-1 flex flex-col justify-center items-center relative"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ duration: 0.8, ease: "easeOut", delay: 0.2 }}
        >
          {/* 右区内容 */}
          <div className="relative z-10 w-full flex flex-col items-center">
            <h1 className="text-3xl font-bold">欢迎回来</h1>
            <div className="flex items-center gap-x-2 my-2">
              <div className="w-14 border-y" />
              <p className="text-[#e5e7eb] text-sm">账号密码登录</p>
              <div className="w-14 border-y" />
            </div>
            <LoginForm />
            <div className="flex items-center gap-x-2 my-2">
              <div className="w-14 border-y" />
              <Link href="/" className="text-[#e5e7eb] text-sm">返回首页</Link>
              <div className="w-14 border-y" />
            </div>
          </div>
        </motion.div>
      </section>
    </>
  );
}

export default function Page() {
  return (
    <Suspense fallback={<div>加载中...</div>}>
      <LoginContent />
    </Suspense>
  );
}

