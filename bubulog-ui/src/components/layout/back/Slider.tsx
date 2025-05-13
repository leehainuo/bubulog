// "use client";

import { FileText, Home, Inbox, Settings, Tag } from "lucide-react";
import { headers } from "next/headers";
import {
  Sidebar,
  SidebarContent,
  SidebarGroup,
  SidebarGroupContent,
  SidebarGroupLabel,
  SidebarMenu,
  SidebarMenuButton,
  SidebarMenuItem,
} from "@/components/ui/sidebar";

// Menu items.
const items = [
  {
    name: "仪表盘",
    path: "/dashboard",
    icon: Home,
  },
  {
    name: "文章管理",
    path: "/dashboard/article",
    icon: FileText,
  },
  {
    name: "分类管理",
    path: "/dashboard/category",
    icon: Inbox,
  },
  {
    name: "标签管理",
    path: "/dashboard/tag",
    icon: Tag,
  },
  {
    name: "博客设置",
    path: "/dashboard/setting",
    icon: Settings,
  },
];

export const Slider = async () => {
  const headersList = await headers();
  const pathname = headersList.get("x-pathname") || "/dashboard";

  return (
    <Sidebar>
      <SidebarContent>
        <SidebarGroup>
          <SidebarGroupLabel className="bg-foreground h-24 flex justify-center mb-4 rounded-lg">

          </SidebarGroupLabel>
          <SidebarGroupContent>
            <SidebarMenu>
              {items.map((item) => (
                <SidebarMenuItem key={item.name} className="h-12">
                  <SidebarMenuButton
                    asChild
                    className={`h-12 ${
                      pathname === item.path
                        ? "text-primary"
                        : "hover:text-primary text-[#666] dark:text-[#a1a1a1]"
                    }`}
                  >
                    <a href={item.path}>
                      <item.icon />
                      <span>{item.name}</span>
                    </a>
                  </SidebarMenuButton>
                </SidebarMenuItem>
              ))}
            </SidebarMenu>
          </SidebarGroupContent>
        </SidebarGroup>
      </SidebarContent>
    </Sidebar>
  );
};
