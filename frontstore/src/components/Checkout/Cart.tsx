import { useCart, useDeleteFromCart } from '@/hooks/useCart';
import { queryClient } from '@/routes/__root';
import { Stepper, Table, Text, Image, NumberInput, Flex, Box, Space, Button, Card, Divider, TextInput, CloseButton } from '@mantine/core';
import { IconArrowLeft, IconTagStarred } from '@tabler/icons-react';
import { useEffect } from 'react';

function Cart() {
  const query = useCart();

  useEffect(()=>{
    if (query.isSuccess) {
      console.log(query.data);
    }
  },[])

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
                {query.data?.data?.cartDetail.map((row: any) => <Row key={row.gameId} rowData={row} />)}
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
          <Text>abc</Text>     
        </Stepper.Step>
        <Stepper.Step label="Đơn hàng hoàn tất" description="Đơn hàng hoàn tất">
          <Text>abc</Text>     
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
  const {mutate: deleteFromCart, isSuccess} = useDeleteFromCart();
  let tempAmount;
  if (rowData.sale) {
    tempAmount = rowData.price * rowData.quantity * (100 - rowData.sale) * 100
  } else {
    tempAmount = rowData.price * rowData.quantity
  }

  const onRemoveCartItem = (gameId: any) => {
    deleteFromCart(gameId);
  }
  if (isSuccess) {
    queryClient.invalidateQueries({ queryKey: ['cart'] })
  }

  return (
    <Table.Tr key={rowData.gameId}>
      <Table.Td>
        <Flex gap="12" align="center">
          <Image height="80" src={rowData.img} />
          <Text size="xl" fw={700}>{rowData.title}</Text>
        </Flex>
      </Table.Td>
      <Table.Td>{rowData.price}</Table.Td>
      <Table.Td>
        <NumberInput
          value={rowData.quantity}
        />
      </Table.Td>
      <Table.Td>
        <Text>{tempAmount}</Text>
      </Table.Td>
      <CloseButton onClick={(e)=>{e.preventDefault();onRemoveCartItem(rowData.gameId)}} />
    </Table.Tr>
  )
}


