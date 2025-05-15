"use client";

import { X, RefreshCw } from "lucide-react";
import { useRouter } from "next/navigation";
import { useState, useEffect } from "react";
import { useTabStore } from "@/store/tabStore";

// 定义拖拽Tab组件
export const DraggableTabs = () => {
  const { tabs, activeTab, removeTab, setActiveTab, reorderTabs, clearAllTabs } = useTabStore();
  const router = useRouter();
  const [draggedTab, setDraggedTab] = useState<string | null>(null);

  // 如果没有活动标签但有标签，设置第一个标签为活动标签
  useEffect(() => {
    if (!activeTab && tabs.length > 0) {
      setActiveTab(tabs[0].id);
    }
  }, [activeTab, tabs, setActiveTab]);

  // 处理拖拽开始
  const handleDragStart = (e: React.DragEvent, id: string) => {
    setDraggedTab(id);
    // 设置拖拽图像为半透明
    if (e.dataTransfer.setDragImage) {
      const element = document.getElementById(`tab-${id}`);
      if (element) {
        e.dataTransfer.setDragImage(element, 0, 0);
      }
    }
  };

  // 处理拖拽结束
  const handleDragEnd = () => {
    setDraggedTab(null);
  };

  // 处理拖拽进入
  const handleDragOver = (e: React.DragEvent, targetId: string) => {
    e.preventDefault();
    if (!draggedTab || draggedTab === targetId) return;

    const draggedIndex = tabs.findIndex((tab) => tab.id === draggedTab);
    const targetIndex = tabs.findIndex((tab) => tab.id === targetId);

    if (draggedIndex === -1 || targetIndex === -1) return;

    // 重新排序标签
    const newTabs = [...tabs];
    const draggedItem = newTabs[draggedIndex];
    newTabs.splice(draggedIndex, 1);
    newTabs.splice(targetIndex, 0, draggedItem);
    reorderTabs(newTabs);
  };

  // 处理标签点击
  const handleTabClick = (id: string, path: string) => {
    setActiveTab(id);
    router.push(path);
  };

  // 处理标签关闭
  const handleTabClose = (e: React.MouseEvent, id: string) => {
    e.stopPropagation();
    
    // 如果关闭的是当前活动标签，需要记录删除前的活动标签
    const isActiveTab = id === activeTab;
    
    // 移除标签
    removeTab(id);
    
    // 如果关闭的是当前活动标签，需要导航到新的活动标签
    if (isActiveTab) {
      // 延迟执行，确保状态已更新
      setTimeout(() => {
        const updatedTabs = useTabStore.getState().tabs;
        const updatedActiveTab = useTabStore.getState().activeTab;
        
        if (updatedActiveTab) {
          const newActiveTabItem = updatedTabs.find(tab => tab.id === updatedActiveTab);
          if (newActiveTabItem) {
            router.push(newActiveTabItem.path);
          }
        }
      }, 0);
    }
  };

  // 处理清空所有标签
  const handleClearAllTabs = () => {
    clearAllTabs();
    // 清空后跳转到仪表盘
    router.push("/dashboard");
  };

  // 当没有标签时，不渲染任何内容
  if (tabs.length === 0) {
    return null;
  }

  return (
    <div className="flex items-center">
      <div className="flex overflow-x-auto scrollbar-hide transition-all duration-300 ease-in-out">
        {tabs.map((tab) => (
          <div
            id={`tab-${tab.id}`}
            key={tab.id}
            draggable
            onDragStart={(e) => handleDragStart(e, tab.id)}
            onDragEnd={handleDragEnd}
            onDragOver={(e) => handleDragOver(e, tab.id)}
            onClick={() => handleTabClick(tab.id, tab.path)}
            className={`
              flex items-center px-4 py-2 cursor-pointer select-none
              border-b-2 min-w-32 max-w-40 truncate
              transition-all duration-200 ease-in-out transform
              ${draggedTab === tab.id ? 'opacity-50' : 'opacity-100'}
              ${activeTab === tab.id 
                ? "border-primary text-primary" 
                : "border-transparent hover:bg-secondary"}
            `}
          >
            <span className="truncate">{tab.name}</span>
            {tab.closable !== false && (
              <button
                onClick={(e) => handleTabClose(e, tab.id)}
                className="ml-2 p-1 rounded-full hover:bg-accent hover:text-accent-foreground transition-colors duration-200"
              >
                <X size={14} />
              </button>
            )}
          </div>
        ))}
      </div>

      {/* 操作按钮区域 */}
      <div className="ml-auto flex items-center">
        <button
          onClick={handleClearAllTabs}
          className="ml-2 p-2 rounded-md hover:bg-accent hover:text-accent-foreground transition-colors duration-200 flex items-center"
          title="关闭所有标签"
        >
          <RefreshCw size={16} />
        </button>
      </div>
    </div>
  );
} 