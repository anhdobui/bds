type PageHeadingType = {
  title?: string | null
}
function PageHeading({ title }: PageHeadingType) {
  const currentDate = new Date()
  const options: Intl.DateTimeFormatOptions = { day: '2-digit', month: 'short', year: 'numeric' }
  const formattedDate = currentDate.toLocaleDateString('en-GB', options)
  return (
    <>
      <div className=' mb-6 mt-6 mx-auto flex flex-col items-start justify-between border-b border-gray-300 px-6 pb-4 md:flex-row md:items-center lg:mb-12 lg:mt-8'>
        <div>
          {title && (
            <h4 className='text-2xl font-bold uppercase leading-tight tracking-widest text-gray-800 dark:text-gray-100'>
              Danh mục: {title}
            </h4>
          )}

          <ul
            aria-label='current Status'
            className='mt-3 flex flex-col items-start text-sm text-gray-600 dark:text-gray-400 md:flex-row md:items-center'
          >
            <li className='mr-4 flex items-center'>
              <div className='mr-1'>
                <img
                  className='dark:hidden'
                  src='https://tuk-cdn.s3.amazonaws.com/can-uploader/simple_with_sub_text_and_border-svg1.svg'
                  alt='Active'
                />
                <img
                  className='hidden dark:block'
                  src='https://tuk-cdn.s3.amazonaws.com/can-uploader/simple_with_sub_text_and_border-svg1dark.svg'
                  alt='Active'
                />
              </div>
              <span>Active</span>
            </li>
            {/* <li className="mr-4 mt-4 flex items-center md:mt-0">
            <div className="mr-1">
              <img
                className="dark:hidden"
                src="https://tuk-cdn.s3.amazonaws.com/can-uploader/simple_with_sub_text_and_border-svg2.svg"
                alt="Trending"
              />
              <img
                className="hidden dark:block"
                src="https://tuk-cdn.s3.amazonaws.com/can-uploader/simple_with_sub_text_and_border-svg2dark.svg"
                alt="Trending"
              />
            </div>
            <span> Trending</span>
          </li> */}
            <li className='mt-4 flex items-center md:mt-0'>
              <div className='mr-1'>
                <img
                  className='dark:hidden'
                  src='https://tuk-cdn.s3.amazonaws.com/can-uploader/simple_with_sub_text_and_border-svg3.svg'
                  alt='date'
                />
                <img
                  className='hidden dark:block'
                  src='https://tuk-cdn.s3.amazonaws.com/can-uploader/simple_with_sub_text_and_border-svg3dark.svg'
                  alt='date'
                />
              </div>
              <span>Started on {formattedDate}</span>
            </li>
          </ul>
        </div>
        {/* <div className="mt-6 md:mt-0">
        <button className="mr-3 rounded bg-gray-200 px-5 py-2 text-sm text-indigo-700 transition duration-150 ease-in-out hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-indigo-700 focus:ring-offset-2 dark:bg-gray-700 dark:text-indigo-600 dark:hover:bg-gray-600">
          Back
        </button>
        <button className="rounded bg-indigo-700 px-8 py-2 text-sm text-white transition duration-150 ease-in-out hover:bg-indigo-600 focus:outline-none focus:ring-2 focus:ring-indigo-700 focus:ring-offset-2">
          Edit Profile
        </button>
      </div> */}
      </div>
    </>
  )
}

export default PageHeading
