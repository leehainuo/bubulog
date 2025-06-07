import React from 'react'
import { Layout } from 'antd'

export const Footer = () => {
  return (
    <Layout.Footer
     className="text-center"
    >
        布布博客 ©{new Date().getFullYear()} 由 leehainuo 创建
    </Layout.Footer>
  )
}
