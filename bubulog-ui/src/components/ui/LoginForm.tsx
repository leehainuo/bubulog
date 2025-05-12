"use client";

import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { Button } from "@/components/ui/button";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { login } from "@/api/auth";
import { toast } from "sonner";
import { useRouter } from "next/navigation";

const formSchema = z.object({
  username: z.string().min(2).max(50),
  password: z.string().min(4).max(50),
});

export const LoginForm = () => {
  const router = useRouter();

  // 定义表单结构
  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      username: "test",
      password: "test",
    },
  });
  // 表单处理函数
  const onSubmit = async (values: z.infer<typeof formSchema>) => {
    const res = await login(values.username, values.password);
    // 请求不成功
    if (!res.ok) {
      toast.message("登录失败", {
        description: "用户名或密码不正确",
      });
    } else {
      toast.message("登录成功", {
        description: "欢迎进入布布后台",
      });
      router.push("/dashboard")
    }
  };
  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-4">
        <FormField
          control={form.control}
          name="username"
          render={({ field }) => (
            <FormItem>
              <FormControl>
                <Input placeholder="请输入用户名" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />
        <FormField
          control={form.control}
          name="password"
          render={({ field }) => (
            <FormItem>
              <FormControl>
                <Input placeholder="请输入密码" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />
        <Button className="rounded-[0.25rem] w-full py-6 cursor-pointer mt-4">
          登录
        </Button>
      </form>
    </Form>
  );
};
