import { Avatar, Card, Divider, Flex, Rating, Space, Text, Title } from "@mantine/core";
import styles from './style.module.scss'

const Reviews = ({data}: any) => {
  return (
    <Flex direction="column" justify="start" gap={20}>
      <Title order={2}>Review</Title>
      {data.length > 0 ?
        data.map((review: any) => <ReviewCard key={review.id} data={review} />)
        :
        <Text c="dimmed">Hiện chưa có review nào cho sản phẩm.
        Bạn hãy là người đầu tiên review cho sản phẩm.</Text>
      }
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
      <Space h="xs" />
      <Rating value={5} color="#faf737" size="sm" readOnly/>
      <Space h="xs" />
      <Divider orientation="horizontal" />
      <Space h="xs" />
      <Text>{data.comment}</Text>
    </Card>
  )
}

