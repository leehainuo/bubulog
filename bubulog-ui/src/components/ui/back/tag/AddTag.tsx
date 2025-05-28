"use client";
import React, { useState } from "react";
import { Button, Modal, Input, message } from "antd";
import { PlusOutlined } from "@ant-design/icons";
import { addTag } from "@/api/tag";

export const AddTag = ({ onRefresh }: { onRefresh: () => void }) => {
  const [open, setOpen] = useState(false);
  const [confirmLoading, setConfirmLoading] = useState(false);
  const [tagName, setTagName] = useState("")
  const [messageApi, contextHolder] = message.useMessage()

  const showModal = () => {
    setOpen(true);
  };

  const handleOk = async () => {
    setConfirmLoading(true);
    setTimeout(() => {
      setOpen(false);
      setConfirmLoading(false);
    }, 500);
    const res = await addTag(tagName);
    if (res.status === 200) {
      if (res.data.code === 1000) {
        messageApi.warning("标签名已存在")
      } else {
        messageApi.success("添加成功")
      }
      setTagName("")
      onRefresh()
    } else {
      messageApi.error("添加失败")
    }
  };

  const handleCancel = () => {
    console.log("添加标签已取消");
    setOpen(false);
  };

  return (
    <div>
      {contextHolder}
      <Button type="primary" onClick={showModal} className="w-20">
        <PlusOutlined /> 新增
      </Button>
      <Modal
        title="新增标签"
        cancelText="取消"
        okText="确定"
        open={open}
        onOk={handleOk}
        confirmLoading={confirmLoading}
        onCancel={handleCancel}
      >
        <Input
          style={{ marginTop: 10 }}
          placeholder="请输入标签名称"
          value={tagName}
          onChange={(e) => setTagName(e.target.value)}
        />
      </Modal>
    </div>
  );
};
