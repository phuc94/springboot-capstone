import Success from '@/components/Checkout/Success'
import { Container } from '@mantine/core'
import { createFileRoute } from '@tanstack/react-router'

export const Route = createFileRoute('/payment/success')({
  component: RouteComponent,
})

function RouteComponent() {
  return (
    <Container>
      <Success/>
    </Container>
  )
}
