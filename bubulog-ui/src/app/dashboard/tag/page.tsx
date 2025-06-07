"use client";
import { getTag } from "@/api/tag";
import { AddTag } from "@/components/ui/back/tag/AddTag";
import { QueryTag } from "@/components/ui/back/tag/QueryTag";
import { TableTag } from "@/components/ui/back/tag/TableTag";
import { QueryParams } from "@/types/tag.type";
import { useQuery } from "@tanstack/react-query";
import React, { useState } from "react";

export default function TagPage() {
  const [page, setPage] = useState(1);
  const [queryParams, setQueryParams] = useState<QueryParams>({ tagName: "" })

  const { data, isLoading, error, refetch } = useQuery({
    queryKey:["tags", page, queryParams],
    queryFn: () => getTag(
      queryParams.tagName,
      page,
      10,
      queryParams.startDate,
      queryParams.endDate
    )
  })

  const pagination = {
    current: data?.data?.current,
    pageSize: data?.data?.size,
    total: data?.data?.total,
  }
  
  // 查询
  const handleQuery = (params: QueryParams) => {
    setQueryParams(params);
    setPage(1); // 查询时重置到第一页
  }

  // 重置
  const handleReset = () => {
    setQueryParams({ tagName: "" });
    setPage(1);
  }

  // 刷新
  const handleRefresh = () => {
    refetch();
  }

  return (
    <section className="flex flex-col">
      <div className="flex items-center gap-8">
        <AddTag onRefresh={handleRefresh} />
        <div className="border-x h-6" />
        <QueryTag onQuery={handleQuery} onReset={handleReset} />
      </div>
      <TableTag
        data={data?.data?.data}
        pagination={pagination}
        onPageChange={setPage}
        isLoading={isLoading}
        onRefresh={handleRefresh}
      />
      {error && <div className="text-red-500">加载失败: {error.message}</div>}
    </section>
  );
};
