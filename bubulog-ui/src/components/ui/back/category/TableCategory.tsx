"use client";
import React, { useState } from "react";
import { Table, Modal, Input, message, Empty } from "antd";
import type { TableColumnsType } from "antd";
import { deleteCategory, updateCategory } from "@/api/category";

interface DataType {
  categoryId: string;
  categoryName: string;
  createDate: string;
}

interface TableCategoryProps {
  data: DataType[];
  pagination: {
    current: number;
    pageSize: number;
    total: number;
  };
  onPageChange: (page: number) => void;
  isLoading: boolean;
  onRefresh: () => void;
}

export const TableCategory = ({
  data,
  pagination,
  onPageChange,
  isLoading,
  onRefresh,
}: TableCategoryProps) => {
  // 提示弹窗
  const [messageApi, contextHolder] = message.useMessage();
  // 编辑弹窗相关
  const [editModalOpen, setEditModalOpen] = useState(false);
  const [editRecord, setEditRecord] = useState<DataType | null>(null);
  const [editName, setEditName] = useState("");

  // 删除弹窗相关
  const [deleteModalOpen, setDeleteModalOpen] = useState(false);
  const [deleteRecord, setDeleteRecord] = useState<DataType | null>(null);

  // 编辑
  const handleEdit = (record: DataType) => {
    setEditRecord(record);
    setEditName(record.categoryName);
    setEditModalOpen(true);
  };
  const handleEditOk = async () => {
    await updateCategory(editRecord!.categoryId, editName);
    messageApi.success("修改成功");
    setEditModalOpen(false);
    onRefresh();
  };

  // 删除
  const handleDelete = (record: DataType) => {
    setDeleteRecord(record);
    setDeleteModalOpen(true);
  };
  const handleDeleteOk = async () => {
    await deleteCategory(deleteRecord!.categoryId);
    messageApi.success("删除成功");
    setDeleteModalOpen(false);
    onRefresh();
  };

  const columns: TableColumnsType<DataType> = [
    { title: "分类名称", dataIndex: "categoryName" },
    { title: "创建时间", dataIndex: "createDate" },
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

  return (
    <>
      {contextHolder}
      <Table<DataType>
        className="mt-8"
        rowKey="categoryId"
        columns={columns}
        dataSource={data}
        pagination={{
          current: pagination.current,
          pageSize: pagination.pageSize,
          total: pagination.total,
          onChange: onPageChange,
        }}
        loading={isLoading}
        locale={{emptyText: isLoading ? null : <Empty description="暂无数据"></Empty>}}
      />

      {/* 编辑弹窗 */}
      <Modal
        title="编辑分类"
        open={editModalOpen}
        onOk={handleEditOk}
        onCancel={() => setEditModalOpen(false)}
        okText="确定"
        cancelText="取消"
      >
        <Input
          value={editName}
          onChange={e => setEditName(e.target.value)}
          placeholder="请输入分类名称"
        />
      </Modal>

      {/* 删除弹窗 */}
      <Modal
        title="删除"
        open={deleteModalOpen}
        onOk={handleDeleteOk}
        onCancel={() => setDeleteModalOpen(false)}
        okText="确定"
        cancelText="取消"
      >
        确定要删除：{deleteRecord?.categoryName} 标签吗？
      </Modal>
    </>
  );
};
