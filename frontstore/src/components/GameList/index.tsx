import { Center, Container, Divider, Flex, Grid, Pagination, Space, Stack, Text, Title } from "@mantine/core"
import GameCard from "../GameCard"

const GameList = () => {
  return (
    <Container>
      <Space h="xl" />
      <Text size="xl">Mua game Steam tại shop MuaGa.me với giá rẻ, uy tín. Nhận game nhanh chóng, nhiều giảm giá so với mua trực tiếp trên Steam. Sau khi mua trò chơi, bạn có thể tải xuống và cài đặt trò chơi trên máy tính của mình bằng phần mềm Steam.</Text>
      <Space h="xl" />
      <Grid gutter="md">
        {
          Array(12).fill(0).map(_ => (
            <Grid.Col span={3}>
              <GameCard />
              <Space h="md" />
            </Grid.Col>
          ))
        }
      </Grid>
      <Space h="xl" />
      <Center>
        <Pagination total={10} />
      </Center>
      <Space h="xl" />
    </Container>
  )
}

export default GameList

