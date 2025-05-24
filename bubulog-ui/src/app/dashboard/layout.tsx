"use client";
import React, { useState } from "react";
import { Layout, theme } from "antd";
import { Header } from "@/components/layout/back/Header";
import { Footer } from "@/components/layout/back/Footer";
import { Sider } from "@/components/layout/back/Sider";
import { TabList } from "@/components/layout/back/TabList";

const { Content } = Layout;

const DashboardLayout = ({ children }: { children: React.ReactNode }) => {
  const [collapsed, setCollapsed] = useState(false);
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  return (
    <Layout style={{ minHeight: '100vh' }} hasSider>
      <Sider collapsed={collapsed} />
      <Layout>
        <Header collapsed={collapsed} setCollapsed={setCollapsed} />
        <TabList />
        <Content
          style={{
            margin: "24px 16px",
            padding: 24,
            minHeight: 280,
            background: colorBgContainer,
            borderRadius: borderRadiusLG,
          }}
        >
          {children}
        </Content>
        <Footer />
      </Layout>
    </Layout>
  );
};

export default DashboardLayout;
