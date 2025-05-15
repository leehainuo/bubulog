"use client";

import { SidebarTrigger } from "@/components/ui/sidebar";
import { DraggableTabs } from "@/components/ui/Tabs/DraggableTabs";

export const Header = () => {
  return (
    <header className="bg-background w-full h-auto border-b">
      <div className="flex items-center p-2.5">
        <SidebarTrigger className="mr-4" />
      </div>
      <div className="px-4 flex-1 w-full">
        <DraggableTabs />
      </div>
    </header>
  );
};
