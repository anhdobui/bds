/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}', './node_modules/flowbite/**/*.js'],
  theme: {
    extend: {}
  },
  variants: {
    extend: {}
  },
  plugins: [require('flowbite/plugin')]
}
