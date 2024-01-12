import axios, { AxiosInstance } from 'axios'
import { clearLS, getAccessTokenToLS, setAccessTokenToLS } from './auth'

class Http {
  instance: AxiosInstance
  private token: string
  constructor() {
    this.token = getAccessTokenToLS()
    this.instance = axios.create({
      baseURL: 'http://localhost:8080/api',
      timeout: 10000,
      headers: {
        'Content-Type': 'application/json'
      }
    })
    this.instance.interceptors.request.use(
      (config) => {
        const { url } = config

        if (this.token && url != '/auth/authenticate') {
          config.headers.Authorization = `Bearer ${this.token}`
          return config
        }
        return config
      },
      (error) => {
        return Promise.reject(error)
      }
    )
    this.instance.interceptors.response.use((response) => {
      const { url } = response.config
      if (url === '/auth/authenticate') {
        const data: { token: string } = response.data
        this.token = data.token
        setAccessTokenToLS(this.token)
      } else if (url === 'logout') {
        this.token = ''
        clearLS()
      }
      return response
    })
  }
}
const http = new Http().instance
export default http
