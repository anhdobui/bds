import { useForm } from 'react-hook-form'
import * as yup from 'yup'
import { yupResolver } from '@hookform/resolvers/yup'
import FormFilterBuilding from '../Form/FormFilterBuilding'
export type FormValues = {
  name?: string
  email?: string
}
const schema = yup.object({
  name: yup.string(),
  email: yup.string()
})

function Filter() {
  const {
    register,
    control,
    handleSubmit,
    formState: { errors }
  } = useForm<FormValues>({
    resolver: yupResolver(schema)
  })
  const onSubmit = handleSubmit((data) => {
    console.log(data)
  })
  return (
    <div>
      <FormFilterBuilding />
    </div>
  )
}

export default Filter
