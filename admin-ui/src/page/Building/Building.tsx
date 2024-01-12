import { Outlet } from 'react-router-dom'

function Building() {
  return (
    <div className='p-6 bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700'>
      <Outlet />
    </div>
  )
}

export default Building
