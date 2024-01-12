import { yupResolver } from '@hookform/resolvers/yup'
import { useMutation } from '@tanstack/react-query'
import { useContext, useMemo } from 'react'
import { useForm } from 'react-hook-form'
import { useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import { login } from 'src/apis/login.api'
import { AppContext } from 'src/contexts/app.context'
import { LoginForm, LoginResponse } from 'src/types/login.type'
import { isAxiosError } from 'src/utils/utils'
import * as yup from 'yup'

const schema = yup
  .object({
    username: yup.string().required('Bạn cần nhập username'),
    password: yup.string().required('Bạn cần nhập password')
  })
  .required()

type FormError = {
  type: string
  title: string
  status: number
  detail: string
  instance: string
  access_denied_reason: string
}
function Login() {
  const navigate = useNavigate()
  const { setIsAuthenticated } = useContext(AppContext)
  const {
    register,
    handleSubmit,
    formState: { errors }
  } = useForm({
    resolver: yupResolver(schema)
  })
  const { mutate, error, data, reset } = useMutation({
    mutationFn: (body: LoginForm) => login(body)
  })
  const errorForm = useMemo(() => {
    if (isAxiosError<FormError>(error) && error.response?.status === 401) {
      return error.response.data.access_denied_reason
    }
  }, [error])
  const onSubmit = handleSubmit((formData) => {
    mutate(formData, {
      onSuccess: ({ data }: { data: LoginResponse }) => {
        toast.success('Đăng nhập thành công')
        setIsAuthenticated(true)
        console.log(data.token)
        navigate('/')
      }
    })
  })
  return (
    <div className='flex min-h-screen flex-col justify-center bg-gray-100 py-6 sm:py-12'>
      <div className='relative py-3 sm:mx-auto sm:max-w-xl'>
        <div className='absolute inset-0 -skew-y-6 transform bg-gradient-to-r from-blue-300 to-blue-600 shadow-lg sm:-rotate-6 sm:skew-y-0 sm:rounded-3xl'></div>
        <div className='relative min-w-[425px] bg-white px-4 py-10 shadow-lg sm:rounded-3xl sm:p-20'>
          <div className='mx-auto max-w-md'>
            <div>
              <h1 className='text-2xl font-semibold'>Login Admin </h1>
            </div>
            <div className='divide-y divide-gray-200'>
              <div className='space-y-4 py-8 text-base leading-6 text-gray-700 sm:text-lg sm:leading-7'>
                <form onSubmit={onSubmit}>
                  <div className='relative mb-2'>
                    <input
                      autoComplete='off'
                      type='text'
                      className='focus:borer-rose-600 peer h-10 w-full border-b-2 border-gray-300 text-gray-900 placeholder-transparent focus:outline-none'
                      placeholder='Email address'
                      {...register('username')}
                      onChange={() => {
                        if (error || data) {
                          reset()
                        }
                      }}
                    />
                    <label
                      htmlFor='email'
                      className='peer-placeholder-shown:text-gray-440 absolute left-0 -top-3.5 text-sm text-gray-600 transition-all peer-placeholder-shown:top-2 peer-placeholder-shown:text-base peer-focus:-top-3.5 peer-focus:text-sm peer-focus:text-gray-600'
                    >
                      Username
                    </label>
                  </div>
                  {!errorForm && (
                    <div className='mt-1 min-h-[1.25rem] text-sm text-red-600 pb-2'>{errors.username?.message}</div>
                  )}

                  {errorForm && (
                    <div className='mt-1 min-h-[1.25rem] text-sm text-red-600 pb-2'>
                      Username hoặc password không chính xác
                    </div>
                  )}
                  <div className='relative mb-1'>
                    <input
                      autoComplete='off'
                      type='password'
                      className='focus:borer-rose-600 peer h-10 w-full border-b-2 border-gray-300 text-gray-900 placeholder-transparent focus:outline-none'
                      placeholder='Password'
                      {...register('password')}
                      onChange={() => {
                        if (error || data) {
                          reset()
                        }
                      }}
                    />
                    <label
                      htmlFor='password'
                      className='peer-placeholder-shown:text-gray-440 absolute left-0 -top-3.5 text-sm text-gray-600 transition-all peer-placeholder-shown:top-2 peer-placeholder-shown:text-base peer-focus:-top-3.5 peer-focus:text-sm peer-focus:text-gray-600'
                    >
                      Password
                    </label>
                  </div>
                  <div className='mt-1 min-h-[1.25rem] text-sm text-red-600'>{errors.password?.message}</div>
                  <div className='relative'>
                    <button className='rounded-md bg-blue-500 px-2 py-1 text-white'>Login</button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Login
