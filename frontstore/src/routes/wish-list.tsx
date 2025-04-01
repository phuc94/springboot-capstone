import { createFileRoute } from '@tanstack/react-router'

export const Route = createFileRoute('/wish-list')({
  component: RouteComponent,
})

function RouteComponent() {
  return <div>Hello "/wish-list"!</div>
}
