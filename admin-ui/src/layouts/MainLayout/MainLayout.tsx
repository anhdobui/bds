import { ReactNode } from 'react'
import Header from './common/Header'
import Sidebar from './common/Sidebar'
import Body from './common/Body'

type MainLayoutType = {
  children?: ReactNode
}
function MainLayout({ children }: MainLayoutType) {
  return (
    <div>
      <Header />
      <Sidebar />
      <Body>{children}</Body>
    </div>
  )
}

export default MainLayout
