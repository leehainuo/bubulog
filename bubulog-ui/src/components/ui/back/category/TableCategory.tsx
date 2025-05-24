"use client";
import React from "react";
import { Table, Modal, message } from "antd";
import type { TableColumnsType } from "antd";

// 编辑和删除的处理函数
const handleEdit = (record: DataType) => {
  Modal.info({
    title: "编辑",
    content: `你点击了编辑：${record.categoryName}`,
  });
};

const handleDelete = (record: DataType) => {
  Modal.confirm({
    title: "删除",
    content: `确定要删除：${record.categoryName} 吗？`,
    onOk: () => {
      message.success("删除成功");
      // 这里可以加删除逻辑
    },
  });
};

interface DataType {
  categoryId: string;
  categoryName: string;
  createTime: string;
}

const columns: TableColumnsType<DataType> = [
  { title: "分类名称", dataIndex: "categoryName" },
  { title: "创建时间", dataIndex: "createTime" },
  {
    title: "操作",
    dataIndex: "",
    render: (_, record: DataType) => (
      <>
        <a style={{ marginRight: 16 }} onClick={() => handleEdit(record)}>
          修改
        </a>
        <a style={{ color: "red" }} onClick={() => handleDelete(record)}>
          删除
        </a>
      </>
    ),
  },
];

const dataSource = Array.from<DataType>({ length: 46 }).map<DataType>(
  (_, i) => ({
    categoryId: i.toString(),
    categoryName: `Edward King ${i}`,
    createTime: "2025-5-24",
  })
);

export const TableCategory = () => {

  return (
    <Table<DataType>
      className="mt-8"
      rowKey="categoryId"
      columns={columns.map((col) => {
        // 让 columns 能访问 handleEdit/handleDelete
        if (col.title === "操作") {
          return {
            ...col,
            render: (_, record: DataType) => (
              <>
                <a
                  style={{ marginRight: 16 }}
                  onClick={() => handleEdit(record)}
                >
                  修改
                </a>
                <a
                  style={{ color: "red" }}
                  onClick={() => handleDelete(record)}
                >
                  删除
                </a>
              </>
            ),
          };
        }
        return col;
      })}
      dataSource={dataSource}
    />
  );
};
