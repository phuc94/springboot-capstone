import { Center, Container, Grid, Pagination, Space, Text, Title } from "@mantine/core"
import GameCard from "../GameCard"
import { useParams } from "@tanstack/react-router"
import { usePlatformGameList } from "@/hooks/usePlatformGames"
import { useState } from "react"

const GameList = () => {
    const { platformName } = useParams({ from: '/platform/$platformName' })
    const [currentPage, setCurrentPage] = useState(1)
    const [gamesPerPage] = useState(12)

    const { data, isLoading } = usePlatformGameList(platformName || '')
    const games = data?.data || []

    // Pagination logic
    const indexOfLastGame = currentPage * gamesPerPage
    const indexOfFirstGame = indexOfLastGame - gamesPerPage
    const currentGames = games.slice(indexOfFirstGame, indexOfLastGame)
    const totalPages = Math.ceil(games.length / gamesPerPage)

    const handlePageChange = (page: number) => {
        setCurrentPage(page)
    }

    return (
        <Container>
            <Space h="xl" />
            <Title>{platformName ? `${platformName.toUpperCase()} Games` : 'All Games'}</Title>
            <Space h="xl" />
            <Text size="xl">Mua game {platformName ? platformName : ''} tại shop MuaGa.me với giá rẻ, uy tín. Nhận game nhanh chóng, nhiều giảm giá so với mua trực tiếp.</Text>
            <Space h="xl" />
            {isLoading ? (
                <Text>Loading games...</Text>
            ) : (
                <>
                    <Grid gutter="md">
                        {currentGames.length > 0 ? (
                            currentGames.map((game: any) => (
                                <Grid.Col key={game.id} span={3}>
                                    <GameCard data={game} />
                                    <Space h="md" />
                                </Grid.Col>
                            ))
                        ) : (
                            <Text>No games found for this platform.</Text>
                        )}
                    </Grid>
                    <Space h="xl" />
                    {totalPages > 1 && (
                        <Center>
                            <Pagination total={totalPages} value={currentPage} onChange={handlePageChange} />
                        </Center>
                    )}
                </>
            )}
            <Space h="xl" />
        </Container>
    )
}

export default GameList

