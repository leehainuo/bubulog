"use client";

import { LogOutIcon } from "lucide-react";
import { logout } from "@/api/auth";
import { useAuthStore } from "@/store/auth";
import { toast } from "sonner";

export const LogoutButton = () => {
  const { setIsLoggedIn } = useAuthStore();

  const handleLogout = async () => {
    const res = await logout();
    if (res.ok) {
      setIsLoggedIn(false);
      toast.message("退出成功", {
        description: "期待您的下次光临",
      });
    }
  };

  return (
    <div
      onClick={handleLogout}
      className="flex items-center gap-x-2 font-medium text-[13px] text-[#666] dark:text-[#a1a1a1] my-4 cursor-pointer ml-1.5"
    >
      <LogOutIcon size={18} />
      退出登录
    </div>
  );
}; 