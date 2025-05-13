"use client";

import React, { useEffect } from "react";
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import { LoginForm } from "./LoginForm";
import Link from "next/link";
import { DoorOpenIcon } from "lucide-react";
import { LogoutButton } from "./LogoutButton";
import { usePathname } from "next/navigation";
import { useAuthStore } from "@/store/auth";

export const Avatar = () => {
  const { isLoggedIn, setIsLoggedIn } = useAuthStore();
  const pathname = usePathname();

  useEffect(() => {
    const checkLoginStatus = async () => {
      const token = document.cookie
        .split("; ")
        .find((row) => row.startsWith("access_token="))
        ?.split("=")[1];
      setIsLoggedIn(!!token);
    };

    checkLoginStatus();
  }, [pathname, setIsLoggedIn]); // 当路由变化时重新检查登录状态

  // 登录界面
  if (isLoggedIn) {
    return (
      <div className="flex justify-center mx-4 w-[100.5px]">
        <Popover>
          <PopoverTrigger>
            <div
              style={{
                backgroundImage:
                  "linear-gradient(75deg, #000000 15%, #fafafa 100%)",
              }}
              className="size-9 rounded-full cursor-pointer"
            />
            <PopoverContent className="rounded-lg">
              <div className="flex items-center gap-x-4 font-medium">
                <div
                  style={{
                    backgroundImage:
                      "linear-gradient(75deg, #000000 15%, #fafafa 100%)",
                  }}
                  className="size-9 rounded-full cursor-pointer"
                />
                <h1>小布</h1>
              </div>
              <Link
               href="/dashboard"
               className="flex items-center gap-x-2 font-medium text-[13px] text-[#666] dark:text-[#a1a1a1] my-4 ml-1.5"
              >
                <DoorOpenIcon size={18} />
                  前往后台
              </Link>
              <LogoutButton />
            </PopoverContent>
          </PopoverTrigger>
        </Popover>
      </div>
    );
  }

  // 未登录界面
  return (
    <section className="mx-4 w-[100.5px]">
      <Dialog>
        <DialogTrigger
          className="rounded-lg bg-primary text-primary-foreground hover:bg-primary/90
         py-2 px-4 text-sm font-medium cursor-pointer"
        >
          登录 | 注册
        </DialogTrigger>
        <DialogContent className="p-10">
          <DialogHeader>
            <DialogTitle className="font-normal flex flex-col text-2xl">
              欢迎来到布布博客
              <div className="h-[1px] w-full border my-4" />
            </DialogTitle>
          </DialogHeader>

          <LoginForm />
        </DialogContent>
      </Dialog>
    </section>
  );
};
