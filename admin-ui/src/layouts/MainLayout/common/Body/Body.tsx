import Breadcrumb from 'src/component/Breadcrumbs'
import { ReactNode, useContext } from 'react'
import { useLocation } from 'react-router-dom'
import { HeadingContext } from 'src/contexts/heading.context'
import PageHeading from 'src/component/PageHeading'
type BodyType = {
  children?: ReactNode
}

function Body({ children }: BodyType) {
  const { heading } = useContext(HeadingContext)
  const location = useLocation()
  const pathParts = location.pathname.split('/').filter((item) => item != '')
  const breadcrumbItems = pathParts.map((item, index) => {
    let result: { label: string; link?: string } = {
      label: item.charAt(0).toUpperCase() + item.slice(1)
    }
    result = index === pathParts.length - 1 ? { ...result } : { ...result, link: `/${item}` }
    return result
  })
  breadcrumbItems.unshift({
    label: 'Home',
    link: ''
  })
  return (
    <div className='p-4 sm:ml-64'>
      <div className='mt-14'>
        <Breadcrumb items={breadcrumbItems} />
      </div>
      <PageHeading title={heading} />
      {children}
    </div>
  )
}

export default Body
