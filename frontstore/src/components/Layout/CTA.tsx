import { Button, Modal } from "@mantine/core"
import { useDisclosure } from "@mantine/hooks";
import { Link } from "@tanstack/react-router";
import LoginForm from "./LoginForm";

const CTA = () => {
  const [opened, { open, close }] = useDisclosure(false);

  return (
    <>
      <Button variant="filled">
        <Link to="/cart" >
          Giỏ hàng
        </Link>
      </Button>
      <Button onClick={open} variant="filled">Đăng nhập/Đăng ký</Button>

      <Modal
        opened={opened} onClose={close} centered
        title="Đăng nhập/Đăng ký"
        padding={30}
        size="auto"
      >
        <LoginForm />
      </Modal>
    </>
  )
}
export default CTA;

