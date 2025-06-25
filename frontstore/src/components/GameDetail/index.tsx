import { useGameDetail } from "@/hooks/useGameDetail"
import { Box, Button, Container, Divider, Flex, Group, Image, Rating, Space, Stack, Text, Title } from "@mantine/core"
import Description from "../Description";
import styles from './style.module.scss'
import { SaleTag } from "../GameCard";
import { useParams } from "@tanstack/react-router";
import Reviews from "../Review";
import { useAuthStore } from "@/store/useAuthStore";
import { useAddToCart } from "@/hooks/useCart";
import { notifications } from "@mantine/notifications";
import { useRef } from "react";
import Price from "../Price";

const GameDetail = () => {
  const {isAuthenticated} = useAuthStore();
  const {mutate: addToCart, isSuccess } = useAddToCart();
  const { gameId }: {gameId: number}  = useParams({ strict: false });
  const notiRef = useRef<string>('');
  const query = useGameDetail(gameId);
  if (query.isFetching) { return null; }

  const onAddToCart = async (id: number) => {
    if (!isAuthenticated) {
      notifications.show({
        title: 'Bạn chưa đăng nhập!',
        message: 'Vui lòng đăng nhập hoặc đăng ký.',
        color: 'red'
      })
      return;
    }
    notiRef.current = notifications.show({
      title: 'Đang được xử lý!',
      message: 'Xin chờ trong giây lát',
      loading: true
    })
    addToCart(id);
  }

  if (isSuccess) {
    notifications.update({
      id: notiRef.current,
      title: '✅ Thêm vào giỏ thành công!',
      message: 'Bấm vào giỏ hàng để tiến hành thanh toán.',
      color: 'green',
      loading: false
    })
  }

  return (
    <Container size="xl">
      <Flex direction="column" align="center">
        <Space h="xl" />
        <Flex gap={12}>
          <Flex className="relative">
            <Image radius="md" src={query.data?.data?.medias.find((media:any) => media.primary)?.url} w={320} h={500} />
            {query.data?.data?.sale && <SaleTag amount={query.data?.data?.sale.amount} />}
          </Flex>
          <Stack className={styles.info} align="center">
            <Title order={1}>{query.data?.data?.title}</Title>
            <Rating value={5} color="#faf737" size="lg" readOnly/>
            <Text c="dimmed">({query?.data?.data?.reviews.length} đánh giá của khách hàng)</Text>
            <Group>
              {query.data?.data?.sale ?
                <Box>
                  <Title order={2} c="dimmed" td="line-through">
                    <Price value={query.data?.data?.price}/>
                  </Title>
                  <Title order={2} fw={700}>
                    <Price value={query.data?.data?.salePrice}/>
                  </Title>
                </Box>
                :
                <Title order={2} fw={700}>
                  <Price value={query.data?.data?.price}/>
                </Title>
              }
            </Group>
            <Button color="red" onClick={(e)=>{e.preventDefault();onAddToCart(query.data?.data?.id)}}>
              MUA HÀNG
            </Button>
          </Stack>
          <Divider orientation="vertical" />
          <IconBox/>
        </Flex>
        <Space h="xl" />
        <Space h="xl" />
        <Description data={query.data?.data?.gameDescription.description} />
      </Flex>
      <Reviews data={query.data?.data?.reviews} />
    </Container>
  )
}
export default GameDetail

const IconBox = () => (
  <Flex direction="column" justify="space-around">
    <Flex gap={4} className={styles.iconbox} >
      <Image
        width={65}
        height={65}
        src="https://muaga.me/wp-content/uploads/2022/04/premium-icon-1.png"
      />
      <Text>Mua bản quyền để nhận được tính năng cao cấp</Text>
    </Flex>
    <Flex gap={4} className={styles.iconbox}>
      <Image
        width={65}
        height={65}
        src="https://muaga.me/wp-content/uploads/2022/04/sale-icon-1.png"
      />
      <Text>Đảm bảo giá bản quyền rẻ nhất thị trường</Text>
    </Flex>
    <Flex gap={4} className={styles.iconbox}>
      <Image
        width={65}
        height={65}
        src="https://muaga.me/wp-content/uploads/2022/04/payment-icon-1.png"
      />
      <Text>Hỗ trợ tất cả các phương thức thanh toán</Text>
    </Flex>
    <Flex gap={4} className={styles.iconbox}>
      <Image
        width={65}
        height={65}
        src="https://muaga.me/wp-content/uploads/2022/04/gmail-icon-1.png"
      />
      <Text>Giao hàng nhanh chóng qua Email của bạn</Text>
    </Flex>
    <Flex gap={4} className={styles.iconbox}>
      <Image
        width={65}
        height={65}
        src="https://muaga.me/wp-content/uploads/2022/04/support-icon-1.png"
      />
      <Text>Mua bản quyền để nhận được tính năng cao cấp</Text>
    </Flex>
  </Flex>
)
