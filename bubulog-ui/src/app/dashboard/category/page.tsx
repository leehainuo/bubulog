"use client"
import { AddCategory } from '@/components/ui/back/category/AddCategory'
import { QueryCategory } from '@/components/ui/back/category/QueryCategory'
import { TableCategory } from '@/components/ui/back/category/TableCategory'
import React from 'react'



export const CategoryPage = () => {


  return (
    <section
     className="flex flex-col"
    >
      <div className="flex items-center gap-8">
        <AddCategory />
        <div className="border-x h-6" />
        <QueryCategory />
      </div>
      <TableCategory />
    </section>
  )
}

export default CategoryPage