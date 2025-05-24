import { useEffect } from 'react';
import { usePathname } from 'next/navigation';
import useTabListStore from '@/store/tabListStore';

// 路由到标签名称的映射
const routeToLabelMap: Record<string, string> = {
  '/dashboard': '仪表盘',
  '/dashboard/article': '文章管理',
  '/dashboard/category': '分类管理',
  '/dashboard/tag': '标签管理',
  '/dashboard/setting': '博客设置',
  // 可以添加更多路由映射
};

export const useTabNavigation = () => {
  const pathname = usePathname();
  const { addItem, hasItem } = useTabListStore();

  useEffect(() => {
    // 如果当前路径在映射中且标签不存在，则添加新标签
    if (routeToLabelMap[pathname] && !hasItem(pathname)) {
      addItem({
        key: pathname,
        label: routeToLabelMap[pathname],
      });
    }
  }, [pathname, addItem, hasItem]);
}; 