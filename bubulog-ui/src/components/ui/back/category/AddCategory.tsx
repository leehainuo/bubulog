"use client";
import React, { useState } from "react";
import { Button, Modal, Input, message } from "antd";
import { PlusOutlined } from "@ant-design/icons";
import { addCategory } from "@/api/category";

export const AddCategory = ({ onRefresh }: { onRefresh: () => void }) => {
  const [open, setOpen] = useState(false);
  const [confirmLoading, setConfirmLoading] = useState(false);
  const [categoryName, setCategoryName] = useState("");
  const [messageApi, contextHolder] = message.useMessage();

  const showModal = () => {
    setOpen(true);
  };

  // 处理确定按钮
  const handleOk = async () => {
    setConfirmLoading(true);
    setTimeout(() => {
      setOpen(false);
      setConfirmLoading(false);
    }, 500);
    const res = await addCategory(categoryName);
    if (res.status === 200) {
      if (res.data.code === 1000) {
        messageApi.warning("分类名称已存在");
      } else {
        messageApi.success("添加成功");
      }
      setCategoryName("");
      onRefresh();
    } else {
      messageApi.error("添加失败");
    }
  };
  // 处理取消按钮
  const handleCancel = () => {
    console.log("添加分类已取消");
    setOpen(false);
  };

  return (
      <div>
        {contextHolder}
        <Button type="primary" onClick={showModal} className="w-20">
          <PlusOutlined /> 新增
        </Button>
        <Modal
          title="新增分类"
          cancelText="取消"
          okText="确定"
          open={open}
          onOk={handleOk}
          confirmLoading={confirmLoading}
          onCancel={handleCancel}
        >
          <Input
            style={{ marginTop: 10 }}
            placeholder="请输入分类名称"
            value={categoryName}
            onChange={(e) => setCategoryName(e.target.value)}
          />
        </Modal>
      </div>
  );
};
