import { useCancelOrder, useOrderCanceled, useOrderPending, useOrderSuccess } from "@/hooks/useOrder";
import { Box, Button, Card, Divider, Flex, Group, Image, Modal, Space, Stack, Table, Tabs, Text, Title } from "@mantine/core"
import { useDisclosure } from "@mantine/hooks";
import { notifications } from "@mantine/notifications";
import { Link } from "@tanstack/react-router";
import { useEffect, useRef, useState } from "react";
import Price from "../Price";

const Order = () => {
  const [activeTab, setActiveTab] = useState<string | null>(OrderType.SUCCESS.toString());

  return (
    <Tabs value={activeTab} onChange={setActiveTab} variant="outline">
      <Space h="xl" />
      <Title order={1} ta="center">Danh sách đơn mua</Title>
      <Space h="xl" />

      <Tabs.List justify="center">
        <Tabs.Tab value={OrderType.PENDING.toString()} >
          <Title order={5}>Đang tiến hành</Title>
        </Tabs.Tab>
        <Tabs.Tab value={OrderType.SUCCESS.toString()} >
          <Title order={5}>Đã hoàn thành</Title>
        </Tabs.Tab>
        <Tabs.Tab value={OrderType.CANCELLED.toString()} >
          <Title order={5}>Đã hủy</Title>
        </Tabs.Tab>
      </Tabs.List>

      <Tabs.Panel value={OrderType.PENDING.toString()}>
        <PendingTab />
      </Tabs.Panel>

      <Tabs.Panel value={OrderType.SUCCESS.toString()}>
        <SuccessTab />
      </Tabs.Panel>

      <Tabs.Panel value={OrderType.CANCELLED.toString()}>
        <CanceledTab />
      </Tabs.Panel>

      <Space h="xl" />
      <Space h="xl" />
      <Space h="xl" />
    </Tabs>
  )
}

export default Order

const SuccessTab = () => {
  const {data, isSuccess} = useOrderSuccess()
  if (!isSuccess) {return null;}
  return (
    <Stack gap={30}>
      <Space h="lg" />
      {data.data?.length > 0 && data.data?.map((data: any) => <OrderCard key={data.id} data={data} onCancel={null} />)}
    </Stack>
  )
}


const PendingTab = () => {
  const {data, isSuccess} = useOrderPending();
  const {mutate: cancelOrder, isSuccess: isCancelSuccess} = useCancelOrder();
  const cancelOrderRef = useRef<any>({id: null, isOk: false});
  const [opened, { open, close }] = useDisclosure(false);

  const onCancel = (id: number) => {
    cancelOrderRef.current.id = id;
    open();
  }

  useEffect(()=> {
    if (cancelOrderRef.current.isOk) {
      cancelOrder(cancelOrderRef.current.id);
      cancelOrderRef.current.isOk = false;
      cancelOrderRef.current.id = null;
    }
  }, [opened])

  useEffect(()=> {
    if (isCancelSuccess) {
      notifications.show({
        message: 'Hủy thanh toán thành công!'
      })
    }
  },[isCancelSuccess])



  if (!isSuccess) {return null;}
  return (
    <Stack gap={30}>
      <Space h="lg" />
      {data.data?.length > 0 && data.data?.map((data: any) => <OrderCard key={data.id} data={data} onCancel={onCancel} />)}
      <Modal
        opened={opened} onClose={close} centered
        title="❌ Hủy thanh toán"
        padding={30}
        size="auto"
      >
        <Text>Bạn chắc chắn muốn hủy thanh toán này?</Text>
        <Space h="xl" />
        <Flex gap={40} justify="center">
          <Button color="red" onClick={()=>{cancelOrderRef.current.isOk = true;close();}}>Đồng ý</Button>
          <Button color="green" onClick={()=>close()}>Hủy</Button>
        </Flex>
      </Modal>
    </Stack>
  )
}

const CanceledTab = () => {
  const {data, isSuccess} = useOrderCanceled()
  if (!isSuccess) {return null;}
  return (
    <Stack gap={30}>
      <Space h="lg" />
      {data.data?.length > 0 && data.data?.map((data: any) => <OrderCard key={data.id} data={data} onCancel={null} />)}
    </Stack>
  )
}

const OrderCard = ({data, onCancel}: any) => {
  return (
    <Card padding={20}>
     <Table ta="center" withTableBorder >
        <Table.Thead>
          <Table.Tr>
            <Table.Th ta="center">Số Order</Table.Th>
            <Table.Th ta="center">Giá gốc</Table.Th>
            <Table.Th ta="center">Coupon</Table.Th>
            <Table.Th ta="center">Tổng</Table.Th>
            <Table.Th ta="center">Tình trạng</Table.Th>
            {data.orderStatus == OrderType.PENDING.toString() &&
              <Table.Th ta="center">Thao tác</Table.Th>
            }
          </Table.Tr>
        </Table.Thead>
        <Table.Tbody>
          <Table.Tr >
            <Table.Td>{data.id}</Table.Td>
            <Table.Td><Price value={data.subTotalAmount} /></Table.Td>
            <Table.Td><Price value={-data.discountAmount} /></Table.Td>
            <Table.Td><Price value={data.totalAmount} /></Table.Td>
            <Table.Td>{renderOrderStats(data.orderStatus)}</Table.Td>
            {data.orderStatus == OrderType.PENDING.toString() &&
              <Table.Td>
                <Link to={data.url}>
                  <Button>Thanh toán</Button>
                </Link>
                <Button color="red" ml={20}
                  onClick={() => onCancel(data.id)}
                >Hủy</Button>
              </Table.Td>
            }
          </Table.Tr>
        </Table.Tbody>
      </Table>

      <Space h="xl" />
      <Title order={4} ta="center">Danh sách sản phẩm</Title>
      <Space h="md" />
      <Table ta="center">
        <Table.Thead>
          <Table.Tr>
            <Table.Th ta="center">Game</Table.Th>
            <Table.Th ta="center">Title</Table.Th>
            <Table.Th ta="center">Số lượng</Table.Th>
            <Table.Th ta="center">Đơn giá</Table.Th>
            <Table.Th ta="center">Tổng</Table.Th>
          </Table.Tr>
        </Table.Thead>
        <Table.Tbody>
          {data.orderItems.map((item: any) =><OrderItem key={item.gameId} item={item}/>)}
        </Table.Tbody>
      </Table>
    </Card>
  )
}

const renderOrderStats = (orderStatus: string) => {
  switch (orderStatus) {
    case OrderType.SUCCESS.toString():
      return "Thanh toán thành công";
    case OrderType.PENDING.toString():
      return "Đang chờ thanh toán";
    case OrderType.CANCELLED.toString():
      return "Đơn đã hủy";
    default:
      return "Chưa xác định"
  }
}

const OrderItem = ({item}: any) => (
  <Table.Tr key={item.gameId}>
    <Table.Td>
        <Image src={item.img} w={60} h={100}/>
    </Table.Td>
    <Table.Td>
      <Text fw={600}>{item.title}</Text>
    </Table.Td>
    <Table.Td>{item.quantity}</Table.Td>
    <Table.Td><Price value={item.price} /></Table.Td>
    <Table.Td><Price value={item.quantity * item.price} /></Table.Td>
  </Table.Tr>
)

enum OrderType {
  SUCCESS = "COMPLETED",
  PENDING = "PENDING",
  CANCELLED = "CANCELLED"
}

