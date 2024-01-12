import { UseFormRegister } from 'react-hook-form'
import CheckBox from '../CheckBox'

export type GroupCheckBoxType = {
  dataCheckBoxs: {
    label: string
    value: string
  }[]
  register: UseFormRegister<any>
  name: string
  label: string
  className: string
  errorMessage?: string
}

function GroupCheckBox({ dataCheckBoxs, register, name, label, className, errorMessage }: GroupCheckBoxType) {
  return (
    <div className={className}>
      <label className='mb-2 block text-sm font-medium text-gray-900 dark:text-white'>{label}</label>
      <div className='grid gap-2 md:grid-cols-10'>
        {dataCheckBoxs &&
          dataCheckBoxs.map((item) => {
            return (
              <div key={item.value}>
                <CheckBox label={item.label} name={name} register={register} value={item.value} />
              </div>
            )
          })}
      </div>
      <div className='mt-1 min-h-[1.25rem] text-sm text-red-600'>{errorMessage}</div>
    </div>
  )
}

export default GroupCheckBox
