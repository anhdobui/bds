import BreadcrumbItem from '../BreadcrumbItem'

type BreadcrumbType = {
  items?: { label: string; link?: string }[]
}
function Breadcrumb({ items }: BreadcrumbType) {
  return (
    <nav
      className='flex rounded-lg border border-gray-200 bg-gray-50 px-5 py-3 text-gray-700 dark:border-gray-700 dark:bg-gray-800'
      aria-label='Breadcrumb'
    >
      <ol className='inline-flex items-center space-x-1 md:space-x-3'>
        {items &&
          items.map((item, index) => {
            const isLastItem = index === items.length - 1
            const isLink = !isLastItem && item.link
            const position = !index ? 'first' : isLastItem && !isLink ? 'last' : 'middle'
            const renderSvg = () => {
              if (index === 0) {
                return (
                  <svg
                    aria-hidden='true'
                    className='mr-2 h-4 w-4'
                    fill='currentColor'
                    viewBox='0 0 20 20'
                    xmlns='http://www.w3.org/2000/svg'
                  >
                    <path d='M10.707 2.293a1 1 0 00-1.414 0l-7 7a1 1 0 001.414 1.414L4 10.414V17a1 1 0 001 1h2a1 1 0 001-1v-2a1 1 0 011-1h2a1 1 0 011 1v2a1 1 0 001 1h2a1 1 0 001-1v-6.586l.293.293a1 1 0 001.414-1.414l-7-7z' />
                  </svg>
                )
              }
              return (
                <svg
                  aria-hidden='true'
                  className='h-6 w-6 text-gray-400'
                  fill='currentColor'
                  viewBox='0 0 20 20'
                  xmlns='http://www.w3.org/2000/svg'
                >
                  <path
                    fillRule='evenodd'
                    d='M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z'
                    clipRule='evenodd'
                  />
                </svg>
              )
            }
            return (
              <li className='inline-flex items-center' key={index} aria-current={isLastItem ? 'page' : undefined}>
                <BreadcrumbItem label={item.label} position={position} link={item.link} renderSvg={() => renderSvg()} />
              </li>
            )
          })}
      </ol>
    </nav>
  )
}

export default Breadcrumb
