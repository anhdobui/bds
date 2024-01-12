export const setAccessTokenToLS = (access_token: string) => {
  localStorage.setItem('access_token', access_token)
}
export const clearLS = () => {
  localStorage.removeItem('access_token')
}
export const getAccessTokenToLS = () => {
  return localStorage.getItem('access_token') || ''
}
