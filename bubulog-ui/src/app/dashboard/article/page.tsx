import { AddArticle } from '@/components/ui/back/article/AddArticle'
import { QueryArticle } from '@/components/ui/back/article/QueryArticle'
import { TableArticle } from '@/components/ui/back/article/TableArticle'
import React from 'react'

export default function ArticlePage() {
  return (
    <section
    className="flex flex-col"
   >
     <div className="flex items-center gap-8">
       <AddArticle />
       <div className="border-x h-6" />
       <QueryArticle />
     </div>
     <TableArticle />
   </section>
  )
}