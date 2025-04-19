import { Stepper, Table, Text, Image, NumberInput, Flex, Box, Space, Button, Card, Divider, TextInput } from '@mantine/core';
import { IconArrowLeft, IconTagStarred } from '@tabler/icons-react';

function Cart() {
  return (
    <Box style={{paddingTop: 80}} >
      <Stepper active={0}>
        <Stepper.Step label="Giỏ hàng" description="Điều chỉnh giỏ hàng">
          <Space h="xl" />
          <Flex gap={40} direction="row">
            <Table>
               <Table.Thead>
                <Table.Th>San pham</Table.Th>
                <Table.Th>Gia</Table.Th>
                <Table.Th>So luong</Table.Th>
                <Table.Th>Tam tinh</Table.Th>
               </Table.Thead>
               <Table.Tbody>
                {rows.map(row => <Row rowData={row} />)}
               </Table.Tbody>
            </Table>
            <OrderDetail />
          </Flex>
          <Button>
            <IconArrowLeft />
            Tiếp tục xem sản phẩm
          </Button>
        </Stepper.Step>
        <Stepper.Step label="Chi tiết thanh toán" description="Chi tiết thanh toán">
        </Stepper.Step>
        <Stepper.Step label="Đơn hàng hoàn tất" description="Đơn hàng hoàn tất">
        </Stepper.Step>
      </Stepper>
    </Box>
  );
}

export default Cart

const OrderDetail = () => {
  return (
    <Box>
      <Card
        withBorder
        radius="md"
        padding="20"
        style={{width: 400}}
      >
        <Text size="xl" fw={700}>Tổng cộng giỏ hàng</Text>
        <Space h="sm" />
        <Divider />
        <Space h="sm" />
        <Flex justify="space-between">
          <Text size="xl">Tạm tính</Text>
          <Text size="xl">590.000</Text>
        </Flex>
        <Space h="sm" />
        <Divider />
        <Space h="sm" />
        <Flex justify="space-between">
          <Text size="xl">Tổng</Text>
          <Text size="xl">590.000</Text>
        </Flex>
        <Space h="sm" />
        <Button>Tiến hành thanh toán</Button>
      </Card>
      <Space h="sm" />
      <Flex gap={12} align="center">
        <IconTagStarred />
        <Text size="xl" fw={700}>Mã ưu đãi</Text>
      </Flex>
      <Divider />
      <Space h="sm" />
      <TextInput
        placeholder="Mã ưu đãi"
      />
      <Space h="sm" />
      <Button fullWidth>Áp dụng</Button>
    </Box>
  )
}

const Row = ({rowData}: any) => {
  return (
    <Table.Tr key={rowData.id}>
      <Table.Td>
        <Flex gap="12" align="center">
          <Image height="80" src={rowData.img} />
          <Text size="xl" fw={700}>{rowData.title}</Text>
        </Flex>
      </Table.Td>
      <Table.Td>{rowData.price}</Table.Td>
      <Table.Td>
        <NumberInput
          value={rowData.amount}
        />
      </Table.Td>
      <Table.Td>
        <Text>{rowData.temporary_calculation}</Text>
      </Table.Td>
    </Table.Tr>
  )
}

const rows = Array(2).fill(0).map(_=>({
  id: "2",
  img: "https://muaga.me/wp-content/uploads/2020/02/xsplit-premium-1-247x296.png.webp",
  title: " XSplit Premium 1 year",
  price: "590.000",
  amount: 1,
  temporary_calculation: "590.000"
}))

