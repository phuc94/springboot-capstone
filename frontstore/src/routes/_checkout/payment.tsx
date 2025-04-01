import { createFileRoute } from '@tanstack/react-router'

export const Route = createFileRoute('/_checkout/payment')({
  component: RouteComponent,
})

function RouteComponent() {
  return <div>Hello "/_checkout/payment"!</div>
}
