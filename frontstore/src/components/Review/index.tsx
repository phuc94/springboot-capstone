import { Avatar, Card, Divider, Flex, Group, Space, Stack, Text, Title } from "@mantine/core";
import { IconStarFilled } from "@tabler/icons-react";
import styles from './style.module.scss'

const Reviews = ({data}: any) => {
  return (
    <Flex direction="column" justify="start" gap={20}>
      <Title order={2}>Review</Title>
      {data.map((review: any) => <ReviewCard key={review.id} data={review} />)}
      <Space h="xl" />
    </Flex>
  )
}

export default Reviews;

const ReviewCard = ({data}: any) => {
  if (!data.avatar) {
    if (data.user.id % 10 !== 0) {
      data.avatar = `https://cdn.trustindex.io/assets/default-avatar/noprofile-0${data.user.id % 10}.svg`;
    } else {
      data.avatar = `https://cdn.trustindex.io/assets/default-avatar/noprofile-10.svg`;
    }
  }

  return (
    <Card className={styles.reviewCard} padding="md" withBorder>
      <Flex gap={12} align="center">
        <Avatar src={data.avatar} />
        <Flex direction="column">
          <Text fw={700}>{data.user.name}</Text>
          <Text size="xs" c="dimmed" fs="italic" >{data.createdAt}</Text>
        </Flex>
      </Flex>
      <Divider orientation="horizontal" />
      <Space h="md" />
      <Flex>
        <IconStarFilled color="yellow" size={12}/>
        <IconStarFilled color="yellow" size={12}/>
        <IconStarFilled color="yellow" size={12}/>
        <IconStarFilled color="yellow" size={12}/>
        <IconStarFilled color="yellow" size={12}/>
      </Flex>
      <Space h="md" />
      <Divider orientation="horizontal" />
      <Text>{data.comment}</Text>
    </Card>
  )
}

