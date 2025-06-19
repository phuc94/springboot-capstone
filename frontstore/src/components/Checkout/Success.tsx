import { useCheckoutSuccess } from "@/hooks/useCheckout"
import { usePaymentStore } from "@/store/useCartStore"
import { Button, Card, Center, Divider, Flex, Loader, Space, Stack, Text, Title } from "@mantine/core"
import { IconCircleCheck } from "@tabler/icons-react"
import { Link } from "@tanstack/react-router"
import { useEffect } from "react"

const Success = () => {
  const {mutate: checkoutFulFill, isSuccess} = useCheckoutSuccess();
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

  return (
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
            <Text>300.000 VND</Text>
          </Flex>
          <Flex justify="space-between" gap={40}>
            <Text>Phương thức thanh toán</Text>
            <Text>Stripe</Text>
          </Flex>
          <Flex justify="space-between" gap={40}>
            <Text>Số Order</Text>
            <Text>#1982342</Text>
          </Flex>
        </Card>
        <Divider my="sm" />
        <Center>
          <Text>Chi tiết sản phẩm sẽ được gửi qua Email. Xin hãy kiểm tra email.</Text>
        </Center>
        <Center>
          <Button color="green">
            <Link to="/">Quay lại trang chủ</Link>
          </Button>
        </Center>
    </Stack>
  )
}

export default Success

