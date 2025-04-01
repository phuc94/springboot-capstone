import { createFileRoute } from '@tanstack/react-router'

export const Route = createFileRoute('/my-account/orders')({
  component: RouteComponent,
})

function RouteComponent() {
  return <div>Hello "/my-account/orders"!</div>
}
