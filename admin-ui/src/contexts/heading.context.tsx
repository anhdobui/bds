import { ReactNode, createContext, useState } from 'react'

interface HeadingContextInterface {
  heading: string | null
  setHeading: React.Dispatch<React.SetStateAction<string | null>>
}
const initialHeadingContext: HeadingContextInterface = {
  heading: null,
  setHeading: () => null
}
export const HeadingContext = createContext<HeadingContextInterface>(initialHeadingContext)

export const HeadingProvider = ({ children }: { children: ReactNode }) => {
  const [heading, setHeading] = useState<string | null>(initialHeadingContext.heading)
  return (
    <HeadingContext.Provider
      value={{
        heading,
        setHeading
      }}
    >
      {children}
    </HeadingContext.Provider>
  )
}
