import GameList from '@/components/GameList'
import { createFileRoute } from '@tanstack/react-router'

export const Route = createFileRoute('/platform')({
  component: AllPlatformsComponent,
})

function AllPlatformsComponent() {
  return (
      <GameList />
  )
}



