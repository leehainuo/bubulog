import React from "react";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import { cookies } from "next/headers";
import { LoginForm } from "./LoginForm";

export const Avatar = async () => {
  const cookieStore = await cookies();
  const token = cookieStore.get("access_token")?.value;

  if (token) {
    return <h1>已登录</h1>;
  }

  return (
    <section className="mx-4">
      <Dialog>
        <DialogTrigger
          className="rounded-[0.25rem] bg-primary text-primary-foreground hover:bg-primary/90
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
