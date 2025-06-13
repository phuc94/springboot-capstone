import GameDetail from '@/components/GameDetail'
import { Stack } from '@mantine/core'
import { createFileRoute } from '@tanstack/react-router'

export const Route = createFileRoute('/game/$gameId')({
  component: RouteComponent,
})

function RouteComponent() {
  return (
    <Stack>
      <GameDetail/>
    </Stack>
  )
}
