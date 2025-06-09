"use client";
import { getArticle } from "@/api/article";
import { QueryArticle } from "@/components/ui/back/article/QueryArticle";
import { TableArticle } from "@/components/ui/back/article/TableArticle";
import { QueryParams } from "@/types/article.type";
import { PlusOutlined } from "@ant-design/icons";
import { useQuery } from "@tanstack/react-query";
import { Button } from "antd";
import Link from "next/link";
import React, { useState } from "react";

export default function ArticlePage() {
  const [page, setPage] = useState(1);
  const [queryParams, setQueryParams] = useState<QueryParams>({
    articleTitle: "",
  });

  // 查询和分页参数
  const { data, isLoading, error, refetch } = useQuery({
    queryKey: ["articles", page, queryParams],
    queryFn: () =>
      getArticle(
        queryParams.articleTitle,
        page,
        10,
        queryParams.startDate,
        queryParams.endDate
      ),
  });

  // 分页
  const pagination = {
    current: data?.data?.current,
    pageSize: data?.data?.size,
    total: data?.data?.total,
  };

  // 查询
  const handleQuery = (params: QueryParams) => {
    setQueryParams(params);
    setPage(1); // 查询时重置到第一页
  };

  // 重置
  const handleReset = () => {
    setQueryParams({ articleTitle: "" });
    setPage(1);
  };

  // 刷新
  const handleRefresh = () => {
    refetch();
  };

  return (
    <section className="flex flex-col">
      <div className="flex items-center gap-8">
      <Link href="/dashboard/article/new">
        <Button type="primary" className="w-20">
          <PlusOutlined /> 新增
        </Button>
      </Link>
        <div className="border-x h-6" />
        <QueryArticle onQuery={handleQuery} onReset={handleReset} />
      </div>
      <TableArticle
        data={data?.data?.data}
        pagination={pagination}
        onPageChange={setPage}
        isLoading={isLoading}
        onRefresh={handleRefresh}
      />
      {error && <div className="text-red-500">加载失败: {error.message}</div>}
    </section>
  );
}
