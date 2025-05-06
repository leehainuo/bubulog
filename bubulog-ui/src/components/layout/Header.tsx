import { LibraryBigIcon } from 'lucide-react'
import Link from 'next/link'
import React from 'react'
import { Nav } from '../ui/Nav'
import { Avatar } from '../ui/Avatar'

export const Header = () => {
  return (
    <header
     className="flex items-center h-[60px] bg-card"
    >
        {/* Logo */}
        <Link
         href="/"
         className="flex justify-center items-center gap-x-2 text-xl py-4 px-8 text-primary
          font-[family-name:var(--font-geist-sans)] font-medium tracking-tight"
        >
            <LibraryBigIcon />
            <span>布布</span>
        </Link>
        {/* Nav */}
        <Nav />
        {/* Avatar */}
        <Avatar />
    </header>
  )
}
