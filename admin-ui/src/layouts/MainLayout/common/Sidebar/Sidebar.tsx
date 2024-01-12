import SidebarNav from 'src/component/SidebarNav'

function Sidebar() {
  return (
    <aside
      id='logo-sidebar'
      className='fixed top-0 left-0 z-40 h-screen w-64 -translate-x-full border-r border-gray-200 bg-white pt-20 transition-transform dark:border-gray-700 dark:bg-gray-800 sm:translate-x-0'
      aria-label='Sidebar'
    >
      <div className='h-full overflow-y-auto bg-white px-3 pb-4 dark:bg-gray-800'>
        <ul className='space-y-2 font-medium'>
          <SidebarNav
            path='/'
            title='Dashboard'
            renderSvg={() => (
              <svg
                aria-hidden='true'
                className='h-6 w-6 text-gray-500 transition duration-75 group-hover:text-gray-900 dark:text-gray-400 dark:group-hover:text-white'
                fill='currentColor'
                viewBox='0 0 20 20'
                xmlns='http://www.w3.org/2000/svg'
              >
                <path d='M2 10a8 8 0 018-8v8h8a8 8 0 11-16 0z' />
                <path d='M12 2.252A8.014 8.014 0 0117.748 8H12V2.252z' />
              </svg>
            )}
          />
          <SidebarNav
            path='/user'
            title='Thành viên'
            renderSvg={() => (
              <svg
                aria-hidden='true'
                className='h-6 w-6 flex-shrink-0 text-gray-500 transition duration-75 group-hover:text-gray-900 dark:text-gray-400 dark:group-hover:text-white'
                fill='currentColor'
                viewBox='0 0 20 20'
                xmlns='http://www.w3.org/2000/svg'
              >
                <path fillRule='evenodd' d='M10 9a3 3 0 100-6 3 3 0 000 6zm-7 9a7 7 0 1114 0H3z' clipRule='evenodd' />
              </svg>
            )}
          />
          <SidebarNav
            path='/building'
            title='Tòa nhà'
            renderSvg={() => (
              <svg xmlns='http://www.w3.org/2000/svg' height={24} width={24} viewBox='0 0 384 512'>
                {/*!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.*/}
                <path d='M48 0C21.5 0 0 21.5 0 48V464c0 26.5 21.5 48 48 48h96V432c0-26.5 21.5-48 48-48s48 21.5 48 48v80h96c26.5 0 48-21.5 48-48V48c0-26.5-21.5-48-48-48H48zM64 240c0-8.8 7.2-16 16-16h32c8.8 0 16 7.2 16 16v32c0 8.8-7.2 16-16 16H80c-8.8 0-16-7.2-16-16V240zm112-16h32c8.8 0 16 7.2 16 16v32c0 8.8-7.2 16-16 16H176c-8.8 0-16-7.2-16-16V240c0-8.8 7.2-16 16-16zm80 16c0-8.8 7.2-16 16-16h32c8.8 0 16 7.2 16 16v32c0 8.8-7.2 16-16 16H272c-8.8 0-16-7.2-16-16V240zM80 96h32c8.8 0 16 7.2 16 16v32c0 8.8-7.2 16-16 16H80c-8.8 0-16-7.2-16-16V112c0-8.8 7.2-16 16-16zm80 16c0-8.8 7.2-16 16-16h32c8.8 0 16 7.2 16 16v32c0 8.8-7.2 16-16 16H176c-8.8 0-16-7.2-16-16V112zM272 96h32c8.8 0 16 7.2 16 16v32c0 8.8-7.2 16-16 16H272c-8.8 0-16-7.2-16-16V112c0-8.8 7.2-16 16-16z' />
              </svg>
            )}
          />
        </ul>
      </div>
    </aside>
  )
}

export default Sidebar
