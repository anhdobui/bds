import { UseFormRegister } from 'react-hook-form'
import { nanoid } from 'nanoid'
export type CheckBoxType = {
  name: string
  label: string
  register: UseFormRegister<any>
  value: string
}
function CheckBox({ name, label, register, value }: CheckBoxType) {
  const id = nanoid()
  return (
    <div className='flex items-center mb-4'>
      <input
        id={id}
        type='checkbox'
        className='w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600'
        value={value}
        {...register(name)}
      />
      <label htmlFor={id} className='ms-2 text-sm font-medium text-gray-900 dark:text-gray-300'>
        {label}
      </label>
    </div>
  )
}

export default CheckBox
