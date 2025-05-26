import React from "react";
import { Layout, Flex, Popover, Avatar, Button, theme } from "antd";
import {
  MenuFoldOutlined,
  MenuUnfoldOutlined,
  RollbackOutlined,
  UserDeleteOutlined,
} from "@ant-design/icons";
import Link from "next/link";
import { removeToken } from "@/lib/cookie";
import { useRouter } from "next/navigation";

interface HeaderProps {
  collapsed: boolean;
  setCollapsed: (state: boolean) => void;
}

// 用户Menu
const UserMenu = () => {

  const router = useRouter()

  return (
    <div className="flex flex-col gap-y-4 py-3 px-4">
      <Link href="/" className="flex gap-x-3">
        <RollbackOutlined /> <p className="font-medium">返回前台</p>
      </Link>
      <div
       onClick={() => {
        removeToken()
        router.push("/")
       }}
       className="flex gap-x-3 text-red-500 hover:text-red-400 transition-all duration-150 cursor-pointer"
      >
        <UserDeleteOutlined /> <p className="font-medium">退出登录</p>
      </div>
    </div>
  );
};

export const Header = ({ collapsed, setCollapsed }: HeaderProps) => {
  const {
    token: { colorBgContainer },
  } = theme.useToken();

  return (
    <Layout.Header style={{ padding: 0, background: colorBgContainer }}>
      <Flex align="center">
        <Button
          type="text"
          icon={collapsed ? <MenuUnfoldOutlined /> : <MenuFoldOutlined />}
          onClick={() => setCollapsed(!collapsed)}
          style={{
            fontSize: "16px",
            width: 64,
            height: 64,
          }}
        />
        <div className="flex-1" />
        <Popover content={<UserMenu />}>
          <Flex style={{ marginRight: 40 }} align="center" gap={10}>
            <Avatar
              size={40}
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQE2e6JL_PUDVEgS0U5jSNMy-x0_EdLYEI9Tg&s"
            />
            <h1 className="font-bold">管理熊</h1>
          </Flex>
        </Popover>
      </Flex>
    </Layout.Header>
  );
};
