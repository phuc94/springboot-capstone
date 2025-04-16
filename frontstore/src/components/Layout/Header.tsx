import { Group, Space, Stack } from "@mantine/core"
import SearchBar from "./SearchBar"
import CTA from "./CTA"
import Navbar from "./Navbar"

const Header = () => {
  return (
    <Stack>
      <Group gap="xl">
        <Space w="xl" />
        <p>logo</p>
        <Space w="xl" style={{flex: 1}} />
        <SearchBar style={{flex: 10, maxWidth: "70%"}} />
        <Space w="xl" style={{flex: 1}} />
        <CTA />
        <Space w="xl" />
      </Group>
      <Navbar />
    </Stack>
  )
}

export default Header

