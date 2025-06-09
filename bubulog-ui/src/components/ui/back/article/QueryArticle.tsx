/* eslint-disable @typescript-eslint/no-explicit-any */
"use client";
import { QueryParams } from "@/types/article.type";
import { ReloadOutlined, SearchOutlined } from "@ant-design/icons";
import { Input, DatePicker, Button } from "antd";
import React, { useState } from "react";

const { RangePicker } = DatePicker;

export const QueryArticle = ({
  onQuery,
  onReset
}: {
  onQuery: (params: QueryParams) => void,
  onReset: () => void
}) => {

  const [articleTitle, setArticleTitle] = useState("");
  const [dates, setDates] = useState<any>([]);

  // 查询
  const handleQuery = () => {
    onQuery({
      articleTitle,
      startDate: dates?.[0]?.format ? dates[0].format("YYYY-MM-DD HH:mm:ss") : undefined,
      endDate: dates?.[1]?.format ? dates[1].format("YYYY-MM-DD HH:mm:ss") : undefined,
    })
  };
  // 重置
  const handleReset = () => {
    setArticleTitle("")
    setDates([])
    onReset()
  }

  return (
    <div className="flex items-center gap-x-6 overflow-auto">
      <div className="flex items-center gap-x-3">
        <p className="min-w-14">文章名称</p>
        <Input
         placeholder="请输入 (模糊查询)"
         style={{ minWidth: 80 }}
         value={articleTitle}
         onChange={e => setArticleTitle(e.target.value)}
        />
      </div>
      {/* 查询日期 */}
      <div className="flex items-center gap-x-3">
        <p className="min-w-14">创建日期</p>
        <RangePicker
          style={{ minWidth: 120 }}
          placeholder={["开始时间", "结束时间"]}
          value={dates}
          onChange={setDates}
        />
      </div>
      {/* 查询 */}
      <Button
       type="primary"
       className="w-20"
       onClick={handleQuery}
      >
        <SearchOutlined />
        查询
      </Button>
      {/* 重置 */}
      <Button
       className="w-20"
       onClick={handleReset} 
      >
        <ReloadOutlined />
        重置
      </Button>
    </div>
  );
};
