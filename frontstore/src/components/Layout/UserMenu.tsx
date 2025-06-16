import { useAuthStore } from "@/store/useAuthStore";
import { Avatar, Flex, Menu, Text } from "@mantine/core";
import { IconLogout2 } from "@tabler/icons-react";
import styles from './Layout.module.scss'

const UserMenu = () => {
  const user = useAuthStore(state => state.user);
  const logout = useAuthStore(state => state.logout);

  const onLogout = () => {
    logout();
    localStorage.removeItem("token")
  }

  return (
    <Menu shadow="md" width={200}>
      <Menu.Target>
        <Flex align="center" gap={10} className={styles.userHover}>
          <Avatar src="https://cdn.trustindex.io/assets/default-avatar/noprofile-10.svg" alt="avatar" />
          <Text>{user?.name}</Text>
        </Flex>
      </Menu.Target>

      <Menu.Dropdown>
        <Menu.Item
          color="red"
          leftSection={<IconLogout2 size={14} />}
          onClick={onLogout}
        >
          Đăng xuất
        </Menu.Item>
      </Menu.Dropdown>
    </Menu>
  )
}

export default UserMenu;

