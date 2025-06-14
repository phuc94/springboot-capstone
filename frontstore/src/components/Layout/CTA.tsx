import { Button, Modal } from "@mantine/core"
import { useDisclosure } from "@mantine/hooks";
import { Link } from "@tanstack/react-router";
import LoginForm from "./LoginForm";
import { useAuthStore } from "@/store/useAuthStore";
import UserMenu from "./UserMenu";

const CTA = () => {
  const [opened, { open, close }] = useDisclosure(false);
  const isAuthenticated = useAuthStore(state => state.isAuthenticated);

  return (
    <>
      <Button variant="filled">
        <Link to="/cart" >
          Giỏ hàng
        </Link>
      </Button>
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

