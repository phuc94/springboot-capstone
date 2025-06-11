import { Container, Divider, Flex, Grid, NavLink, Space, Stack, Text, Title } from "@mantine/core";
import GameCard from "../GameCard";

const Platform = ({data}: any) => {

  return (
    <Container>
      <Space h="xl" />
      <Stack >
        <Flex justify="space-between" align="center" >
          <Title>{data?.title}</Title>
          <Text size="xl"><NavLink label="Xem tất cả" href={`/platform/${data.name}`}/></Text>
        </Flex>
        <Divider />    
        <Grid>
          {
            data?.games.map((game: any) => (
              <Grid.Col span={3}><GameCard data={game} /></Grid.Col>
            ))
          }
        </Grid>
      </Stack>
    </Container>
  )
}
export default Platform;


