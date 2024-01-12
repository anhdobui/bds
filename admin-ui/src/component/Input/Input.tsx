/* eslint-disable @typescript-eslint/no-explicit-any */
import { nanoid } from 'nanoid'
import { UseFormRegister } from 'react-hook-form'
import { forwardRef } from 'react'
export interface Props {
  className?: string
  type: React.HTMLInputTypeAttribute
  name: string
  placeholder?: string
  register?: UseFormRegister<any>
  errorMessage?: string | any
  autoComplete?: string
  label: string
  isrReadonly?: boolean
  isDisabled?: boolean
  value?: string
  ref?: any
}
const Input = forwardRef<HTMLInputElement, Props>(
  (
    { className, label, name, register, type, autoComplete, errorMessage, isDisabled, isrReadonly, placeholder, value },
    ref
  ) => {
    const id = nanoid()
    const isControl = register ? true : false
    const renderInput = (isControl: boolean) => {
      if (isControl && register) {
        return (
          <input
            readOnly={isrReadonly}
            disabled={isDisabled}
            type={type}
            autoComplete={autoComplete}
            id={id}
            className='block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400 dark:focus:border-blue-500 dark:focus:ring-blue-500'
            placeholder={placeholder}
            {...register(name)}
          />
        )
      }
      return (
        <input
          readOnly={isrReadonly}
          disabled={isDisabled}
          type={type}
          autoComplete={autoComplete}
          id={id}
          className='block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400 dark:focus:border-blue-500 dark:focus:ring-blue-500'
          placeholder={placeholder}
          value={value}
          ref={ref}
        />
      )
    }
    return (
      <div className={className}>
        <label htmlFor={id} className='mb-2 block text-sm font-medium text-gray-900 dark:text-white'>
          {label}
        </label>
        {renderInput(isControl)}
        <div className='mt-1 min-h-[1.25rem] text-sm text-red-600'>{errorMessage}</div>
      </div>
    )
  }
)
Input.displayName = 'Input'
export default Input
