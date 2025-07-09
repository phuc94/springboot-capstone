import { Button, Indicator, Modal } from "@mantine/core"
import { useDisclosure } from "@mantine/hooks";
import { Link } from "@tanstack/react-router";
import LoginForm from "./LoginForm";
import { useAuthStore } from "@/store/useAuthStore";
import UserMenu from "./UserMenu";
import { useCartStore } from "@/store/useCartStore";
import { IconShoppingCart } from "@tabler/icons-react";

const CTA = () => {
  const [opened, { open, close }] = useDisclosure(false);
  const isAuthenticated = useAuthStore(state => state.isAuthenticated);
  const items = useCartStore(state => state.items);

  return (
    <>
      {isAuthenticated &&
        <Indicator
          color="red"
          label={items.length}
          disabled={items.length === 0}
          size={18}
          radius="xl"
        >
          <Link to="/cart" >
            <Button variant="filled" color="green">
              <IconShoppingCart />
              Giỏ hàng
            </Button>
          </Link>
        </Indicator>
      }
      {isAuthenticated ? 
        <UserMenu />
        :
        <Button onClick={open} variant="filled">Đăng nhập/Đăng ký</Button>
      }

      <Modal
        opened={opened} onClose={close} centered
        title="Đăng nhập/Đăng ký"
        padding={30}
        size="auto"
      >
        <LoginForm closeModal={close} />
      </Modal>
    </>
  )
}
export default CTA;

