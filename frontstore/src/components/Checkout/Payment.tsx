import { useCheckout } from "@/hooks/useCheckout"
import { useCartStore, usePaymentStore } from "@/store/useCartStore"
import { Accordion, Box, Button, Card, Flex, Radio, Space, Stepper, Table, Text, Textarea, TextInput } from "@mantine/core"
import { useForm } from "@mantine/form"
import { notifications } from "@mantine/notifications"
import { useEffect, useState } from "react"
import Price from "../Price"

const Payment = () => {
  const cartDetail = useCartStore.getState();
  const form = useForm({
    mode: 'uncontrolled',
    initialValues: {
      email: '',
      name: '',
      phone: '',
      note: '',
    },
    validate: {
      email: (value) => (/^\S+@\S+$/.test(value) ? null : 'Invalid email'),
      phone: (value) => ( /\d{10}$/.test(value) ? null : 'Invalid phone number'),
    },
  });

  const {mutate: checkout, isSuccess, data} = useCheckout();
  const init = usePaymentStore(state => state.init);

  const onOrder = (e: React.MouseEvent<HTMLElement>, form) => {
    e.preventDefault();
    if (!form.isValid()) {
      notifications.show({
        title: "Nhập thông tin chưa đúng!",
        message: "Vui lòng kiểm tra lại thông tin thanh toán.",
        color: "red"
      })
      return;
    }
    notifications.show({
      loading: true,
      message: "Đang tiến hành chuyển trang, xin chờ giây lát!"
    })
    checkout(form.values);
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
    <Box style={{paddingTop: 80}} >
      <Stepper active={1}>
        <Stepper.Step label="Giỏ hàng" description="Điều chỉnh giỏ hàng" />
        <Stepper.Step label="Chi tiết thanh toán" description="Chi tiết thanh toán">
          <Space h="xl" />
          <form onSubmit={form.onSubmit(console.log)}>
            <Flex gap={20} direction="row">
              <PaymentForm form={form}/>
              <OrderDetail onOrder={onOrder} cartDetail={cartDetail} form={form} />
            </Flex>
          </form>
        </Stepper.Step>
        <Stepper.Step label="Đơn hàng hoàn tất" description="Đơn hàng hoàn tất" />
      </Stepper>
    </Box>
  )
}
export default Payment

const PaymentForm = ({form}: any) => {
  return (
    <Box style={{minWidth: 320}}>
      <Text size="xl" fw={700}>Thông tin thanh toán</Text>
      <Space h="md" />
      <TextInput
        placeholder="Địa chỉ Email nhận hàng"
        key={form.key('email')}
        {...form.getInputProps('email')}
      />
      <Space h="sm" />
      <TextInput
        placeholder="Tên"
        key={form.key('name')}
        {...form.getInputProps('name')}
      />
      <Space h="sm" />
      <TextInput
        placeholder="Số điện thoại (tùy chọn)"
        key={form.key('phone')}
        {...form.getInputProps('phone')}
      />
      <Space h="md" />
      <Text size="xl" fw={700}>Thông tin bổ sung</Text>
      <Space h="sm" />
      <Textarea
        placeholder="Nếu bạn có ghi chú thêm về đơn hàng, xin viết ở đây (Có thể để trống)"
        key={form.key('note')}
        {...form.getInputProps('note')}
      />
    </Box>
  )
}

const OrderDetail = ({onOrder, cartDetail, form}: any) => {
  const [value, setValue] = useState<string | null>("stripe");

  return (
    <Card style={{flex: 1}}>
      <Text size="xl" fw={700}>Đơn hàng của bạn</Text>
      <Table>
        <Table.Thead>
          <Table.Th>Sản phẩm</Table.Th>
          <Table.Th>Số lượng</Table.Th>
          <Table.Th>Giá</Table.Th>
        </Table.Thead>
        <Table.Tbody>
          {
            cartDetail.items.map((item: any) => (
              <Table.Tr key={item.id}>
                <Table.Td>{item.title}</Table.Td>
                <Table.Td>{item.quantity}</Table.Td>
                <Table.Td>
                  <Price value={item.quantity*item.price} />
                </Table.Td>
              </Table.Tr>
            ))
          }
          <Table.Tr>
            <Table.Td><Text fw={700}>Tổng</Text></Table.Td>
            <Table.Td>{null}</Table.Td>
            <Table.Td>
              <Price value={cartDetail.finalPrice} />
            </Table.Td>
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
      <Button onClick={(e:any)=>onOrder(e,form)}>
        <Text size="xl" fw={700} >Đặt hàng</Text>
      </Button>
      <Space h="xl" />
      <Text>
        Thông tin cá nhân của bạn sẽ được sử dụng để xử lý đơn hàng, tăng trải nghiệm sử dụng website, tiếp thị và cho các mục đích cụ thể khác đã được mô tả trong Chính sách bảo mật.
      </Text>
    </Card>
  )
}

