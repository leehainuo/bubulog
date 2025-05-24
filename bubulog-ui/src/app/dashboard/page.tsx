"use client";

import { useSearchParams, useRouter } from "next/navigation";
import { useEffect, useRef } from "react";
import { message } from "antd";

export default function DashboardPage() {
  const searchParams = useSearchParams();
  const router = useRouter();
  const [messageApi, contextHolder] = message.useMessage();
  const messageShown = useRef(false);

  useEffect(() => {
    const message = searchParams.get("message");
    if (message && !messageShown.current) {
      // 清除 URL 参数
      router.replace("/dashboard");
      if (message === "登录成功，欢迎回来") {
        messageApi.success(message);
      } else {
        messageApi.info(message);
      }
      messageShown.current = true;
    }
  }, [searchParams, messageApi, router]);

  return (
    <>
      {contextHolder}
      <div>仪表盘</div>
    </>
  );
}
