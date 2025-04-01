import { createFileRoute } from '@tanstack/react-router'

export const Route = createFileRoute('/my-account/address')({
  component: RouteComponent,
})

function RouteComponent() {
  return <div>Hello "/my-account/address"!</div>
}
