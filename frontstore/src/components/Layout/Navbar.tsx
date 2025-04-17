import { Center, Group, Menu, UnstyledButton } from "@mantine/core"
import { Link } from "@tanstack/react-router";
import styles from './Layout.module.scss'

const Navbar = () => {
  return (
    <Center>
      <Group gap='xl'>
      
        <Menu trigger="hover" closeDelay={100}>
          <Menu.Target>
            <Link className={styles.navLink} to="/platform">STEAM</Link>
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
            <Link className={styles.navLink} to="/platform">ORIGIN</Link>
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
            <Link className={styles.navLink} to="/platform">UPLAY</Link>
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
            <Link className={styles.navLink} to="/platform">PLAYSTATION</Link>
          </Menu.Target>

          <Menu.Dropdown>
            <Menu.Item>ASLKDJ</Menu.Item>
          </Menu.Dropdown>
        </Menu>

        <Menu trigger="hover" closeDelay={100}>
          <Menu.Target>
            <Link className={styles.navLink} to="/platform">NINTENDO</Link>
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

