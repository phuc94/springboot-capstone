import { Container, Divider, Flex, Grid, Space, Stack, Text, Title } from "@mantine/core";
import GameCard from "../GameCard";

const Platform = () => {
  return (
    <Container>
      <Space h="xl" />
      <Stack >
        <Flex justify="space-between" align="center" >
          <Title>STEAM GAME</Title>
          <Text size="xl">Xem tất cả </Text>
        </Flex>
        <Divider />    
        <Grid>
          <Grid.Col span={3}><GameCard /></Grid.Col>
          <Grid.Col span={3}><GameCard /></Grid.Col>
          <Grid.Col span={3}><GameCard /></Grid.Col>
          <Grid.Col span={3}><GameCard /></Grid.Col>
        </Grid>
      </Stack>
    </Container>
  )
}
export default Platform;


