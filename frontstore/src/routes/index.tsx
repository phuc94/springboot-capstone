import { createFileRoute } from '@tanstack/react-router'
import AppCarousel from '@/components/Carousel';
import Platform from '@/components/Platform';
import { Space } from '@mantine/core';

export const Route = createFileRoute('/')({
  component: RouteComponent,
})

export function RouteComponent() {
  return (
    <>
      <AppCarousel />
      <Platform />
      <Platform />
      <Platform />
      <Platform />
      <Space h="xl" />
      <Space h="xl" />
    </>
  );
}

