"use client";

/* eslint-disable @typescript-eslint/no-explicit-any */
import React, { useEffect, useState } from "react";
import type { DragEndEvent } from "@dnd-kit/core";
import {
  closestCenter,
  DndContext,
  PointerSensor,
  useSensor,
} from "@dnd-kit/core";
import {
  horizontalListSortingStrategy,
  SortableContext,
  useSortable,
} from "@dnd-kit/sortable";
import { CSS } from "@dnd-kit/utilities";
import { Tabs, Button, message } from "antd";
import { ReloadOutlined } from "@ant-design/icons";
import { usePathname, useRouter } from "next/navigation";
import useTabListStore from "@/store/tabListStore";
import { useTabNavigation } from "@/hooks/useTabNavigation";

interface DraggableTabPaneProps extends React.HTMLAttributes<HTMLDivElement> {
  "data-node-key": string;
}

const DraggableTabNode: React.FC<Readonly<DraggableTabPaneProps>> = ({
  ...props
}) => {
  const { attributes, listeners, setNodeRef, transform, transition } =
    useSortable({
      id: props["data-node-key"],
    });

  const style: React.CSSProperties = {
    ...props.style,
    transform: CSS.Translate.toString(transform),
    transition,
    cursor: "move",
  };

  return React.cloneElement(props.children as React.ReactElement<any, string>, {
    ref: setNodeRef,
    style,
    ...attributes,
    ...listeners,
  });
};

export const TabList = () => {
  const pathname = usePathname()
  const router = useRouter()
  const [mounted, setMounted] = useState(false);
  
  // 使用zustand store
  const { items, moveItem, removeItem, resetItems } = useTabListStore();

  // 使用标签导航hook
  useTabNavigation();

  useEffect(() => {
    setMounted(true);
  }, []);

  // 推拽事件处理
  const sensor = useSensor(PointerSensor, {
    activationConstraint: { distance: 10 },
  });
  const onDragEnd = ({ active, over }: DragEndEvent) => {
    if (active.id !== over?.id) {
      if (over) {
        moveItem(active.id, over.id);
      }
    }
  };
  
  // 点击标签事件处理
  const onChange = ( key: string ) => {
    router.push(key)
  }

  // 删除标签事件处理
  const onEdit = (targetKey: React.MouseEvent | React.KeyboardEvent | string, action: 'add' | 'remove') => {
    if (action === 'remove') {
      const key = targetKey as string;
      // 不允许删除仪表盘
      if (key === '/dashboard') {
        message.warning('不能删除仪表盘标签');
        return;
      }
      
      // 获取当前标签的索引
      const currentIndex = items.findIndex(item => item.key === key);
      
      // 删除标签
      removeItem(key);
      
      // 如果删除的是当前激活的标签，则跳转到上一个标签
      if (key === pathname) {
        // 如果删除的是最后一个标签，则跳转到前一个标签
        if (currentIndex === items.length - 1) {
          router.push(items[currentIndex - 1].key);
        } else {
          // 否则跳转到下一个标签
          router.push(items[currentIndex + 1].key);
        }
      }
    }
  };

  // 重置标签事件处理
  const handleReset = () => {
    resetItems();
    router.push('/dashboard');
    message.success('标签已重置');
  };

  if (!mounted) {
    return <div style={{ height: '40px' }} />;
  }

  return (
    <div style={{ display: 'flex', alignItems: 'center' }}>
      <Tabs
        hideAdd
        type="editable-card"
        items={items}
        activeKey={pathname}
        onChange={onChange}
        onEdit={onEdit}
        renderTabBar={(tabBarProps, DefaultTabBar) => (
          <DndContext
            sensors={[sensor]}
            onDragEnd={onDragEnd}
            collisionDetection={closestCenter}
          >
            <SortableContext
              items={items.map((i) => i.key)}
              strategy={horizontalListSortingStrategy}
            >
              <DefaultTabBar {...tabBarProps}>
                {(node) => (
                  <DraggableTabNode
                    {...(node as React.ReactElement<DraggableTabPaneProps>).props}
                    key={node.key}
                  >
                    {node}
                  </DraggableTabNode>
                )}
              </DefaultTabBar>
            </SortableContext>
          </DndContext>
        )}
        style={{
          marginBottom: 0,
          padding: '8px 16px 0',
          flex: 1
        }}
      />
      <Button 
        type="text" 
        icon={<ReloadOutlined />} 
        onClick={handleReset}
        style={{ marginRight: 16 }}
        title="重置标签"
      />
    </div>
  );
};
