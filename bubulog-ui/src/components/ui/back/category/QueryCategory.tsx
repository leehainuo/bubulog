"use client";
import { ReloadOutlined, SearchOutlined } from "@ant-design/icons";
import { Input, DatePicker, Button } from "antd";
import React from "react";

const { RangePicker } = DatePicker;

export const QueryCategory = () => {
  return (
    <div className="flex items-center gap-x-6">
      <div className="flex items-center gap-x-3">
        <p>分类名称</p>
        <Input placeholder="请输入 (模糊查询)" style={{ width: 180 }} />
      </div>
      {/* 查询日期 */}
      <div className="flex items-center gap-x-3">
        <p>创建日期</p>
        <RangePicker 
         placeholder={["开始时间", "结束时间"]}
        />
      </div>
      {/* 查询 */}
      <Button type="primary" className="w-20">
        <SearchOutlined />查询
      </Button>
      {/* 重置 */}
      <Button className="w-20">
        <ReloadOutlined />重置
      </Button>
    </div>
  );
};
