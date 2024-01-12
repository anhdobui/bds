import { useRoutes } from 'react-router-dom'
import MainLayout from './layouts/MainLayout'
import Home from './page/Home'
import Login from './page/Login'
import Building from './page/Building'
import User from './page/User'
import ViewBuilding from './page/Building/ViewBuilding'
import EditBuilding from './page/Building/EditBuilding'

function useRouteElements() {
  const routeElements = useRoutes([
    {
      path: '',
      index: true,
      element: (
        <MainLayout>
          <Home />
        </MainLayout>
      )
    },
    {
      path: '/building',
      element: (
        <MainLayout>
          <Building />
        </MainLayout>
      ),
      children: [
        {
          path: '',
          index: true,
          element: <ViewBuilding />
        },
        {
          path: 'add',
          index: true,
          element: <EditBuilding />
        },
        {
          path: 'edit/:id',
          index: true,
          element: <EditBuilding />
        }
      ]
    },
    {
      path: '/user',
      element: (
        <MainLayout>
          <User />
        </MainLayout>
      )
    },
    {
      path: '/login',
      element: (
        <div>
          <Login />
        </div>
      )
    }
  ])
  return routeElements
}

export default useRouteElements
