"use client";
import { AddArticle } from "@/components/ui/back/article/AddArticle";
import React from "react";
import { useQuery, useQueryClient } from "@tanstack/react-query";
import { getAllCategory } from "@/api/category";
import { getAllTag } from "@/api/tag";

export default function NewArticlePage() {
  const queryClient = useQueryClient();
  
  const { data: categoryData } = useQuery({
    queryKey: ["categories"],
    queryFn: () => getAllCategory(),
  });

  const { data: tagData } = useQuery({
    queryKey: ["tags"],
    queryFn: () => getAllTag(),
  });

  const handleSuccess = () => {
    queryClient.invalidateQueries({ queryKey: ["articles"] });
  };

  console.log("Category Data (from .data.data):", categoryData?.data?.data);

  return <AddArticle
          categoryData={categoryData?.data?.data}
          tagData={tagData?.data?.data}
          onSuccess={handleSuccess}
         />;
}
