import { Badge, Box, Button, Card, Flex, Image, Rating, Space, Text } from "@mantine/core"
import styles from './style.module.scss'
import { Link, useRouter } from "@tanstack/react-router"
import { useAddToCart } from "@/hooks/useCart"

const GameCard = ({data}: any) => {
  const router = useRouter();
  const {mutate: addToCart, isSuccess } = useAddToCart();

  const onAddToCard = async (id: number) => {
    addToCart(id);
    if (isSuccess) {
      router.history.push('/cart');
    }
  }

  return (
    <Link to="/game/$gameId" params={{gameId: data.id}}>
      <Card
        shadow="sm" padding="lg" radius="md" withBorder
        className={styles.gameCard}
      >
        <Card.Section>
          <Image
            src="https://muaga.me/wp-content/uploads/2023/02/nba-2k23-steam-1-247x296.jpg.webp"
          />
        </Card.Section>

        <Flex direction="column" justify="center" align="center">
          <Text size="xl">{data.title}</Text>
          <Space h="xs" />
          <Rating value={5} color="#faf737" size="md" readOnly/>
          {data.sale !== 0 ?
            <Box>
              <Text size="xl" c="dimmed" td="line-through">{data.price}đ</Text>
              <Text size="xl" fw={700}>{Math.round(data.price * (100 - data.sale) / 100)}đ</Text>
            </Box>
            :
            <Text size="xl" fw={700}>{data.price}đ</Text>
          }
          <Space h="md" />
          <Button color="red" onClick={(e)=>{e.preventDefault();onAddToCard(data.id)}}>
            MUA HÀNG
          </Button>
        </Flex>
        {data.sale !== 0 && <SaleTag amount={data.sale} />}
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


