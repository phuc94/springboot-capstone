import { Badge, Box, Button, Card, Flex, Image, Rating, Space, Text } from "@mantine/core"
import styles from './style.module.scss'
import { Link, useRouter } from "@tanstack/react-router"
import { useAddToCart } from "@/hooks/useCart"
import { notifications } from "@mantine/notifications"
import { useRef } from "react"
import { useAuthStore } from "@/store/useAuthStore"
import Price from "../Price"

const GameCard = ({data}: any) => {
  const {isAuthenticated} = useAuthStore();
  const {mutate: addToCart, isSuccess } = useAddToCart();
  const notiRef = useRef<string>('');
  const router = useRouter();

  const onAddToCart = async (id: number, stock: number) => {
    if (stock === 0) {
      return router.history.push(`game/${id}`)
    }
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
    <Link to="/game/$gameId" params={{gameId: data.id}}>
      <Card
        shadow="sm" padding="lg" radius="md" withBorder
        className={styles.gameCard}
      >
        <Card.Section pos="relative">
          <Image
            w={220}
            h={300}
            src={data.img}
          />
          {
            data.stock === 0 &&
            <div className={styles.outStock}>
              <div className={styles.outStockOverlay}/>
              <div className={styles.outStockText}> HẾT HÀNG </div>
            </div>
          }
        </Card.Section>

        <Flex direction="column" justify="center" align="center">
          <Space h="xs" />
          <Text size="xl" ta="center">{data.title}</Text>
          <Space h="xs" />
          <Rating value={5} color="#faf737" size="md" readOnly/>
          {data.sale ?
            <Box>
              <Text size="xl" c="dimmed" td="line-through">
                <Price value={data.price} />
              </Text>
              <Text size="xl" fw={700}>
                <Price value={data.salePrice} />
              </Text>
            </Box>
            :
            <Text size="xl" fw={700}>
              <Price value={data.price} />
            </Text>
          }
          <Space h="md" />
          <Button color="red"
            onClick={(e)=>{e.preventDefault();onAddToCart(data.id,data.stock)}}
          >
            {
              data.stock !== 0 ?
              'MUA HÀNG' : 'XEM TIẾP'
            }
          </Button>
        </Flex>
        {data.sale && <SaleTag amount={data.sale.amount} />}
      </Card>
    </Link>
  )
}

export default GameCard

export const SaleTag = ({amount}: any) => (
  <Badge className={styles.gameCardTag} color="red" size="xl" radius="xs">
    {amount}%
  </Badge>
)


