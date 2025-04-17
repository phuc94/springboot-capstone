import { Divider, Flex, Group, Image, Stack, Text } from "@mantine/core"
import { IconStarFilled } from "@tabler/icons-react"

const GameDetail = () => {
  return (
    <Stack>
      <GameInfo />
    </Stack>
  )
}
export default GameDetail

const GameInfo = () => {
  return (
    <Flex>
      <Image src="https://muaga.me/wp-content/uploads/2019/09/Resident-Evil-0-Biohazard-0-HD-Remaster-Steam-Key-1.jpg.webp" />
      <Stack>
        <Text>Resident Evil 0 / Biohazard 0 HD Remaster Steam Key</Text>
        <Group>
          <IconStarFilled color="yellow" />
          <IconStarFilled color="yellow" />
          <IconStarFilled color="yellow" />
          <IconStarFilled color="yellow" />
          <IconStarFilled color="yellow" />
        </Group>
        <Text>(34 đánh giá của khách hàng)</Text>
        <Group>
          <Text td="line-through" c="dimmed">276.000₫</Text>
          <Text>220.000₫</Text>
        </Group>
      </Stack>
      <Divider orientation="vertical" />
      <Stack>
        {Array(4).fill(0).map(x=>(
          <Flex gap={4}>
            <Image
              width={65}
              height={65}
              src="https://muaga.me/wp-content/uploads/2022/04/premium-icon-1.png"
            />
            <Text>Mua bản quyền để nhận được tính năng cao cấp</Text>
          </Flex>
        ))}
      </Stack>
    </Flex>
  )
}

