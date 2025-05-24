import { NextResponse } from "next/server";
import type { NextRequest } from "next/server";

// 需要登录才能访问的路径
const PROTECT_LIST = [
    "/dashboard"
];

// 登录用户不应访问的路径
const AUTH_LIST = [
    "/auth/login",
    "/register"
];

export const middleware = async (
    req: NextRequest
) => {
  const token = req.cookies.get("Authorization")?.value;
  const { pathname } = req.nextUrl;

  // 访问需要登录的页面
  if (PROTECT_LIST.some(route => pathname.startsWith(route)) && !token) {
    const url = new URL('/auth/login', req.url);
    url.searchParams.set('message', '请先登录后再访问');
    return NextResponse.redirect(url);
  }

  // 已登录用户访问登录页
  if (AUTH_LIST.includes(pathname) && token) {
    const url = new URL("/dashboard", req.url);
    url.searchParams.set('message', '请勿重复登录');
    return NextResponse.redirect(url);
  }

  return NextResponse.next();
}

export const config = {
  matcher: [
    '/((?!api|_next/static|_next/image|favicon.ico|sitemap.xml|robots.txt).*)',
  ]
};
