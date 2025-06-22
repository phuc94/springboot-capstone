import { useGameDetail } from "@/hooks/useGameDetail"
import { Box, Container, Divider, Flex, Group, Image, Rating, Space, Stack, Text, Title } from "@mantine/core"
import Description from "../Description";
import styles from './style.module.scss'
import { SaleTag } from "../GameCard";
import { useParams } from "@tanstack/react-router";
import Reviews from "../Review";

const GameDetail = () => {

  const { gameId }: {gameId: number}  = useParams({ strict: false });
  const query = useGameDetail(gameId);
  if (query.isFetching) { return null; }

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
              {query.data?.data?.sale !== 0 ?
                <Box>
                  <Title order={2} c="dimmed" td="line-through">{query.data?.data?.price}đ</Title>
                  <Title order={2} fw={700}>{query.data?.data?.salePrice}đ</Title>
                </Box>
                :
                <Title order={2} size="xl" fw={700}>{query.data?.data?.price}đ</Title>
              }
            </Group>
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
