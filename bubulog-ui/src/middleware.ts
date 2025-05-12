import { NextResponse } from "next/server";
import type { NextRequest } from "next/server";

// 需要登录才能访问的路径
const PROTECT_LIST = ["/dashboard"];

// 登录用户不应访问的路径
const AUTH_LIST = ["/login", "/register"];

export async function middleware(req: NextRequest) {
  const token = req.cookies.get("access_token")?.value;
  const path = req.nextUrl.pathname;

  // 检查是否访问受保护的路由
  if (PROTECT_LIST.some((route) => path.startsWith(route))) {
    /**
     * 情况一:无令牌 回首页 弹窗提示
     * 情况二:令牌有 检查是否过期
     */
    if (!token) {
      const url = new URL("/", req.url);
      url.searchParams.set("auth", "required");
      return NextResponse.redirect(url);
    } else {
      console.log("开发中...")
    }
  }

  // 检查是否已登录但访问登录/注册页面
  if (AUTH_LIST.some((route) => path.startsWith(route)) && token) {
    return NextResponse.redirect(new URL("/dashboard", req.url));
  }

  return NextResponse.next();
}

export const config = {
  matcher: [
    '/((?!api|_next/static|_next/image|favicon.ico|sitemap.xml|robots.txt).*)',
  ]
};
