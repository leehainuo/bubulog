"use client";
import React, { useState } from "react";
import { Table, Modal, Input, message } from "antd";
import type { TableColumnsType } from "antd";
import { deleteTag, updateTag } from "@/api/tag";

interface DataType {
  tagId: string;
  tagName: string;
  createDate: string;
}

interface TableTagProps {
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

export const TableTag = ({
  data,
  pagination,
  onPageChange,
  isLoading,
  onRefresh,
}: TableTagProps) => {
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
    setEditName(record.tagName);
    setEditModalOpen(true);
  };
  const handleEditOk = async () => {
    await updateTag(editRecord!.tagId, editName)
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
    await deleteTag(editRecord!.tagId)
    messageApi.success("删除成功");
    setDeleteModalOpen(false);
    onRefresh();
  };

  const columns: TableColumnsType<DataType> = [
    { title: "标签名称", dataIndex: "tagName" },
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

  return (
    <>
      {contextHolder}
      <Table<DataType>
        className="mt-8"
        rowKey="tagId"
        columns={columns}
        dataSource={data}
        pagination={{
          current: pagination.current,
          pageSize: pagination.pageSize,
          total: pagination.total,
          onChange: onPageChange,
        }}
        loading={isLoading}
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
        确定要删除：{deleteRecord?.tagName} 标签吗？
      </Modal>
    </>
  );
};
