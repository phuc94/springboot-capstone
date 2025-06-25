import { Box, Button, Card, Center, Chip, Divider, Flex, Group, Image, List, Paper, Space, Stack, Stepper, Table, Text, Title } from "@mantine/core"
import styles from './Checkout.module.scss'
import { IconArrowLeft } from "@tabler/icons-react";

const Checkout = () => {
  return (
    <Box style={{paddingTop: 80}} >
      <Stepper active={2}>
        <Stepper.Step label="Giỏ hàng" description="Điều chỉnh giỏ hàng" />
        <Stepper.Step label="Chi tiết thanh toán" description="Chi tiết thanh toán" />
        <Stepper.Step label="Đơn hàng hoàn tất" description="Đơn hàng hoàn tất">
          <Space h="xl" />
          <Flex gap={40} direction="row">
            <OrderQRAndDetail />
            <OrderInfo />
          </Flex>
        </Stepper.Step>
      </Stepper>
      <Space h="xl" />
      <Space h="xl" />
      <Space h="xl" />
    </Box>
  )
}

export default Checkout;

const OrderQRAndDetail = () => {
  return (
    <Stack>
      <QRCard />
      <Title>Chi tiết đơn hàng</Title>
      <Flex justify="space-between">
        <Text>Sản phẩm</Text>
        <Text>Tổng</Text>
      </Flex>
      <Divider size="md" />
      <Flex justify="space-between">
        <Text>XSplit Premium 1 năm × 1</Text>
        <Text>590.000</Text>
      </Flex>
      <Divider />
      <Flex justify="space-between">
        <Text>Tổng số phụ:</Text>
        <Text>590.000</Text>
      </Flex>
      <Divider />
      <Flex justify="space-between">
        <Text>Phương thức thanh toán:</Text>
        <Text>Thanh toán Momo</Text>
      </Flex>
      <Divider />
      <Flex justify="space-between">
        <Text>Tổng cộng:</Text>
        <Text>590.000</Text>
      </Flex>
      <Divider />
      <Flex justify="space-between">
        <Text>Các thao tác:</Text>
        <Group>
          <Button>
            <IconArrowLeft />
            Chọn lại Thanh Toán
          </Button>
          <Button>Xem</Button>
          <Button>Hủy</Button>
        </Group>
      </Flex>
      <Title>Chi tiết đơn hàng</Title>
      <Text>PHÚC Nguyen</Text>
      <Text>+84369555142</Text>
      <Text fs="italic">trungphuc91@gmail.com</Text>
    </Stack>
  )
}

const DarkText = ({children}: any) => <Text color="black">{children}</Text>

const QRCard = () => {
  return (
    <Paper bg="white" radius="lg">
      <Flex >
        <Card bg="white" padding={22} radius="lg">
          <DarkText>Bước 1: Mở Ví Momo</DarkText>
          <DarkText>Bước 2: Chọn QR và quét mã</DarkText>
          <Space h="md" />
          <Image
            width={200}
            height={200}
            src="https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=2|99|0343237950|||0|0|590000|MM147112|transfer_p2p"
            />
          <Space h="md" />
          <DarkText>Bước 3: Xác Nhận Chuyển Khoản</DarkText>
        </Card>
        <Divider orientation="vertical" />
        <Card bg="white" padding={22} radius="lg">
          <Center><DarkText>Thanh toán Ví điện tử MoMo</DarkText></Center>
          <Table className={styles.paymentTable}>
            <Table.Tbody>
              <Table.Tr>
                <Table.Th><DarkText>Họ tên:</DarkText></Table.Th>
                <Table.Th><DarkText>Nguyen Truong Trung Phuc</DarkText></Table.Th>
              </Table.Tr>
              <Table.Tr>
                <Table.Th><DarkText>Tài Khoản:</DarkText></Table.Th>
                <Table.Th><DarkText>0369999999</DarkText></Table.Th>
              </Table.Tr>
              <Table.Tr>
                <Table.Th><DarkText>Số Tiền</DarkText></Table.Th>
                <Table.Th><DarkText>590,000</DarkText></Table.Th>
              </Table.Tr>
              <Table.Tr>
                <Table.Th><DarkText>Nội Dung*:</DarkText></Table.Th>
                <Table.Th><Chip radius="sm" color="red" checked fw={700}>MM147112</Chip></Table.Th>
              </Table.Tr>
            </Table.Tbody>
          </Table>
          <Card bg="#ccc" padding={8} radius="md">
            <Text
              color="black"
              size="xl"
              style={{maxWidth: 300, textAlign: 'center'}}
            >
            Nhập <Text fw={700} span color="red">chính xác nội dung</Text>, thanh toán xong vui lòng không tắt trình duyệt cho tới khi đơn hàng được xác nhận.
            </Text>
          </Card>
        </Card>
      </Flex>
    </Paper>
  )
}

const OrderInfo = () => {
  return (
    <Box>
      <Text>Cảm ơn bạn. Đơn hàng của bạn đã được nhận.</Text>
       <List>
          <List.Item>Mã đơn hàng: 147112</List.Item>
          <List.Item>Ngày: 19/04/2025</List.Item>
          <List.Item>Tổng cộng: 590.000</List.Item>
          <List.Item>Phương thức thanh toán: <Text span fw={700}>Thanh toán Momo</Text></List.Item>
        </List>
    </Box>
  )
}

