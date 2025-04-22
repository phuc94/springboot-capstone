import { Button } from "@mantine/core"
import { Link } from "@tanstack/react-router";

const CTA = () => {
  return (
    <>
      <Button variant="filled">
        <Link to="/cart" >
          Giỏ hàng
        </Link>
      </Button>
      <Button variant="filled">Đăng nhập/Đăng ký</Button>
    </>
  )
}
export default CTA;

