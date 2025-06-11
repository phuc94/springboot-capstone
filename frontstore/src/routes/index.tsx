import { createFileRoute } from '@tanstack/react-router'
import AppCarousel from '@/components/Carousel';
import Platform from '@/components/Platform';
import { Space } from '@mantine/core';
import { usePlatformGames } from '@/hooks/usePlatformGames';

export const Route = createFileRoute('/')({
  component: RouteComponent,
})

export function RouteComponent() {

  const query = usePlatformGames();

  return (
    <>
      <AppCarousel />
      {
        query.data?.data?.map((platform: any) => 
          <Platform data={platform} key={platform.name} />
        )
      }
      <Space h="xl" />
      <Space h="xl" />
    </>
  );
}

