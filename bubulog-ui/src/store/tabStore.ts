"use client";

import { create } from "zustand";
import { persist } from "zustand/middleware";

export type TabItem = {
  id: string;
  name: string;
  path: string;
  icon?: string;
  closable?: boolean; // 是否可关闭
};

interface TabState {
  tabs: TabItem[];
  activeTab: string | null;
  addTab: (tab: Omit<TabItem, "id">) => void;
  removeTab: (id: string) => void;
  setActiveTab: (id: string) => void;
  reorderTabs: (tabs: TabItem[]) => void;
  clearAllTabs: () => void; // 清除所有可关闭的标签
}

// 默认标签 - 仪表盘
const DEFAULT_TAB = {
  name: "仪表盘",
  path: "/dashboard",
  closable: false, // 默认标签不可关闭
};

export const useTabStore = create<TabState>()(
  persist(
    (set) => ({
      tabs: [],
      activeTab: null,
      addTab: (tab) => {
        const id = `${tab.path}-${Date.now()}`;
        set((state) => {
          // 检查是否已经存在相同路径的标签
          const existingTab = state.tabs.find((t) => t.path === tab.path);
          if (existingTab) {
            // 如果存在，只设置为活动标签而不添加新标签
            return { activeTab: existingTab.id };
          }
          
          // 设置标签是否可关闭
          const closable = tab.path !== DEFAULT_TAB.path && tab.closable !== false;
          
          // 如果不存在，添加新标签并设置为活动标签
          return {
            tabs: [...state.tabs, { ...tab, id, closable }],
            activeTab: id,
          };
        });
      },
      removeTab: (id) => {
        set((state) => {
          // 检查标签是否可关闭
          const tabToRemove = state.tabs.find(tab => tab.id === id);
          if (tabToRemove && tabToRemove.closable === false) {
            return state; // 不可关闭的标签不做任何操作
          }
          
          const newTabs = state.tabs.filter((tab) => tab.id !== id);
          
          // 如果删除的是当前活动标签，需要选择一个新的活动标签
          let newActiveTab = state.activeTab;
          if (state.activeTab === id) {
            // 找到要删除标签的索引
            const index = state.tabs.findIndex((tab) => tab.id === id);
            
            // 优先选择前一个标签
            if (index > 0) {
              newActiveTab = state.tabs[index - 1].id;
            } 
            // 如果没有前一个标签，则选择第一个标签
            else if (newTabs.length > 0) {
              newActiveTab = newTabs[0].id;
            } 
            // 如果没有任何标签了
            else {
              newActiveTab = null;
            }
          }
          
          return {
            tabs: newTabs,
            activeTab: newActiveTab,
          };
        });
      },
      setActiveTab: (id) => {
        set({ activeTab: id });
      },
      reorderTabs: (tabs) => {
        set({ tabs });
      },
      clearAllTabs: () => {
        set((state) => {
          // 只保留不可关闭的标签
          const remainingTabs = state.tabs.filter(tab => tab.closable === false);
          
          // 如果没有不可关闭的标签，则保持当前状态
          if (remainingTabs.length === 0) {
            return state;
          }
          
          // 找到仪表盘标签（路径为/dashboard的标签）
          const dashboardTab = remainingTabs.find(tab => tab.path === '/dashboard');
          
          // 设置仪表盘标签为活动标签，如果没有则使用第一个标签
          return {
            tabs: remainingTabs,
            activeTab: dashboardTab ? dashboardTab.id : remainingTabs[0].id
          };
        });
      },
    }),
    {
      name: "tab-storage",
    }
  )
); 