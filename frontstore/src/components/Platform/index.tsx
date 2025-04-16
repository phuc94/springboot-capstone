import { Box, Button, Card, Container, Divider, Flex, Grid, Group, Image, Space, Stack, Text, Title } from "@mantine/core";
import { IconStar, IconStarFilled } from "@tabler/icons-react";
import styles from './style.module.scss'

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

const GameCard = () => (
  <Card shadow="sm" padding="lg" radius="md" withBorder
    className={styles.gameCard}
  >
    <Card.Section>
      <Image
        src="https://muaga.me/wp-content/uploads/2023/02/nba-2k23-steam-1-247x296.jpg.webp"
      />
    </Card.Section>

    <Flex direction="column" justify="center" align="center">
      <Text size="xl">NBA 2K23 Steam Key</Text>
      <Space h="xs" />
      <Box>
        <IconStarFilled color="yellow"/>
        <IconStarFilled color="yellow"/>
        <IconStarFilled color="yellow"/>
        <IconStarFilled color="yellow"/>
        <IconStar color="yellow" />
      </Box>
      <Text size="xl" c="dimmed" td="line-through">1.000.000đ</Text>
      <Text size="xl" fw={700}>950.000đ</Text>
      <Space h="md" />
      <Button color="red">MUA HÀNG</Button>
    </Flex>

  </Card>
)

