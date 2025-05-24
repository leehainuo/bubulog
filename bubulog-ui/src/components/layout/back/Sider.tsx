import React from "react";
import {
  DashboardOutlined,
  FileTextOutlined,
  FolderOutlined,
  TagOutlined,
  SettingOutlined,
} from "@ant-design/icons";
import { Layout, Menu } from "antd";
import { usePathname, useRouter } from "next/navigation";

interface SiderProps {
  collapsed: boolean;
}

const menuItems = [
  {
    key: "/dashboard",
    icon: <DashboardOutlined />,
    label: "仪表盘",
  },
  {
    key: "/dashboard/article",
    icon: <FileTextOutlined />,
    label: "文章管理",
  },
  {
    key: "/dashboard/category",
    icon: <FolderOutlined />,
    label: "分类管理",
  },
  {
    key: "/dashboard/tag",
    icon: <TagOutlined />,
    label: "标签管理",
  },
  {
    key: "/dashboard/setting",
    icon: <SettingOutlined />,
    label: "博客设置",
  },
];

export const Sider = ({ collapsed }: SiderProps) => {
  const router = useRouter();
  const pathname = usePathname();

  const handleMenuClick = ({ key }: { key: string }) => {
    router.push(key);
  };

  return (
    <Layout.Sider trigger={null} collapsible collapsed={collapsed}>
      <div className="h-20 flex items-center justify-center">
        <p
          className={`text-white text-xl font-bold transition-all
         ${
           collapsed === true
             ? "opacity-0 duration-0"
             : "opacity-100 duration-500"
         }
         `}
        >
          布布管理后台
        </p>
      </div>
      <Menu
        theme="dark"
        mode="inline"
        selectedKeys={[pathname]}
        onClick={handleMenuClick}
        items={menuItems}
      />
    </Layout.Sider>
  );
};
