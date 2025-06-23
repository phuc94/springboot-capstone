import { useCheckout } from "@/hooks/useCheckout"
import { usePaymentStore } from "@/store/useCartStore"
import { Accordion, Box, Button, Card, Flex, Radio, Space, Stepper, Table, Text, Textarea, TextInput } from "@mantine/core"
import { notifications } from "@mantine/notifications"
import { useEffect, useState } from "react"

const Payment = () => {
  return (
    <Box style={{paddingTop: 80}} >
      <Stepper active={1}>
        <Stepper.Step label="Giỏ hàng" description="Điều chỉnh giỏ hàng" />
        <Stepper.Step label="Chi tiết thanh toán" description="Chi tiết thanh toán">
          <Space h="xl" />
          <Flex gap={40} direction="row">
            <PaymentForm />
            <OrderDetail />
          </Flex>
        </Stepper.Step>
        <Stepper.Step label="Đơn hàng hoàn tất" description="Đơn hàng hoàn tất" />
      </Stepper>
    </Box>
  )
}
export default Payment

const PaymentForm = () => {
  return (
    <Box style={{flex: 1}}>
      <Text size="xl" fw={700}>Thông tin thanh toán</Text>
      <Space h="md" />
      <TextInput placeholder="Địa chỉ Email nhận hàng" />
      <Space h="sm" />
      <Flex>
        <TextInput placeholder="Tên" style={{flex:1}} />
        <Space w="sm" />
        <TextInput placeholder="Họ" style={{flex:1}} />
      </Flex>
      <Space h="sm" />
      <TextInput placeholder="Số điện thoại (tùy chọn)" />
      <Space h="md" />
      <Text size="xl" fw={700}>Thông tin bổ sung</Text>
      <Space h="sm" />
      <Textarea placeholder="Nếu bạn có ghi chú thêm về đơn hàng, xin viết ở đây (Có thể để trống)" />
    </Box>
  )
}

const OrderDetail = () => {
  const [value, setValue] = useState<string | null>("stripe");
  const {mutate: checkout, isSuccess, data} = useCheckout();
  const init = usePaymentStore(state => state.init);

  const onOrder = (e: React.MouseEvent<HTMLElement>) => {
    e.preventDefault();
    notifications.show({
      loading: true,
      message: "Đang tiến hành chuyển trang, xin chờ giây lát!"
    })
    checkout();
  }
  useEffect(() => {
    if (isSuccess) {
      init({
        sessionId: data.data.sessionId,
        url: data.data.sessionUrl,
        status: data.data.status
      })
      window.location = data.data.sessionUrl
    }
  }, [isSuccess])

  return (
    <Card style={{flex: 1}}>
      <Text size="xl" fw={700}>Đơn hàng của bạn</Text>
      <Table>
        <Table.Thead>
          <Table.Th>Sản phẩm</Table.Th>
          <Table.Th>Tạm tính</Table.Th>
        </Table.Thead>
        <Table.Tbody>
          <Table.Tr>
            <Table.Td>XSplit Premium 1 năm  × 1</Table.Td>
            <Table.Td>590.000</Table.Td>
          </Table.Tr>
          <Table.Tr>
            <Table.Td>Tạm tính</Table.Td>
            <Table.Td>590.000</Table.Td>
          </Table.Tr>
          <Table.Tr>
            <Table.Td>Tổng</Table.Td>
            <Table.Td>590.000</Table.Td>
          </Table.Tr>
        </Table.Tbody>
      </Table>
      <Radio.Group
        value={value}
        onChange={setValue}
      >
        <Accordion value={value}>
          <Accordion.Item value="stripe">
            <Accordion.Control >
              <Radio value="stripe" label="Thanh toán quốc tế Stripe" />
            </Accordion.Control>
            <Accordion.Panel>Hỗ trợ chuyển tiền tất cả các ngân hàng lớn tại Việt Nam, tự động xác nhận đơn hàng</Accordion.Panel>
          </Accordion.Item>
        </Accordion>
      </Radio.Group>
      <Space h="xl" />
      <Button onClick={onOrder}>
        <Text size="xl" fw={700} >Đặt hàng</Text>
      </Button>
      <Space h="xl" />
      <Text>
      Thông tin cá nhân của bạn sẽ được sử dụng để xử lý đơn hàng, tăng trải nghiệm sử dụng website, tiếp thị và cho các mục đích cụ thể khác đã được mô tả trong Chính sách bảo mật.
      </Text>
    </Card>
  )
}

