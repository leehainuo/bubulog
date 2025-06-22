"use client";

import React, { useState, useEffect } from "react";
import { Layout, Menu, Input, Avatar, Button, Popover, Flex } from "antd";
import { SearchOutlined, SwapRightOutlined, UserDeleteOutlined, MenuOutlined } from "@ant-design/icons";
import Link from "next/link";
import { getToken, removeToken } from "@/lib/cookie";
import { usePathname, useRouter } from "next/navigation";

const { Header: AntHeader } = Layout;

const UserMenu = () => {
  const router = useRouter();

  return (
    <div className="flex flex-col gap-y-4 py-3 px-4">
      <Link href="/dashboard" className="flex gap-x-3">
        <SwapRightOutlined /> <p className="font-medium">前往后台</p>
      </Link>
      <div
        onClick={() => {
          removeToken();
          router.push("/");
          window.location.reload();
        }}
        className="flex gap-x-3 text-red-500 hover:text-red-400 transition-all duration-150 cursor-pointer"
      >
        <UserDeleteOutlined /> <p className="font-medium">退出登录</p>
      </div>
    </div>
  );
};

export const Header = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [menuMode, setMenuMode] = useState<"horizontal" | "inline">("horizontal");
  const [menuOpen, setMenuOpen] = useState(false);
  const pathname = usePathname();

  useEffect(() => {
    const token = getToken();
    setIsLoggedIn(!!token);

    const handleResize = () => {
      if (window.innerWidth < 768) { // 假设 768px 是您想要的断点
        setMenuMode("inline");
      } else {
        setMenuMode("horizontal");
      }
    };

    handleResize(); // 初始设置一次
    window.addEventListener("resize", handleResize);

    return () => window.removeEventListener("resize", handleResize);
  }, []);

  const getSelectedKey = () => {
    if (pathname === "/") {
      return "1";
    } else if (pathname.startsWith("/categories")) {
      return "2";
    } else if (pathname.startsWith("/tags")) {
      return "3";
    } else if (pathname.startsWith("/archives")) {
      return "4";
    }
    return "1";
  };

  return (
    <AntHeader
      className="sticky top-0 z-50"
      style={{
        display: "flex",
        alignItems: "center",
        justifyContent: "space-between",
        padding: "0 24px",
        backgroundColor: "#fff",
        borderBottom: "1px solid #f0f0f0",
      }}
    >
      <div className="flex justify-center items-center">

        {menuMode === "horizontal" ? (
          <Menu
            key="horizontal-menu"
            mode="horizontal"
            selectedKeys={[getSelectedKey()]}
            style={{ borderRight: 0 }}
            items={[
              {
                key: "1",
                label: <Link href="/">首页</Link>,
              },
              {
                key: "2",
                label: <Link href="/categories">分类</Link>,
              },
              {
                key: "3",
                label: <Link href="/tags">标签</Link>,
              },
              {
                key: "4",
                label: <Link href="/archives">归档</Link>,
              },
            ]}
          />
        ) : (
          <Popover
            key="inline-menu-popover"
            content={
              <Menu
                mode="inline"
                selectedKeys={[getSelectedKey()]}
                style={{ borderRight: 0 }}
                items={[
                  {
                    key: "1",
                    label: <Link href="/">首页</Link>,
                  },
                  {
                    key: "2",
                    label: <Link href="/categories">分类</Link>,
                  },
                  {
                    key: "3",
                    label: <Link href="/tags">标签</Link>,
                  },
                  {
                    key: "4",
                    label: <Link href="/archives">归档</Link>,
                  },
                ]}
                onClick={() => setMenuOpen(false)}
              />
            }
            trigger="click"
            open={menuOpen}
            onOpenChange={setMenuOpen}
            placement="bottomRight"
          >
            <Button type="text" icon={<MenuOutlined />} />
          </Popover>
        )}
      </div>

      <div style={{ display: "flex", alignItems: "center", gap: "20px" }}>
        <Input
          placeholder="请输入关键词..."
          prefix={<SearchOutlined />}
          style={{ width: 200 }}
        />
        {isLoggedIn ? (
          <Popover content={<UserMenu />}>
            <Flex style={{ marginRight: 10, marginLeft: 10 }} align="center" gap={10}>
              <Avatar
                size={40}
                src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQE2e6JL_PUDVEgS0U5jSNMy-x0_EdLYEI9Tg&s"
              />
            </Flex>
          </Popover>
        ) : (
          <Link href="/auth/login">
            <Button type="primary">登录</Button>
          </Link>
        )}
      </div>
    </AntHeader>
  );
};
