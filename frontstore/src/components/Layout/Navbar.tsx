import { Center, Group, Menu, UnstyledButton } from "@mantine/core"
import { Link } from "@tanstack/react-router";
import styles from './Layout.module.scss'
import { usePlatformList } from "@/hooks/usePlatformGames";

const Navbar = () => {
  const { data: platforms, isSuccess } = usePlatformList();

  if (!isSuccess) {
    return <StaticPlatform />
  }

  return (
    <Center>
      <Group gap='xl'>

        {platforms.data.length > 0 && platforms.data?.map((platform: any) => (
          <Menu trigger="hover" closeDelay={100}>
            <Menu.Target>
              <Link className={styles.navLink}
                to="/platform/$platformName"
                params={{platformName: platform.name}}
              >{platform.title}</Link>
            </Menu.Target>

            {
              platform.children.length > 0 &&
              <Menu.Dropdown>
                {platform.children.map((children: any)=> (
                  <Menu.Item>
                    <Link className={styles.navLink}
                      to="/platform/$platformName"
                      params={{platformName: children.name}}
                    >{children.title}</Link>
                  </Menu.Item>
                ))}
              </Menu.Dropdown>
            }

          </Menu>
        ))}

      </Group>
    </Center>
  )
}
export default Navbar;

const StaticPlatform = () => {
  return (
    <Center>
      <Group gap='xl'>
      
        <Menu trigger="hover" closeDelay={100}>
          <Menu.Target>
            <span>Steam</span>
          </Menu.Target>

          <Menu.Dropdown>
            <Menu.Item>Steam Key</Menu.Item>
            <Menu.Item>Steam Gift</Menu.Item>
          </Menu.Dropdown>
        </Menu>

        <Menu trigger="hover" closeDelay={100}>
          <Menu.Target>
            <span>Origin</span>
          </Menu.Target>
        </Menu>

        <Menu trigger="hover" closeDelay={100}>
          <Menu.Target>
            <span>Playstation</span>
          </Menu.Target>

          <Menu.Dropdown>
            <Menu.Item>PlayStation Game</Menu.Item>
            <Menu.Item>PlayStation Plus</Menu.Item>
          </Menu.Dropdown>
        </Menu>

        <Menu trigger="hover" closeDelay={100}>
          <Menu.Target>
            <span>Nintendo</span>
          </Menu.Target>

          <Menu.Dropdown>
            <Menu.Item>Nintendo Switch Game</Menu.Item>
            <Menu.Item>Nintendo Switch Online Membership</Menu.Item>
          </Menu.Dropdown>
        </Menu>

        <Menu trigger="hover" closeDelay={100}>
          <Menu.Target>
            <span>Xbox</span>
          </Menu.Target>

          <Menu.Dropdown>
            <Menu.Item>Xbox Game</Menu.Item>
            <Menu.Item>Xbox Game Pass Ultimate</Menu.Item>
          </Menu.Dropdown>
        </Menu>

        <Menu trigger="hover" closeDelay={100}>
          <Menu.Target>
            <span>Uplay</span>
          </Menu.Target>

          <Menu.Dropdown>
            <Menu.Item>Uplay Key</Menu.Item>
            <Menu.Item>Ubisoft Connect</Menu.Item>
          </Menu.Dropdown>
        </Menu>

      </Group>
    </Center>
  )
}

