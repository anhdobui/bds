import { Link } from 'react-router-dom'
interface ButtonInterface {
  className?: string
  path?: string
  children?: React.ReactNode
  [key: string]: any
}
function Button({ className, path, children, ...passport }: ButtonInterface) {
  const Comp = path ? Link : 'button'
  const props = {
    ...passport
  }
  return (
    <Comp to={path ? path : ''} className={`${className} w-full`} {...props}>
      {children}
    </Comp>
  )
}

export default Button
