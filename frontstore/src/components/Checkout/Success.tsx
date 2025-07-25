import { useCheckoutSuccess } from "@/hooks/useCheckout"
import { useOrderById } from "@/hooks/useOrder"
import { usePaymentStore } from "@/store/useCartStore"
import { Stepper, Box, Button, Card, Center, Divider, Flex, Loader, Space, Stack, Text, Title } from "@mantine/core"
import { IconCircleCheck } from "@tabler/icons-react"
import { Link } from "@tanstack/react-router"
import { useEffect } from "react"
import Price from "../Price"

const Success = () => {
  const {mutate: checkoutFulFill, isSuccess, data} = useCheckoutSuccess();
  const sessionId = usePaymentStore(state => state.sessionId);

  useEffect(() => {
    checkoutFulFill(sessionId);
  }, [])

  if (!isSuccess) {
    return <Flex align="center" direction="column">
      <Space h="xl" />
      <Space h="xl" />
      <Space h="xl" />
      <Loader color="blue" />
      <Space h="xl" />
      <Text c="dimmed">Yêu cầu đang được xử lý. Xin chờ giây lát.</Text>
    </Flex>;
  }

  // const {data: successOrderData} = useOrderById(data.data.id)

  return (
    <Box style={{paddingTop: 80}} >
      <Stepper active={3}>
        <Stepper.Step label="Giỏ hàng" description="Điều chỉnh giỏ hàng" />
        <Stepper.Step label="Chi tiết thanh toán" description="Chi tiết thanh toán" />
        <Stepper.Step label="Đơn hàng hoàn tất" description="Đơn hàng hoàn tất" />
      </Stepper>
      <Stack>
        <Space h="xl"/>
          <Flex direction="column" align="center">
            <IconCircleCheck size={60} color="#26c957" />
            <Title>Thanh toán thành công!</Title>
            <Text c="dimmed">Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi!</Text>
          </Flex>
          <Divider my="sm" />
          <Card>
            <Flex justify="space-between" gap={40}>
              <Text>Tổng tiền đã thanh toán:</Text>
              <Text><Price value={data.data.totalAmount}/></Text>
            </Flex>
            <Flex justify="space-between" gap={40}>
              <Text>Phương thức thanh toán</Text>
              <Text>Stripe</Text>
            </Flex>
            <Flex justify="space-between" gap={40}>
              <Text>Số Order</Text>
              <Text>#{data.data.id}</Text>
            </Flex>
          </Card>
          <Divider my="sm" />
          <Center>
            <Text>Chi tiết sản phẩm sẽ được gửi qua Email. Xin hãy kiểm tra email.</Text>
          </Center>
          <Center>
            <Button color="blue">
              <Link to="/orders">Xem đơn hoàn thành</Link>
            </Button>
            <Space w="md" />
            <Button color="green">
              <Link to="/">Quay lại trang chủ</Link>
            </Button>
          </Center>
      </Stack>
    </Box>
  )
}

export default Success

