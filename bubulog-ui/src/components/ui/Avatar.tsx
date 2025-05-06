import React from "react";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import { LoginForm } from "./LoginForm";

export const Avatar = () => {
  return (
    <section className="mx-4">
      <Dialog>
        <DialogTrigger
          className="rounded-[0.25rem] bg-primary text-primary-foreground hover:bg-primary/90
         py-2 px-4 text-sm font-medium cursor-pointer"
        >
          登录 | 注册
        </DialogTrigger>
        <DialogContent>
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
