import Filter from 'src/component/Filter'
import Form from 'src/component/Form'
import { Accordion, CustomFlowbiteTheme, Flowbite } from 'flowbite-react'
const customTheme: CustomFlowbiteTheme = {
  accordion: {
    root: {
      base: 'divide-y divide-gray-200 border-gray-200 dark:divide-gray-700 dark:border-gray-700',
      flush: {
        off: 'rounded-lg border',
        on: 'border-b'
      }
    },
    content: {
      base: 'py-5 px-5 last:rounded-b-lg dark:bg-gray-900 first:rounded-t-lg'
    },
    title: {
      arrow: {
        base: 'h-6 w-6 shrink-0',
        open: {
          off: '',
          on: 'rotate-180'
        }
      },
      base: 'flex w-full items-center justify-between first:rounded-t-lg last:rounded-b-lg py-5 px-5 text-left font-medium text-gray-500 dark:text-gray-400',
      flush: {
        off: 'hover:bg-gray-100 focus:ring-4 focus:ring-gray-200 dark:hover:bg-gray-800 dark:focus:ring-gray-800',
        on: 'bg-transparent dark:bg-transparent'
      },
      heading: '',
      open: {
        off: '',
        on: 'text-gray-900 bg-gray-100 dark:bg-gray-800 dark:text-white'
      }
    }
  }
}
function ViewBuilding() {
  return (
    <div>
      <Flowbite theme={{ theme: customTheme }}>
        <Accordion>
          <Accordion.Panel>
            <Accordion.Title>Tìm kiếm</Accordion.Title>
            <Accordion.Content>
              <Filter />
            </Accordion.Content>
          </Accordion.Panel>
        </Accordion>
      </Flowbite>
    </div>
  )
}

export default ViewBuilding
