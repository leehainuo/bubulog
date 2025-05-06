"use client";

import Link from "next/link";
import React from "react";
import { usePathname } from "next/navigation";

const links = [
  {
    name: "首页",
    path: "/",
  },
  {
    name: "分类",
    path: "/category",
  },
  {
    name: "标签",
    path: "/tag",
  },
  {
    name: "归档",
    path: "/archive",
  },
];

export const Nav = () => {
  const pathname = usePathname();

  return (
    <nav className="flex mx-auto">
      {links.map((item, index) => {
        return (
          <Link key={index} href={item.path}>
            <ul
              className={`w-20 text-center text-[14.5px] font-medium relative
             ${
               pathname === item.path
                 ? "text-primary"
                 : "hover:text-[#252933] text-[#666] dark:text-[#a1a1a1]"
             }
             after:content-[''] after:absolute after:bottom-[-20px] after:left-0 after:w-full after:h-[2.5px] 
             after:bg-primary after:scale-x-0 hover:after:scale-x-100`}
            >
              {item.name}
            </ul>
          </Link>
        );
      })}
    </nav>
  );
};
