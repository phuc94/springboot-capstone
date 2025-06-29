import { UpdateItem } from '@/api/cart';
import { useDeleteFromCart, useUpdateCart } from '@/hooks/useCart';
import { useCartStore } from '@/store/useCartStore';

import { Stepper, Table, Text, Image, NumberInput, Flex, Box, Space, Button, Card, Divider, CloseButton, Center } from '@mantine/core';
import { IconArrowLeft } from '@tabler/icons-react';
import { useRouter } from '@tanstack/react-router';
import Price from '../Price';

function Cart() {
  const {mutate: deleteFromCart} = useDeleteFromCart();
  const {mutate: updateCart} = useUpdateCart();
  const cartStoreState = useCartStore.getState();
  const cartStore = useCartStore(); // work around for re-render
  const router = useRouter();

  return (
    <Box style={{paddingTop: 80}} >
      <Stepper active={0}>
        <Stepper.Step label="Giỏ hàng" description="Điều chỉnh giỏ hàng">
          <Space h="xl" />
          {
            cartStoreState.items.length === 0 ?
            <Flex direction="column" justify="center" align="center" gap={28}>
              <Space h="xl" />
              <Text size="xl">Giỏ hàng hiện chưa có sản phẩm nào.</Text>
              <Space h="md" />
              <Button onClick={() => router.history.back()}>
                <IconArrowLeft />
                Tiếp tục xem sản phẩm
              </Button>
            </Flex>
              :
              <Box>
                <Flex gap={40} direction="row">
                  <Table style={{height: 'fit-content'}}>
                     <Table.Thead>
                      <Table.Th>Sản phẩm</Table.Th>
                      <Table.Th w={120}>Đơn giá</Table.Th>
                      <Table.Th w={120}>Sale</Table.Th>
                      <Table.Th w={100}>Số lượng</Table.Th>
                      <Table.Th w={120}>Tổng Giá</Table.Th>
                     </Table.Thead>
                     <Table.Tbody>
                      {cartStoreState.items.map((row: any) => 
                        <Row
                          key={row.gameId}
                          deleteFromCart={deleteFromCart}
                          updateCart={updateCart}
                          rowData={row}
                        />
                      )}
                     </Table.Tbody>
                  </Table>
                  <OrderDetail data={cartStoreState} />
                </Flex>
                <Button onClick={() => router.history.back()}>
                  <IconArrowLeft />
                  Tiếp tục xem sản phẩm
                </Button>
              </Box>
          }
        </Stepper.Step>
        <Stepper.Step label="Chi tiết thanh toán" description="Chi tiết thanh toán" />
        <Stepper.Step label="Đơn hàng hoàn tất" description="Đơn hàng hoàn tất" />
      </Stepper>
    </Box>
  );
}

export default Cart

const OrderDetail = ({data}: any) => {
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
          <Text size="xl">
            <Price value={data.originalPrice} />
          </Text>
        </Flex>
        <Space h="sm" />
        {
          data.saleAmount > 0 &&
          <Box>
          <Divider />
          <Space h="sm" />
          <Flex justify="space-between">
            <Text size="md">Giá giảm sale</Text>
            <Text size="md"><Price value={-data.saleAmount} /></Text>
          </Flex>
          </Box>
        }
        <Space h="sm" />
        <Divider />
        <Space h="sm" />
        <Flex justify="space-between">
          <Text size="xl">Tạm tính</Text>
          <Flex gap={12} align="center">
            <Text size="xl">
              <Price value={data.subTotalAmount} />
            </Text>
          </Flex>
        </Flex>
        <Space h="sm" />
        <Button onClick={()=>router.history.push('/payment')}>
          Tiến hành thanh toán
        </Button>
      </Card>
      <Space h="sm" />
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
      <Table.Td>
        <Price value={rowData.price} />
      </Table.Td>
      <Table.Td>
        <Price value={-(rowData.price - tempAmount/rowData.quantity)} />
      </Table.Td>
      <Table.Td>
        <NumberInput onChange={(quantity: any) => onQuantityChange(quantity, rowData.gameId)} 
          value={rowData.quantity}
        />
      </Table.Td>
      <Table.Td>
        <Price value={tempAmount} />
      </Table.Td>
      <CloseButton onClick={(e)=>{e.preventDefault();onRemoveCartItem(rowData.gameId)}} />
    </Table.Tr>
  )
}
