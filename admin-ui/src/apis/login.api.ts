import http from 'src/utils/http'

export const login = (body: { username: string; password: string }) => http.post<any>('/auth/authenticate', body)
