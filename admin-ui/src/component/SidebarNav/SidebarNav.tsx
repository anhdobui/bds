import { useState } from 'react'
import Button from '../Button'

type SidebarNavType = {
  title: string
  renderSvg: () => React.ReactNode
  menu?: { path: string; title: string }[]
  path?: string
}
function SidebarNav({ title, path, renderSvg, menu }: SidebarNavType) {
  const [visibMenu, setVisibMenu] = useState<boolean>(false)
  return (
    <li>
      <Button
        onClick={() => setVisibMenu((prev) => !prev)}
        path={path}
        className='flex cursor-pointer items-center rounded-lg p-2 text-gray-900 hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700'
      >
        {renderSvg()}
        <span className='ml-3 flex-1 text-left'>{title}</span>
        {menu && (
          <svg className='h-6 w-6' fill='currentColor' viewBox='0 0 20 20' xmlns='http://www.w3.org/2000/svg'>
            <path
              fillRule='evenodd'
              d='M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z'
              clipRule='evenodd'
            />
          </svg>
        )}
      </Button>
      {menu && (
        <ul className={`${!visibMenu ? 'hidden' : ''} space-y-2 py-2`}>
          {menu.map((item) => (
            <li key={item.title}>
              <Button
                path={item.path}
                className='group flex w-full items-center rounded-lg p-2 pl-11 text-gray-900 transition duration-75 hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700'
              >
                {item.title}
              </Button>
            </li>
          ))}
        </ul>
      )}
    </li>
  )
}

export default SidebarNav
