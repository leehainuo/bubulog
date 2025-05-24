import React from 'react'
import { Layout } from 'antd'

export const Footer = () => {
  return (
    <Layout.Footer
     className="text-center"
    >
        Pop Lab ©{new Date().getFullYear()} Created by leehainuo
    </Layout.Footer>
  )
}
