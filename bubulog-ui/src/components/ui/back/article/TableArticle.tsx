/* eslint-disable @next/next/no-img-element */
"use client";
import React, { useState } from "react";
import { Empty, message, Modal, Table } from "antd";
import type { TableColumnsType } from "antd";
import { useRouter } from "next/navigation";

interface DataType {
  articleId: string;
  articleTitle: string;
  articleCover: string;
  createDate: string;
}

interface TableArticleProps {
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

export const TableArticle = ({
  data,
  pagination,
  onPageChange,
  isLoading,
  onRefresh,
}: TableArticleProps) => {
  const router = useRouter();
  // 提示弹窗
  const [messageApi, contextHolder] = message.useMessage();
  // 删除弹窗相关
  const [deleteModalOpen, setDeleteModalOpen] = useState(false);
  const [deleteRecord, setDeleteRecord] = useState<DataType | null>(null);

  // 删除
  const handleDelete = (record: DataType) => {
    setDeleteRecord(record);
    setDeleteModalOpen(true);
  };
  const handleDeleteOk = async () => {
    // 处理失败....
    messageApi.success("删除成功");
    setDeleteModalOpen(false);
    onRefresh();
  };

  const columns: TableColumnsType<DataType> = [
    { title: "文章标题", dataIndex: "articleTitle" },
    { 
      title: "文章封面", 
      dataIndex: "articleCover",
      render: (cover: string) => (
        <img 
          src={cover} 
          alt="文章封面" 
          style={{ 
            width: '100px', 
            height: '60px', 
            objectFit: 'cover',
            borderRadius: '4px'
          }} 
        />
      )
    },
    { title: "创建时间", dataIndex: "createDate" },
    {
      title: "操作",
      dataIndex: "",
      render: (_, record: DataType) => (
        <>
          <a 
            style={{ marginRight: 16 }} 
            onClick={() => router.push(`/dashboard/article/edit/${record.articleId}`)}
          >
            编辑
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
        className="my-8"
        rowKey="articleId"
        columns={columns}
        dataSource={data}
        pagination={{
          current: pagination.current,
          pageSize: pagination.pageSize,
          total: pagination.total,
          onChange: onPageChange,
        }}
        loading={isLoading}
        locale={{ emptyText: isLoading ? null : <Empty description="暂无数据"></Empty> }}
      />

      {/* 删除弹窗 */}
      <Modal
        title="删除"
        open={deleteModalOpen}
        onOk={handleDeleteOk}
        onCancel={() => setDeleteModalOpen(false)}
        okText="确定"
        cancelText="取消"
      >
        确定要删除：{deleteRecord?.articleTitle} 这篇文章吗？
      </Modal>
    </>
  );
};
