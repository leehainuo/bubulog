import { create } from "zustand";
import { arrayMove } from "@dnd-kit/sortable";
import type { TabsProps } from "antd";
import { persist, createJSONStorage } from "zustand/middleware";

interface TabItem {
  key: string;
  label: string;
}

interface TabListState {
  items: NonNullable<TabsProps["items"]>;
  setItems: (items: NonNullable<TabsProps["items"]>) => void;
  moveItem: (activeId: string | number, overId: string | number) => void;
  removeItem: (key: string) => void;
  resetItems: () => void;
  addItem: (item: TabItem) => void;
  hasItem: (key: string) => boolean;
}

// 默认标签列表
const defaultItems = [
  { key: "/dashboard", label: "仪表盘" },
//   { key: "/dashboard/article", label: "文章管理" },
//   { key: "/dashboard/category", label: "分类管理" },
//   { key: "/dashboard/tag", label: "标签管理" },
//   { key: "/dashboard/setting", label: "博客设置" },
];

const useTabListStore = create<TabListState>()(
  persist(
    (set, get) => ({
      items: defaultItems,
      setItems: (items) => set({ items }),
      moveItem: (activeId, overId) => 
        set((state) => {
          const activeIndex = state.items.findIndex((i) => i.key === activeId);
          const overIndex = state.items.findIndex((i) => i.key === overId);
          return { items: arrayMove(state.items, activeIndex, overIndex) };
        }),
      removeItem: (key) => 
        set((state) => {
          // 不允许删除仪表盘
          if (key === "/dashboard") return state;
          return { items: state.items.filter(item => item.key !== key) };
        }),
      resetItems: () => {
        set({ items: defaultItems });
      },
      addItem: (item) => 
        set((state) => {
          // 如果标签已存在，则不添加
          if (state.items.some(i => i.key === item.key)) return state;
          return { items: [...state.items, item] };
        }),
      hasItem: (key) => get().items.some(item => item.key === key),
    }),
    {
      name: "tab-list-storage",
      storage: createJSONStorage(() => {
        // 只在客户端进行存储
        if (typeof window === 'undefined') {
          return {
            getItem: () => null,
            setItem: () => {},
            removeItem: () => {},
          };
        }
        return {
          getItem: (name) => {
            const str = window.localStorage.getItem(name);
            if (!str) return null;
            try {
              return JSON.parse(str);
            } catch {
              return null;
            }
          },
          setItem: (name, value) => {
            window.localStorage.setItem(name, JSON.stringify(value));
          },
          removeItem: (name) => {
            window.localStorage.removeItem(name);
          },
        };
      }),
    }
  )
);

export default useTabListStore;