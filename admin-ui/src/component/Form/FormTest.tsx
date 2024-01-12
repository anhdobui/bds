import { yupResolver } from '@hookform/resolvers/yup'

import Input from '../Input'
import Select from '../Select'
import InputFile from '../InputFile'
import {
  Control,
  Controller,
  FieldErrors,
  FieldValues,
  Resolver,
  UseFormHandleSubmit,
  UseFormRegister,
  UseFormWatch,
  useForm
} from 'react-hook-form'
import { Props as InputBaseInfer } from '../Input/Input'
import { useRef } from 'react'
import { InputFileInfer } from '../InputFile/InputFile'
import CheckBox from '../CheckBox'
import GroupCheckBox from '../GroupCheckBox'
import { GroupCheckBoxType } from '../GroupCheckBox/GroupCheckBox'

type DesignFormElementBase = {
  type: 'input-text' | 'input-number' | 'input-email' | 'file' | 'checkbox'
  name: string
  label: string
  placeholder?: string
  className?: string
  isValidation?: boolean
  'col-size': 1 | 2 | 3 | 4 | 5 | 6
}
type DesignFormElementInput = DesignFormElementBase & {
  isControl: boolean
  autoComplete?: string
  isDisabled?: boolean
  isrReadonly?: boolean
}
type DesignFormElementCheckBoxGroup = DesignFormElementBase & {
  dataCheckBoxs: {
    label: string
    value: string
  }[]
}
type DesignFormElementFile = DesignFormElementBase & { multiple: boolean }
type DesignFormElement =
  | {
      type: 'none'
      'col-size': 1 | 2 | 3 | 4 | 5 | 6
    }
  | DesignFormElementInput
  | DesignFormElementFile
  | DesignFormElementCheckBoxGroup
export type DesignForm = DesignFormElement[]

type FormType<T extends FieldValues> = {
  register: UseFormRegister<T>
  control: Control<T, any>
  errors: FieldErrors<T>
  designForm: DesignForm
  watch?: UseFormWatch<FieldValues>
  submital: string
  onSubmit: (e?: React.BaseSyntheticEvent<object, any, any> | undefined) => Promise<void>
}
const designForm: DesignForm = [
  {
    type: 'input-text',
    name: 'name',
    label: 'Tên sản phẩm',
    isValidation: true,
    placeholder: '',
    className: '',
    isControl: true,
    'col-size': 3
  },
  {
    type: 'input-text',
    name: 'floorarea',
    label: 'Diện tích sàn',
    isValidation: true,
    placeholder: '',
    className: '',
    isControl: true,
    'col-size': 3
  },
  {
    type: 'input-text',
    name: 'ward',
    label: 'Phường',
    isValidation: true,
    placeholder: '',
    className: '',
    isControl: true,
    'col-size': 2
  },
  {
    type: 'input-text',
    name: 'street',
    label: 'Đường',
    isValidation: true,
    placeholder: '',
    className: '',
    isControl: true,
    'col-size': 2
  },
  {
    type: 'input-text',
    name: 'direction',
    label: 'Hướng',
    isValidation: true,
    placeholder: '',
    className: '',
    isControl: true,
    'col-size': 2
  },
  {
    type: 'input-text',
    name: 'level',
    label: 'Hạng',
    isValidation: true,
    placeholder: '',
    className: '',
    isControl: true,
    'col-size': 2
  },
  {
    type: 'input-number',
    name: 'rentareaTo',
    label: 'Diện tích từ',
    isValidation: true,
    placeholder: '',
    className: '',
    isControl: true,
    'col-size': 2
  },
  {
    type: 'input-number',
    name: 'rentareaFrom',
    label: 'Diện tích đến',
    isValidation: true,
    placeholder: '',
    className: '',
    isControl: true,
    'col-size': 2
  },
  {
    type: 'input-number',
    name: 'costRentTo',
    label: 'Giá thuê từ',
    isValidation: true,
    placeholder: '',
    className: '',
    isControl: true,
    'col-size': 2
  },
  {
    type: 'input-number',
    name: 'costRentFrom',
    label: 'Giá thuê đến',
    isValidation: true,
    placeholder: '',
    className: '',
    isControl: true,
    'col-size': 2
  },
  {
    type: 'none',
    'col-size': 2
  },
  {
    type: 'checkbox',
    name: 'type',
    label: 'Loại tòa nhà',
    isValidation: false,
    dataCheckBoxs: [
      {
        label: 'Tầng trệt',
        value: 'TANG-TRET'
      },
      {
        label: 'Nội thất',
        value: 'NOI-THAT'
      },
      {
        label: 'Nguyên căn',
        value: 'NGUYEN-CAN'
      }
    ],
    'col-size': 6
  }
]
function Form<T extends FieldValues>({
  register,
  control,
  onSubmit,
  errors,
  designForm,
  watch,
  submital
}: FormType<T>) {
  const uncontrolledInputRef = useRef<HTMLInputElement>(null)
  return (
    <div>
      <form onSubmit={onSubmit}>
        <div className='mb-6 grid grid-cols-6 gap-6 md:grid-cols-6 '>
          {designForm &&
            designForm.map((item, index) => {
              let props: FieldValues | InputBaseInfer | InputFileInfer | GroupCheckBoxType = {}
              let Comp: (props: any) => any = () => {}
              if (item.type !== 'none') {
                if (item.isValidation) {
                  if (errors?.[item.name]?.message) {
                    props = { errorMessage: errors?.[item.name]?.message }
                  }
                }
                props = { ...props, label: item.label, name: item.name, className: item.className }

                if (item.type.startsWith('input')) {
                  const typeInput = item.type.split('-')
                  Comp = Input
                  ;(item = item as DesignFormElementInput),
                    (props = {
                      ...props,
                      type: typeInput[1],
                      autoComplete: item.autoComplete,
                      isDisabled: item.isDisabled,
                      isrReadonly: item.isrReadonly,
                      placeholder: item.placeholder
                    })
                  if (item.isControl) {
                    props = { ...props, register }
                  } else {
                    props = {
                      ...props,
                      value: watch && (watch(item.name) ? watch(item.name) : ''),
                      ref: uncontrolledInputRef
                    }
                  }
                } else {
                  if (item.type === 'file') {
                    Comp = InputFile
                    item = item as DesignFormElementFile
                    props = { ...props, multiple: item.multiple, register }
                  } else if (item.type === 'checkbox') {
                    Comp = GroupCheckBox
                    item = item as DesignFormElementCheckBoxGroup
                    props = { ...props, dataCheckBoxs: item.dataCheckBoxs, register: register }
                  }
                }
              }
              const classGrid = `col-span-${item['col-size']} md:col-span-${item['col-size']}`
              return (
                <div key={index + item.type + item['col-size']} className={classGrid}>
                  {item.type !== 'none' && <Comp {...props} />}
                </div>
              )
            })}
        </div>

        <button
          type='submit'
          className='w-full rounded-lg bg-blue-700 px-5 py-2.5 text-center text-sm font-medium text-white hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800 sm:w-auto mb-6'
        >
          {submital}
        </button>
      </form>
    </div>
  )
}

export default Form
