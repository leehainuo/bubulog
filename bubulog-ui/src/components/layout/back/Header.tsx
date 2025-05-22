"use client";

import { SidebarTrigger } from "@/components/ui/sidebar";
import { DraggableTabs } from "@/components/ui/Tabs/DraggableTabs";
import { Popover, PopoverContent, PopoverTrigger } from "@/components/ui/popover";
import Link from "next/link";
import { DoorOpenIcon } from "lucide-react";

export const Header = () => {
  return (
    <header className="bg-background w-full border-b">
      <div className="flex items-center w-full p-2.5 border-b">
        <SidebarTrigger className="mx-4" />
        <div className="flex-1" />
        <Popover>
          <PopoverTrigger className="flex items-center gap-x-2 mx-4 cursor-pointer">
            <div
              style={{
                backgroundImage:
                  "linear-gradient(75deg, #000000 15%, #fafafa 100%)",
              }}
              className="size-7 rounded-full"
            />
            <span className="font-medium text-[#666]">小布管理员</span>
            <PopoverContent className="rounded-lg w-40">
              <Link
               href="/"
               className="flex items-center gap-x-2 font-medium text-[13px] text-[#666] dark:text-[#a1a1a1] my-4 ml-1.5"
              >
                <DoorOpenIcon size={18} />
                  前往前台
              </Link>
            </PopoverContent>
          </PopoverTrigger>
        </Popover>
      </div>
      <div className="px-4 flex-1 w-full">
        <DraggableTabs />
      </div>
    </header>
  );
};
