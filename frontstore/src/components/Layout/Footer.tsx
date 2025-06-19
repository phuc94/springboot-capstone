import { Carousel } from "@mantine/carousel"
import { Avatar, Button, Card, Center, Flex, Group, Image, Rating, Space, Stack, Text } from "@mantine/core"
import { IconMessageStar } from "@tabler/icons-react"
import Autoplay from "embla-carousel-autoplay"
import { useRef } from "react"
import styles from './Layout.module.scss'

const Footer = () => {
  const autoplay = useRef(Autoplay({ delay: 5000 }))

  return (
    <Center className={styles.footer}>
      <Stack>
        <Space h="xl"/>
        <Group>
          <ReviewSite />
          <Carousel
            style={{width: 640}}
            withIndicators={false}
            withControls={true}
            height={200}
            plugins={[autoplay.current]}
            onMouseEnter={autoplay.current.stop}
            onMouseLeave={autoplay.current.reset}
            loop
            align="start"
            slideSize="50%"
            slidesToScroll={1}
          >
            <Carousel.Slide>
              <ReviewCard />
            </Carousel.Slide>
            <Carousel.Slide>
              <ReviewCard />
            </Carousel.Slide>
            <Carousel.Slide>
              <ReviewCard />
            </Carousel.Slide>
            <Carousel.Slide>
              <ReviewCard />
            </Carousel.Slide>
          </Carousel>
        </Group>
        <Flex justify="space-between">
          <Stack>
            <Text>Quy định & điều khoản</Text>
            <Text>Chính sách bảo mật</Text>
            <Text>Chính sách trả hàng</Text>
          </Stack>
          <Stack>
            <Text>Hướng dẫn</Text>
            <Text>Hướng dẫn mua hàng</Text>
            <Text>Cách kích hoạt sản phẩm</Text>
          </Stack>
          <Stack>
            <Text>Hợp tác</Text>
            <Text>Kiếm tiền cùng muaGAME</Text>
            <Text>Liên hệ hợp tác</Text>
          </Stack>
        </Flex>
        <Space h="md" />
        <Center>
          <Text c="dimmed">
            Copyright 2025 © MuaGa.me - Cảm ơn bạn đã lựa chọn sử dụng các sản phẩm bản quyền.
          </Text>
        </Center>
        <Space h="xl" />
      </Stack>
    </Center>
  )
}
export default Footer

export const ReviewCard = () => {
  return (
    <Card className={styles.reviewCard} padding="md" >
      <Group>
        <Avatar src="https://cdn.trustindex.io/assets/default-avatar/noprofile-10.svg" />
        <Text fw={700}>Nhân Lê</Text>
      </Group>
      <Space h="md" />
      <Group>
        <IconMessageStar />
        <Text fw={700} size="sm">đề xuất</Text>
      </Group>
      <Space h="md" />
      <Text>
        Xin 5 sao chất lượng cho shop! Dù đôi khi shop làm em hơi đau tim xí! 🤣🤣🤣
      </Text>
    </Card>
  )
}

const ReviewSite = () => {
  return (
    <Stack>
      <Group>
        <Image
          width={65}
          height={65}
          src="https://scontent-hkg1-2.xx.fbcdn.net/v/t39.30808-1/322006656_491317216422889_1302875434044134969_n.jpg?stp=dst-jpg_s160x160_tt6&_nc_cat=107&ccb=1-7&_nc_sid=79bf43&_nc_ohc=cr5O6X8_ooMQ7kNvwGUGE7h&_nc_oc=Adl2HmI8xSzWSgCMPkWj4yGbww1zbkFrJ056VtKSX9JQduS76Yet81whHWzZNDQPiNCCaRh6RQHmu7vnBC8iFCNC&_nc_zt=24&_nc_ht=scontent-hkg1-2.xx&edm=AOf6bZoEAAAA&_nc_gid=9Setjpk8utI0U121Cl-HZw&oh=00_AfFcK4d6GHe-nLKlTwXmwIl6Jc3z8wDxB4O6eLqtjB81PQ&oe=6806F077"/>
          <Stack>
            <Text>MuaGa.me - Game Bản Quyền</Text>
            <Rating value={5} color="#faf737" size="lg" readOnly/>
            <Text>239 Facebook bài đánh giá</Text>
          </Stack>
      </Group>
      <Button>Viết đánh giá</Button>
    </Stack>
  )
}

