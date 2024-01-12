import { useForm } from 'react-hook-form'
import Input from '../Input'

function FormFilterBuilding() {
  const {
    register,
    control,
    handleSubmit,
    formState: { errors }
  } = useForm({})
  const onSubmit = handleSubmit((data) => {
    console.log(data)
  })
  return (
    <form onSubmit={onSubmit}>
      <div className='mb-6 grid gap-6 md:grid-cols-6 '>
        <Input label='Tên tòa nhà' name='name' type='text' register={register} className='col-span-2' />
        <Input label='Diện tích sàn' name='floorarea' type='number' register={register} className='col-span-2' />
        <Input label='Phường' name='ward' type='text' register={register} className='col-span-2' />
        <Input label='Đường' name='ward' type='text' register={register} className='col-span-2' />
        <Input label='Số tầng hầm' name='ward' type='number' register={register} className='col-span-2' />
        <Input label='Hướng' name='ward' type='number' register={register} className='col-span-2' />
        <Input label='Hạng' name='ward' type='number' register={register} className='col-span-2' />
        <Input label='Diện tích từ' name='ward' type='number' register={register} className='col-span-2' />
        <Input label='Diện tích đến' name='ward' type='number' register={register} className='col-span-2' />
        <Input label='Giá thuê từ' name='ward' type='number' register={register} className='col-span-2' />
        <Input label='Giá thuê đến' name='ward' type='number' register={register} className='col-span-2' />
        <Input label='Tên quản lý' name='ward' type='number' register={register} className='col-span-2' />
        <Input label='Điện thoại quản lý' name='ward' type='number' register={register} className='col-span-2' />
      </div>
    </form>
  )
}

export default FormFilterBuilding
