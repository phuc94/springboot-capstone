import { useCheckout } from "@/hooks/useCheckout"
import { useCartStore, usePaymentStore } from "@/store/useCartStore"
import { Accordion, Box, Button, Card, Divider, Flex, Loader, LoadingOverlay, Radio, Space, Stepper, Table, Text, Textarea, TextInput } from "@mantine/core"
import { useForm } from "@mantine/form"
import { notifications } from "@mantine/notifications"
import { useEffect, useState } from "react"
import Price from "../Price"
import { IconTagStarred } from "@tabler/icons-react"
import { useApplyCoupon } from "@/hooks/useApplyCoupon"
import { useDisclosure } from "@mantine/hooks"

const Payment = () => {
  const {mutate: checkout, isSuccess, data} = useCheckout();
  const {mutate: applyCoupon, isSuccess: applyCouponSuccess, data: applyCouponData} = useApplyCoupon();
  const init = usePaymentStore(state => state.init);
  const cartDetail = useCartStore.getState();
  const setStore = useCartStore(state => state.setStore);
  const [visible, { toggle }] = useDisclosure(false);
  const [couponApplied, setCouponApplied] = useState(false);
  const fullStore = useCartStore(); // work around for zustand to trigger re-render when setStore

  const form = useForm({
    mode: 'uncontrolled',
    initialValues: {
      email: '',
      name: '',
      phone: '',
      note: '',
      code: '',
    },
    validate: {
      email: (value) => (/^\S+@\S+$/.test(value) ? null : 'Invalid email'),
      phone: (value) => ( /\d{10}$/.test(value) ? null : 'Invalid phone number'),
    },
  });

  const onApplyCoupon = (e: React.MouseEvent<HTMLElement>) => {
    e.preventDefault();
    applyCoupon(form.getValues().code);
    toggle();
  }

  const onOrder = (e: React.MouseEvent<HTMLElement>, form: any) => {
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
    if (applyCouponSuccess) {
      if (applyCouponData.code === 200) {
        setStore(applyCouponData.data);
        toggle();
        setCouponApplied(true)
        notifications.show({
          message: 'Áp dụng coupon thành công!',
          color: 'green'
        })
      } else {
        toggle();
        notifications.show({
          title: "Áp dụng coupon không thành công!",
          message: "Vui lòng kiểm tra lại thông tin coupon.",
          color: "red"
        })
      }
    }
  }, [applyCouponSuccess])

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
              <PaymentForm
                form={form}
                onApplyCoupon={onApplyCoupon}
                visible={visible}
                couponApplied={couponApplied}
              />
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

const PaymentForm = ({form, onApplyCoupon, visible, couponApplied}: any) => {

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
      <Space h="md" />
      <Box pos="relative">
        <LoadingOverlay visible={visible} loaderProps={{ children: <Loader color="blue" /> }} />
        <Flex gap={12} align="center">
          <IconTagStarred />
          <Text size="xl" fw={700}>Mã ưu đãi</Text>
        </Flex>
        <Divider />
        <Space h="sm" />
        <TextInput
          placeholder="Mã ưu đãi"
          key={form.key('code')}
          {...form.getInputProps('code')}
          disabled={couponApplied}
        />
        <Space h="sm" />
        <Button disabled={couponApplied} onClick={onApplyCoupon} fullWidth color="green">
          {couponApplied ? '✅ Áp mã thành công' : 'Áp dụng'}
        
        </Button>
      </Box>
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
          {
            cartDetail.discountAmount > 0 &&
              <Table.Tr>
                <Table.Td>Coupon</Table.Td>
                <Table.Td>
                  <Text>{cartDetail.code}</Text>
                </Table.Td>
                <Table.Td>
                  - <Price value={cartDetail.discountAmount} />
                </Table.Td>
              </Table.Tr>
          }
          <Table.Tr>
            <Table.Td><Text fw={700}>Tổng</Text></Table.Td>
            <Table.Td>{null}</Table.Td>
            <Table.Td>
              <Price value={cartDetail.totalAmount} />
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



