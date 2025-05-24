import { AddTag } from '@/components/ui/back/tag/AddTag'
import { QueryTag } from '@/components/ui/back/tag/QueryTag'
import React from 'react'

export const TagPage = () => {
  return (
    <section
    className="flex flex-col"
   >
     <div className="flex items-center gap-8">
       <AddTag />
       <div className="border-x h-6" />
       <QueryTag />
     </div>
   </section>
  )
}

export default TagPage