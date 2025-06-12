"use client";

import React from "react";
import { useQuery } from "@tanstack/react-query";
import { getAllCategory } from "@/api/category";
import { getAllTag } from "@/api/tag";
import { EditArticle } from "@/components/ui/back/article/EditArticle";
import { useParams } from "next/navigation";
import { getArticleDetail } from "@/api/article";

export default function EditArticlePage() {
  const params = useParams();
  const articleId = params.articleId as string;

  // 获取当前文章
  const { data: articleData } = useQuery({
    queryKey: ["article", articleId],
    queryFn: () => getArticleDetail(articleId),
    staleTime: 0, // 数据立即过期
    gcTime: 0, // 不缓存数据
    refetchOnMount: true, // 组件挂载时重新获取
    refetchOnWindowFocus: true, // 窗口获得焦点时重新获取
  });

  // 获取分类下拉列表
  const { data: categoryData } = useQuery({
    queryKey: ["categories"],
    queryFn: () => getAllCategory(),
  });
  // 获取标签下拉列表
  const { data: tagData } = useQuery({
    queryKey: ["tags"],
    queryFn: () => getAllTag(),
  });

  console.log("Article Data", articleData?.data)

  return <EditArticle
          articleData={articleData?.data?.data}
          categoryData={categoryData?.data?.data}
          tagData={tagData?.data?.data}
         />;
}
