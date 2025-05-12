"use client";

import { useRouter, useSearchParams } from "next/navigation";
import { useEffect } from "react";
import { toast } from "sonner";

export const AuthCheck = () => {
  const router = useRouter();
  const searchParams = useSearchParams();

  useEffect(() => {
    const timer = setTimeout(() => {
      const authParam = searchParams.get("auth");
      if (authParam === "required") {
        toast.error("需要登录", {
          description: "请先登录后再访问该页面"
        });
        
        // 清除 URL 参数
        const url = new URL(window.location.href);
        url.searchParams.delete("auth");
        router.replace(url.pathname);
      }
    }, 0);

    return () => clearTimeout(timer);
  }, [searchParams, router]);

  return null;
};