"use client";
import React, { useState } from "react";
import { getArticleList } from "@/api/article";
/* eslint-disable @next/next/no-img-element */
import { Header } from "@/components/layout/front/Header";
import { SideBar } from "@/components/layout/front/SideBar";
import { useQuery } from "@tanstack/react-query";
import { Tag, Card, Pagination, Skeleton } from "antd";
import Link from "next/link";

const { Meta } = Card;

interface Category {
  categoryId: number,
  categoryName: string
}
interface Tag {
  tagId: number
}
interface ArticleList {
  articleCover: string,
  articleId: number,
  articleTitle: string,
  createDate: string,
  articleSummary: string,
  category: Category,
  tags: Tag[]
}

export default function Home() {
  const [current, setCurrent] = useState(1);
  const pageSize = 10;

  const { data, isLoading, error } = useQuery({
    queryKey: ["home-articles", current, pageSize],
    queryFn: () => getArticleList(current, pageSize),
  });

  const articleData: ArticleList[] = data?.data?.data;
  const total = data?.data?.total || 0;

  console.log(data?.data?.data)

  return (
    <section className="w-full min-h-screen bg-[#f5f5f5]">
      <Header />
      <main className="grid grid-cols-12 gap-8 py-12 px-8 md:px-12 lg:px-[15%] 2xl:px-72">
        {/* Article */}
        <div className="col-span-12 md:col-span-8">
          {/* 文章列表，grid 表格布局，分为 2 列 */}
          <div className="grid grid-cols-2 gap-4">
            {isLoading &&
              Array.from({ length: 4 }).map((_, index) => (
                <Card key={index} className="overflow-hidden">
                  <div className="w-full h-48 bg-gray-200 rounded mb-4 animate-pulse" />
                  <Skeleton active paragraph={{ rows: 2 }} title={false} />
                </Card>
              ))
            }
            {error && <div>加载失败</div>}
            {articleData && articleData.map((item: ArticleList) => (
              <Link href={`/article/${item.articleId}`} key={item.articleId} className="block">
                <Card
                  hoverable
                  className="overflow-hidden"
                  cover={
                    <div className="w-full h-48 overflow-hidden relative">
                      <img
                        alt={item.articleTitle}
                        src={item.articleCover}
                        className="w-full h-full object-cover object-center absolute inset-0"
                      />
                    </div>
                  }
                >
                  <Meta
                    title={item.articleTitle}
                    description={
                      <div className="flex items-center justify-between">
                        <p className="hidden md:block">{item.createDate}</p>
                        <Tag color="blue">{item.category.categoryName}</Tag>
                      </div>
                    }
                  />
                </Card>
              </Link>
            ))}
          </div>
          <Pagination
            align="center"
            current={current}
            pageSize={pageSize}
            total={total}
            onChange={page => setCurrent(page)}
            style={{
              margin: "40px 0 40px 0",
            }}
          />
        </div>
        {/* SideBar */}
        <SideBar />
      </main>
    </section>
  );
}
