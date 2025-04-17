import GameDetail from '@/components/GameDetail'
import { Stack } from '@mantine/core'
import { createFileRoute } from '@tanstack/react-router'

export const Route = createFileRoute('/game_detail')({
  component: RouteComponent,
})

function RouteComponent() {
  return (
    <Stack>
      <GameDetail />
    </Stack>
  )
}
