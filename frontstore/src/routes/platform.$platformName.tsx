import GameList from '@/components/GameList'
import { createFileRoute } from '@tanstack/react-router'

export const Route = createFileRoute('/platform/$platformName')({
    component: RouteComponent,
})

function RouteComponent() {
    return <GameList />
}