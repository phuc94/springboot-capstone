import Checkout from '@/components/Checkout/Checkout'
import { Container } from '@mantine/core'
import { createFileRoute } from '@tanstack/react-router'

export const Route = createFileRoute('/_checkout/checkout')({
  component: RouteComponent,
})

function RouteComponent() {
  return (
    <Container size="md">
      <Checkout/>
    </Container>
   )
}
