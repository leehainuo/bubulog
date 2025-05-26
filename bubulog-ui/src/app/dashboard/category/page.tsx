"use client";
import { AddCategory } from "@/components/ui/back/category/AddCategory";
import { QueryCategory } from "@/components/ui/back/category/QueryCategory";
import { TableCategory } from "@/components/ui/back/category/TableCategory";
import React, { useState } from "react";
import { useQuery } from "@tanstack/react-query";
import { getCategory } from "@/api/category";
import { QueryParams } from "@/types/category.type";

export const CategoryPage = () => {
  const [page, setPage] = useState(1);
  const [queryParams, setQueryParams] = useState<QueryParams>({ categoryName: "" });

  // 查询和分页参数
  const { data, isLoading, error, refetch } = useQuery({
    queryKey: ["categories", page, queryParams],
    queryFn: () =>
      getCategory(
        queryParams.categoryName,
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
    setQueryParams({ categoryName: "" });
    setPage(1);
  };

  // 刷新
  const handleRefresh = () => {
    refetch();
  };

  return (
    <section className="flex flex-col">
      <div className="flex items-center gap-8">
        <AddCategory onSuccess={handleRefresh} />
        <div className="border-x h-6" />
        <QueryCategory onQuery={handleQuery} onReset={handleReset} />
      </div>
      <TableCategory
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

export default CategoryPage;
