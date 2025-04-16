import { Center, Group, Menu, UnstyledButton } from "@mantine/core"

const Navbar = () => {
  return (
    <Center>
      <Group gap='xl'>
      
        <Menu trigger="hover" closeDelay={100}>
          <Menu.Target>
            <UnstyledButton>STEAM</UnstyledButton>
          </Menu.Target>

          <Menu.Dropdown>
            <Menu.Item>ASLKDJ</Menu.Item>
            <Menu.Item>ASLKDJ</Menu.Item>
            <Menu.Item>ASLKDJ</Menu.Item>
            <Menu.Item>ASLKDJ</Menu.Item>
            <Menu.Item>ASLKDJ</Menu.Item>
          </Menu.Dropdown>
        </Menu>

        <Menu trigger="hover" closeDelay={100}>
          <Menu.Target>
            <UnstyledButton>ORIGIN</UnstyledButton>
          </Menu.Target>

          <Menu.Dropdown>
            <Menu.Item>ASLKDJ</Menu.Item>
            <Menu.Item>ASLKDJ</Menu.Item>
            <Menu.Item>ASLKDJ</Menu.Item>
            <Menu.Item>ASLKDJ</Menu.Item>
            <Menu.Item>ASLKDJ</Menu.Item>
          </Menu.Dropdown>
        </Menu>

        <Menu trigger="hover" closeDelay={100}>
          <Menu.Target>
            <UnstyledButton>UPLAY</UnstyledButton>
          </Menu.Target>

          <Menu.Dropdown>
            <Menu.Item>ASLKDJ</Menu.Item>
          </Menu.Dropdown>
        </Menu>

        <Menu trigger="hover" closeDelay={100}>
          <Menu.Target>
            <UnstyledButton>XBOX</UnstyledButton>
          </Menu.Target>

          <Menu.Dropdown>
            <Menu.Item>ASLKDJ</Menu.Item>
          </Menu.Dropdown>
        </Menu>

        <Menu trigger="hover" closeDelay={100}>
          <Menu.Target>
            <UnstyledButton>PLAYSTATION</UnstyledButton>
          </Menu.Target>

          <Menu.Dropdown>
            <Menu.Item>ASLKDJ</Menu.Item>
          </Menu.Dropdown>
        </Menu>

        <Menu trigger="hover" closeDelay={100}>
          <Menu.Target>
            <UnstyledButton>NINTENDO</UnstyledButton>
          </Menu.Target>

          <Menu.Dropdown>
            <Menu.Item>ASLKDJ</Menu.Item>
          </Menu.Dropdown>
        </Menu>

      </Group>
    </Center>
  )
}
export default Navbar;

