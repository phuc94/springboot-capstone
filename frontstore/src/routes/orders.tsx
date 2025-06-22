import Order from '@/components/Order'
import { Container } from '@mantine/core'
import { createFileRoute } from '@tanstack/react-router'

export const Route = createFileRoute('/orders')({
  component: RouteComponent,
})

function RouteComponent() {
  return <Container><Order /></Container>
}
