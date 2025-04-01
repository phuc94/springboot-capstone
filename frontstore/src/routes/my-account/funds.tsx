import { createFileRoute } from '@tanstack/react-router'

export const Route = createFileRoute('/my-account/funds')({
  component: RouteComponent,
})

function RouteComponent() {
  return <div>Hello "/my-account/funds"!</div>
}
