import { ReactNode } from 'react'
import { Link } from 'react-router-dom'
type BreadcrumbItemType = {
  renderSvg?: () => ReactNode
  position: 'first' | 'middle' | 'last'
  label: string
  link?: string
}
function BreadcrumbItem({ renderSvg, position = 'middle', label, link = '' }: BreadcrumbItemType) {
  const renderResult = (position: 'first' | 'middle' | 'last') => {
    switch (position) {
      case 'first':
        return (
          <Link
            to={link}
            className='inline-flex items-center text-sm font-medium text-gray-700 hover:text-blue-600 dark:text-gray-400 dark:hover:text-white'
          >
            {renderSvg && renderSvg()}
            {label}
          </Link>
        )
      case 'middle':
        return (
          <div className='flex items-center'>
            {renderSvg && renderSvg()}
            <Link
              to={link}
              className='ml-1 text-sm font-medium text-gray-700 hover:text-blue-600 dark:text-gray-400 dark:hover:text-white md:ml-2'
            >
              {label}
            </Link>
          </div>
        )
      case 'last':
        return (
          <div className='flex items-center'>
            {renderSvg && renderSvg()}
            <span className='ml-1 text-sm font-medium text-gray-500 dark:text-gray-400 md:ml-2'>{label}</span>
          </div>
        )
    }
  }
  const result = renderResult(position)
  return result
}

export default BreadcrumbItem
