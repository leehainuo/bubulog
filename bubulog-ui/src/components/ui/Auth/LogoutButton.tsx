'use client';

import { LogOutIcon } from "lucide-react";
import { useRouter } from "next/navigation";
import { toast } from "sonner";

export const LogoutButton = () => {
  const router = useRouter();

  const handleLogout = () => {
    document.cookie = "access_token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    router.push('/');
    router.refresh();
    toast("账户已退出")
  };

  return (
    <button
      onClick={handleLogout}
      className="flex items-center gap-x-2 font-medium text-[13px] text-[#666] dark:text-[#a1a1a1] my-4 cursor-pointer ml-1.5"
    >
      <LogOutIcon size={18} />
      退出登录
    </button>
  );
}; 