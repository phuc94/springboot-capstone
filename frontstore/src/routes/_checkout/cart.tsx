import Cart from '@/components/Checkout/Cart'
import { Container } from '@mantine/core'
import { createFileRoute } from '@tanstack/react-router'

export const Route = createFileRoute('/_checkout/cart')({
  component: RouteComponent,
})

function RouteComponent() {
  return (
    <Container size="md">
      <Cart/>
    </Container>
   )
}
