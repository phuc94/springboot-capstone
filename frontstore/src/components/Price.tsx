import { NumberFormatter } from "@mantine/core"

const Price = ({value}: any) => {
  return (
    <NumberFormatter
      suffix=" ₫"
      value={value}
      thousandSeparator
    />
  )
}

export default Price

