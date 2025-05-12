import { NextRequest, NextResponse } from "next/server";

const API_URL = process.env.API_URL || "http://localhost:8080";

export const POST = async (req: NextRequest) => {
  try {
    const body = await req.json();

    // 从 Next.js API 路由发送请求到 Spring 后端
    const res = await fetch(`${API_URL}/auth/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    });
    // 从 Spring 后端获取的数据
    const data = await res.json();

    // 对响应失败进行处理
    if (!res.ok) {
      return NextResponse.json(
        { error: "Authentication failed" },
        { status: res.status }
      );
    }

    // 创建响应并设置 cookie
    const nextResponse = NextResponse.json(data);
    
    if (data.data.token) {
      nextResponse.cookies.set({
        name: "access_token",
        value: data.data.token,
      });
    }

    return nextResponse;
  } catch (err) {
    console.error("Login error:", err);
    return NextResponse.json(
      { error: "Internal server error" },
      { status: 500 }
    );
  }
};
