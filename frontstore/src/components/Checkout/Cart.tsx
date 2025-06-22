import { UpdateItem } from '@/api/cart';
import { useDeleteFromCart, useUpdateCart } from '@/hooks/useCart';
import { useCartStore } from '@/store/useCartStore';

import { Stepper, Table, Text, Image, NumberInput, Flex, Box, Space, Button, Card, Divider, CloseButton } from '@mantine/core';
import { IconArrowLeft } from '@tabler/icons-react';
import { useRouter, Link } from '@tanstack/react-router';

function Cart() {
  const {mutate: deleteFromCart} = useDeleteFromCart();
  const {mutate: updateCart} = useUpdateCart();
  const items = useCartStore(state => state.items);
  const originalPrice = useCartStore(state => state.originalPrice);
  const discountAmount = useCartStore(state => state.discountAmount);
  const finalPrice = useCartStore(state => state.finalPrice);
  const router = useRouter();

  return (
    <Box style={{paddingTop: 80}} >
      <Stepper active={0}>
        <Stepper.Step label="Giỏ hàng" description="Điều chỉnh giỏ hàng">
          <Space h="xl" />
          <Flex gap={40} direction="row">
            <Table>
               <Table.Thead>
                <Table.Th>Sản phẩm</Table.Th>
                <Table.Th w={120}>Đơn giá</Table.Th>
                <Table.Th w={100}>Số lượng</Table.Th>
                <Table.Th w={120}>Tổng Giá</Table.Th>
               </Table.Thead>
               <Table.Tbody>
                {items.map((row: any) => 
                  <Row
                    key={row.gameId}
                    deleteFromCart={deleteFromCart}
                    updateCart={updateCart}
                    rowData={row}
                  />
                )}
               </Table.Tbody>
            </Table>
            <OrderDetail data={{originalPrice, discountAmount, finalPrice}} />
          </Flex>
          <Space h="xl" />
          <Button onClick={() => router.history.back()}>
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

const OrderDetail = ({data}) => {
  const router = useRouter();

  return (
    <Box>
      <Card
        withBorder
        radius="md"
        padding="20"
        style={{width: 320}}
      >
        <Text size="xl" fw={700}>Tổng cộng giỏ hàng</Text>
        <Space h="sm" />
        <Divider />
        <Space h="sm" />
        <Flex justify="space-between">
          <Text size="xl">Giá gốc</Text>
          <Text size="xl">{data.originalPrice}</Text>
        </Flex>
        <Space h="sm" />
        {
          data.discountAmount > 0 &&
          <Box>
          <Divider />
          <Space h="sm" />
          <Flex justify="space-between">
            <Text size="xl">Giá giảm</Text>
            <Text size="xl">- {data.discountAmount}</Text>
          </Flex>
          </Box>
        }
        <Space h="sm" />
        <Divider />
        <Space h="sm" />
        <Flex justify="space-between">
          <Text size="xl">Tạm tính</Text>
          <Flex gap={12} align="center">
            <Text size="md" td="line-through" c="dimmed">{data.originalPrice}</Text>
            <Text size="xl">{data.finalPrice}</Text>
          </Flex>
        </Flex>
        <Space h="sm" />
        <Button onClick={()=>router.history.push('/payment')}>
          Tiến hành thanh toán
        </Button>
      </Card>
      <Space h="sm" />
      {/* <Flex gap={12} align="center"> */}
      {/*   <IconTagStarred /> */}
      {/*   <Text size="xl" fw={700}>Mã ưu đãi</Text> */}
      {/* </Flex> */}
      {/* <Divider /> */}
      {/* <Space h="sm" /> */}
      {/* <TextInput */}
      {/*   placeholder="Mã ưu đãi" */}
      {/* /> */}
      {/* <Space h="sm" /> */}
      {/* <Button fullWidth>Áp dụng</Button> */}
    </Box>
  )
}

const Row = ({rowData, deleteFromCart, updateCart}: any) => {
  let tempAmount;

  if (rowData.sale) {
    tempAmount = Math.round(rowData.price * rowData.quantity * (100 - rowData.sale.amount) / 100)
  } else {
    tempAmount = Math.round(rowData.price * rowData.quantity)
  }

  const onRemoveCartItem = (gameId: any) => {
    deleteFromCart(gameId);
  }

  const onQuantityChange = (quantity: number, gameId: number) => {
    const updateItem: UpdateItem = {
      quantity,
      gameId
    }
    updateCart(updateItem);
  }

  return (
    <Table.Tr key={rowData.gameId}>
      <Table.Td>
        <Flex gap="12" align="center">
          <Image h={80} w={60} src={rowData.img} />
          <Text size="md" fw={700}>{rowData.title}</Text>
        </Flex>
      </Table.Td>
      <Table.Td>{rowData.price}</Table.Td>
      <Table.Td>
        <NumberInput onChange={(quantity: any) => onQuantityChange(quantity, rowData.gameId)} 
          value={rowData.quantity}
        />
      </Table.Td>
      <Table.Td>{tempAmount}</Table.Td>
      <CloseButton onClick={(e)=>{e.preventDefault();onRemoveCartItem(rowData.gameId)}} />
    </Table.Tr>
  )
}


