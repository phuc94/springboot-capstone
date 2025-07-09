import { Flex, Group, Image, Space } from "@mantine/core"
import SearchBar from "./SearchBar"
import CTA from "./CTA"
import Navbar from "./Navbar"
import { Link } from "@tanstack/react-router"

const Header = () => {
  return (
    <Flex direction="column">
      <Group gap="xl">
        <Space w="xl" />
        <Link to="/">
          <Image
            width={90}
            height={90}
            src="https://muaga.me/wp-content/uploads/2020/03/mua-game-logo-pubg-800x800.png"
          />
        </Link>
        <Space w="xl" style={{flex: 1}} />
        <SearchBar style={{flex: 10, maxWidth: "70%"}} />
        <Space w="xl" style={{flex: 1}} />
        <CTA />
        <Space w="xl" />
      </Group>
      <Navbar />
    </Flex>
  )
}

export default Header

